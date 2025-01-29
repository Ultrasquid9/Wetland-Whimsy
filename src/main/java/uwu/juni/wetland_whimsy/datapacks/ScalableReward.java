package uwu.juni.wetland_whimsy.datapacks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
		private static Registry<ScalableReward> SCALABLE_REWARDS = null;

		public static void add(Registry<ScalableReward> rewards) {
			SCALABLE_REWARDS = rewards;
		}

		public static List<ItemStack> getLoot(
			RandomSource random, 
			ResourceLocation key, 
			int quality
		) {
			var scalable_reward = SCALABLE_REWARDS.get(key);
			if (scalable_reward == null)
				return List.of(); 

			var table = new HashMap<ResourceLocation, Integer>(scalable_reward.rewards());
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
