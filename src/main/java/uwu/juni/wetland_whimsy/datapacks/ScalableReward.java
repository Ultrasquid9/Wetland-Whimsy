package uwu.juni.wetland_whimsy.datapacks;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.resources.ResourceKey;

public record ScalableReward(String input, Map<String, Integer> rewards) {
	public static final Codec<ScalableReward> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			Codec.STRING.fieldOf("input").forGetter(ScalableReward::input),
			Codec.unboundedMap(Codec.STRING, Codec.INT).fieldOf("rewards").forGetter(ScalableReward::rewards)
		)
		.apply(instance, ScalableReward::new)	
	);

	public class Manager {
		private static List<ScalableReward> SCALABLE_REWARDS = List.of();

		public static void add(Set<Entry<ResourceKey<ScalableReward>, ScalableReward>> rewards) {
			for (var entry : rewards)
				SCALABLE_REWARDS.add(entry.getValue());
		}
	}
}
