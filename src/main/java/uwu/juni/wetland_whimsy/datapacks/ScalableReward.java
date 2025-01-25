package uwu.juni.wetland_whimsy.datapacks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.item.ItemStack;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public record ScalableReward(String input, Map<String, Integer> rewards) {
	public static final Codec<ScalableReward> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			Codec.STRING.fieldOf("input").forGetter(ScalableReward::input),
			Codec.unboundedMap(Codec.STRING, Codec.INT).fieldOf("rewards").forGetter(ScalableReward::rewards)
		)
		.apply(instance, ScalableReward::new)	
	);

	public class Manager {
		private static List<ScalableReward> SCALABLE_REWARDS = new ArrayList<>();

		public static void add(Set<Entry<ResourceKey<ScalableReward>, ScalableReward>> rewards) {
			for (var entry : rewards)
				SCALABLE_REWARDS.add(entry.getValue());

			for (var entry : SCALABLE_REWARDS)
				WetlandWhimsy.LOGGER.info(entry.input());
		}

		public static List<ItemStack> getLoot(
			RandomSource random, 
			String key, 
			int quality
		) {
			HashMap<String, Integer> table = null;

			for (var i : SCALABLE_REWARDS) {
				if (i.input().contains(key)) {
					table = new HashMap<String, Integer>(i.rewards());
					break;
				}
			}

			if (table == null)
				return List.of();

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
