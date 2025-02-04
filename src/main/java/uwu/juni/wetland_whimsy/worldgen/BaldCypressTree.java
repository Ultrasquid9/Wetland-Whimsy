package uwu.juni.wetland_whimsy.worldgen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import uwu.juni.wetland_whimsy.data.registries.WetlandWhimsyConfiguredFeatures;

public class BaldCypressTree extends AbstractTreeGrower {
	@Override
	@Nullable
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@Nonnull RandomSource random, boolean bool) {
		return WetlandWhimsyConfiguredFeatures.BALD_CYPRESS_TREE;
	}
}
