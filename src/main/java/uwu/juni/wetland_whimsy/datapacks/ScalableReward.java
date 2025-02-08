package uwu.juni.wetland_whimsy.datapacks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.misc.Config;

public record ScalableReward(ResourceLocation input, List<Loot> rewards) {
	public static final Codec<ScalableReward> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			ResourceLocation.CODEC.fieldOf("input").forGetter(ScalableReward::input),
			Loot.CODEC.listOf().fieldOf("rewards").forGetter(ScalableReward::rewards)
		)
		.apply(instance, ScalableReward::new)	
	);

	public record Loot(
		Integer weight, 
		Integer maxStackSize,
		Either<ResourceLocation, List<ResourceLocation>> items
	) {
		public static final Codec<ScalableReward.Loot> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				Codec.INT.fieldOf("weight").forGetter(Loot::weight),
				Codec.INT.optionalFieldOf("max_stack_size", 64).forGetter(Loot::maxStackSize),
				Codec.either(
					ResourceLocation.CODEC, 
					ResourceLocation.CODEC.listOf()
				).fieldOf("items").forGetter(Loot::items)
			)
			.apply(instance, Loot::new)
		);

		public ItemStack getItem(RandomSource random, int quality) {
			ResourceLocation rLoc;

			if (items().left().isPresent()) {
				rLoc = items().left().get();
			} else {
				var list = items().right().get();
				rLoc = list.get(random.nextInt(0, list.size()));
			}

			var item = BuiltInRegistries.ITEM.getOptional(rLoc);

			if (item.isEmpty())
				return new ItemStack(Items.RED_WOOL);

			var stack = new ItemStack(item.get());
			growStack(random, stack, quality);
			return stack;
		}

		private void growStack(RandomSource random, @Nonnull ItemStack stack, int quality) {
			var size = Integer.min(
				maxStackSize, 
				Integer.min(
					stack.getMaxStackSize(), 
					random.nextInt(1, quality)
				)
			);

			stack.grow(size - 1);
	
			if (stack.isDamageableItem())
				stack.setDamageValue(random.nextInt(1, stack.getMaxDamage() - 1));
		}
	}

	public class Manager {
		public static List<ItemStack> getLoot(
			ServerLevel level, 
			ItemLike key, 
			int quality
		) {
			var random = level.getRandom();
			var registries = level.getServer().registryAccess().lookupOrThrow(Datapacks.SCALABLE_REWARD);

			// Java doesn't let you modify variables directly within Lambdas, you need to use a wrapper.
			// This is because variables are cloned when passed to lambdas.
			// The wrapper acts as a pointer to the variable, so it gets cloned but the variable inside does not.
			var wrapper = new Object() { ScalableReward scalableReward = null; };
			registries.listElements().forEach(registry -> {
				var b = registry.value();

				WetlandWhimsy.LOGGER.info("" + b.input());

				if (b.input().toString().equals(key.asItem().toString()))
					wrapper.scalableReward = b;
			});

			if (wrapper.scalableReward == null)
				return List.of(); 

			var rewards = new ArrayList<>(wrapper.scalableReward.rewards);
			var list = new ArrayList<ItemStack>();

			var maxWeight = 0;
			for (var reward : rewards)
				maxWeight += reward.weight();

			quality++;
			for (var i = 0; i < Math.min(random.nextInt(random.nextInt(1, quality), quality), Config.ancientPotMaxDropCount); i++)
				list.add(getStack(rewards, random, quality, maxWeight));

			return list;
		}

		private static ItemStack getStack(
			List<Loot> rewards, 
			RandomSource random, 
			int quality, 
			int maxWeight
		) {
			var choices = new ArrayList<Loot>();

			for (var i = 0; i < quality - 1; i++) {
				var rand = random.nextInt(0, maxWeight);
				var cursor = 0;

				for (var choice : rewards) {
					cursor += choice.weight();

					if (cursor >= rand) {
						choices.add(choice);
						break;
					}
				}
			}

			WetlandWhimsy.LOGGER.debug("maxWeight: " + maxWeight);
			WetlandWhimsy.LOGGER.debug("quality: " + quality);
			WetlandWhimsy.LOGGER.debug(choices.toString());

			var choiceWeight = Integer.MAX_VALUE;
			Loot toReturn = null;

			for (var choice : choices) {
				if (choice.weight() > choiceWeight)
					continue;
				
				choiceWeight = choice.weight();
				toReturn = choice;
			}

			rewards.remove(toReturn);
			return toReturn == null
				? new ItemStack(Items.RED_WOOL)
				: toReturn.getItem(random, quality);
		}
	}
}
