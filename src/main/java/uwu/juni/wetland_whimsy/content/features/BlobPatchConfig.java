package uwu.juni.wetland_whimsy.content.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;

public record BlobPatchConfig(
	RuleBasedBlockStateProvider stateProvider, 
	BlockPredicate target, 
	IntProvider radius
) implements FeatureConfiguration {
	public static final Codec<BlobPatchConfig> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			RuleBasedBlockStateProvider.CODEC.fieldOf("state_provider").forGetter(BlobPatchConfig::stateProvider),
			BlockPredicate.CODEC.fieldOf("target").forGetter(BlobPatchConfig::target),
			IntProvider.codec(0, 8).fieldOf("radius").forGetter(BlobPatchConfig::radius)
		)
		.apply(instance, BlobPatchConfig::new)
	);
}
