package uwu.juni.wetland_whimsy.datapacks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.item.ItemStack;

public record ScalableReward(String input, Map<String, Integer> rewards) {
	public static final Codec<ScalableReward> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			Codec.STRING.fieldOf("input").forGetter(ScalableReward::input),
			Codec.unboundedMap(Codec.STRING, Codec.INT).fieldOf("rewards").forGetter(ScalableReward::rewards)
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

			var table = new HashMap<String, Integer>(scalable_reward.rewards());

			List<Integer> weights = new ArrayList<>();
			for (var i : table.values())
				weights.add(i);
			var average = average(weights);

			var quality2 = Math.max(1, (int)((double)quality * 0.5));
			for (var weight : table.values())
				if (weight > average)
					weight /= quality2;
				else if (weight < average)
					weight *= quality2;

			List<ItemStack> list = new ArrayList<>();
			for (var i = Math.min(random.nextInt(1, quality), 10); i > 0; i--) {
				if (table.isEmpty())
					break;

				var builder = SimpleWeightedRandomList.<String>builder();
				for (var j : table.entrySet())
					builder.add(j.getKey(), j.getValue());
				var loottable = builder.build();

				var str = loottable
					.getRandom(random)
					.get()
					.data();

				table.remove(str);

				var item = new ItemStack(
					BuiltInRegistries.ITEM.get(
						ResourceLocation.parse(str)
					)
				);
				growStack(random, item, quality);
				list.add(item);
			}

			return list;
		}

		private static void growStack(RandomSource random, ItemStack stack, int quality) {
			if (stack.toString().contains("template")) return;
			stack.grow(Integer.min(stack.getMaxStackSize(), random.nextInt(1, quality)) - 1);
	
			if (stack.isDamageableItem())
				stack.setDamageValue(random.nextInt(1, stack.getMaxDamage() - 1));
		}

		private static int average(List<Integer> input) {
			int average = 0;

			for (var i : input)
				average += i;

			average /= input.size();

			return average;
		}
	}
}
