package uwu.juni.wetland_whimsy.datapacks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public record ScalableReward(ResourceLocation input, int max_drops, List<Loot> rewards) {
	public static final Codec<ScalableReward> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			ResourceLocation.CODEC.fieldOf("input").forGetter(ScalableReward::input),
			Codec.INT.fieldOf("max_drops").forGetter(ScalableReward::max_drops),
			Loot.CODEC.listOf().fieldOf("rewards").forGetter(ScalableReward::rewards)
		)
		.apply(instance, ScalableReward::new)	
	);

	public record Loot(
		Integer weight, 
		Integer maxStackSize,
		Either<ResourceLocation, List<ResourceLocation>> items,
		List<ResourceKey<Enchantment>> enchantments
	) {
		public static final Codec<ScalableReward.Loot> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
				Codec.INT.fieldOf("weight").forGetter(Loot::weight),
				Codec.INT.optionalFieldOf("max_stack_size", 64).forGetter(Loot::maxStackSize),
				Codec.either(
					ResourceLocation.CODEC, 
					ResourceLocation.CODEC.listOf()
				).fieldOf("items").forGetter(Loot::items),
				Codec.list(ResourceKey.codec(Registries.ENCHANTMENT)).optionalFieldOf("enchantments", List.of()).forGetter(Loot::enchantments)
			)
			.apply(instance, Loot::new)
		);

		public ItemStack getItem(ServerLevel level, int quality) {
			ResourceLocation rLoc;

			if (items().left().isPresent()) {
				rLoc = items().left().get();
			} else {
				var list = items().right().get();
				rLoc = list.get(level.getRandom().nextInt(0, list.size()));
			}

			var item = BuiltInRegistries.ITEM.getOptional(rLoc);

			if (item.isEmpty())
				return new ItemStack(Items.RED_WOOL);

			var stack = new ItemStack(item.get());
			growStack(level, stack, quality);
			return stack;
		}

		private void growStack(ServerLevel level, @Nonnull ItemStack stack, int quality) {
			var random = level.getRandom();
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

			if (!stack.isEnchantable() || random.nextBoolean())
				return;

			var getter = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
			outer: for (var e : enchantments) {
				if (random.nextBoolean())
					continue;

				var enchantment = getter.getOrThrow(e);

				// Ensuring enchantment is valid for this item
				if (!stack.supportsEnchantment(enchantment))
					continue;

				for (var idk : stack.getAllEnchantments(getter).entrySet())
					if (!Enchantment.areCompatible(idk.getKey(), enchantment))
						continue outer;

				var maxLevel = enchantment.value().getMaxLevel();
				WetlandWhimsy.LOGGER.info("" + maxLevel);
				stack.enchant(
					enchantment, 
					maxLevel == 1 // It complains if forced to choose a random number between 1 and 1
						? maxLevel
						: random.nextInt(1, maxLevel)
				);
			}
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
			for (
				var i = 0; 
				i < Math.min(random.nextInt(random.nextInt(1, quality), quality), wrapper.scalableReward.max_drops); 
				i++
			) {
				list.add(getStack(level, rewards, quality, maxWeight));
			}

			return list;
		}

		private static ItemStack getStack(
			ServerLevel level,
			List<Loot> rewards, 
			int quality, 
			int maxWeight
		) {
			var choices = new ArrayList<Loot>();

			for (var i = 0; i < quality - 1; i++) {
				var rand = level.getRandom().nextInt(0, maxWeight);
				var cursor = 0;

				for (var choice : rewards) {
					cursor += choice.weight();

					if (cursor >= rand) {
						choices.add(choice);
						break;
					}
				}
			}

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
				: toReturn.getItem(level, quality);
		}
	}
}
