package uwu.juni.wetland_whimsy.datapacks;

import java.util.ArrayList;
import java.util.HashMap;
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
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

public record ScalableReward(ResourceLocation input, List<Loot> rewards) {
	public static final Codec<ScalableReward> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			ResourceLocation.CODEC.fieldOf("input").forGetter(ScalableReward::input),
			Loot.CODEC.listOf().fieldOf("rewards").forGetter(ScalableReward::rewards)
		)
		.apply(instance, ScalableReward::new)	
	);

	public record Loot(Integer weight, Either<ResourceLocation, List<ResourceLocation>> items) {
		public static final Codec<ScalableReward.Loot> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				Codec.INT.fieldOf("weight").forGetter(Loot::weight),
				Codec.either(
					ResourceLocation.CODEC, 
					ResourceLocation.CODEC.listOf()
				).fieldOf("items").forGetter(Loot::items)
			)
			.apply(instance, Loot::new)
		);

		public ResourceLocation getRLoc(RandomSource random) {
			if (items().left().isPresent())
				return items().left().get();

			var list = items().right().get();
			return list.get(random.nextInt(0, list.size()));
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
			ItemStack item = new ItemStack(Items.RED_WOOL);
			var choices = new HashMap<ResourceLocation, Integer>();

			for (var i = 0; i < quality - 1; i++) {
				var rand = random.nextInt(0, maxWeight);
				var cursor = 0;

				for (var choice : rewards) {
					cursor += choice.weight();

					if (cursor >= rand) {
						choices.put(choice.getRLoc(random), choice.weight());
						break;
					}
				}
			}

			WetlandWhimsy.LOGGER.debug("maxWeight: " + maxWeight);
			WetlandWhimsy.LOGGER.debug("quality: " + quality);
			WetlandWhimsy.LOGGER.debug(choices.toString());

			var choiceWeight = Integer.MAX_VALUE;
			for (var choice : choices.entrySet()) {
				if (choice.getValue() <= choiceWeight) {
					choiceWeight = choice.getValue();

					var i2 = BuiltInRegistries.ITEM.getOptional(choice.getKey());
					if (i2.isEmpty())
						WetlandWhimsy.LOGGER.error("item " + choice.getKey() + " not found!");
					else
						item = new ItemStack(i2.get());
				}
			}

			growStack(random, item, quality);
			return item;
		}

		private static void growStack(RandomSource random, @Nonnull ItemStack stack, int quality) {
			if (stack.is(WetlandWhimsyTags.Items.SCALABLE_DO_NOT_GROW)) return;

			stack.grow(Integer.min(stack.getMaxStackSize(), random.nextInt(1, quality)) - 1);
	
			if (stack.isDamageableItem())
				stack.setDamageValue(random.nextInt(1, stack.getMaxDamage() - 1));
		}
	}
}
