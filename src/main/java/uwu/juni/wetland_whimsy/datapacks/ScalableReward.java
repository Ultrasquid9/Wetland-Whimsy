package uwu.juni.wetland_whimsy.datapacks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

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
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

public record ScalableReward(ResourceLocation input, Map<ResourceLocation, Integer> rewards) {
	public static final Codec<ScalableReward> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			ResourceLocation.CODEC.fieldOf("input").forGetter(ScalableReward::input),
			Codec.unboundedMap(ResourceLocation.CODEC, Codec.INT).fieldOf("rewards").forGetter(ScalableReward::rewards)
		)
		.apply(instance, ScalableReward::new)	
	);

	public class Manager {
		public static List<ItemStack> getLoot(
			ServerLevel level, 
			ItemLike key, 
			int quality
		) {
			var random = level.getRandom();
			var registries = level.getServer().registryAccess().lookupOrThrow(Datapacks.SCALABLE_REWARD);

			// Java doesn't let you modify variables directly within Lambdas, you need to use a wrapper
			// This is because variables are cloned when passed to lambdas, whereas the wrapper is a pointer to the variable
			var wrapper = new Object() { ScalableReward scalableReward = null; };
			registries.listElements().forEach(registry -> {
				var b = registry.value();

				WetlandWhimsy.LOGGER.info("" + b.input());

				if (b.input().toString().equals(key.asItem().toString()))
					wrapper.scalableReward = b;
			});

			if (wrapper.scalableReward == null)
				return List.of(); 

			var table = new HashMap<ResourceLocation, Integer>(wrapper.scalableReward.rewards());
			var list = new ArrayList<ItemStack>();

			var maxWeight = 0;
			for (var i : table.entrySet())
				maxWeight += i.getValue();

			quality++;
			for (var i = 0; i < Math.min(random.nextInt(random.nextInt(1, quality), quality), 10); i++)
				list.add(getStack(table, random, quality, maxWeight));

			return list;
		}

		private static ItemStack getStack(
			HashMap<ResourceLocation, Integer> table, 
			RandomSource random, 
			int quality, 
			int maxWeight
		) {
			ItemStack item = new ItemStack(Items.RED_WOOL);
			var choices = new HashMap<ResourceLocation, Integer>();

			for (var i = 0; i < quality - 1; i++) {
				var rand = random.nextInt(0, maxWeight);
				var cursor = 0;

				for (var choice : table.entrySet()) {
					cursor += choice.getValue();

					if (cursor >= rand) {
						choices.put(choice.getKey(), choice.getValue());
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
