package corundum.wetland_whimsy.data.worldgen;

import java.util.List;

import com.google.common.collect.ImmutableList;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

public class WetlandsWhimsyPlacedFeaturesDatagen {
	public static final ResourceKey<PlacedFeature> TREES_BOG = createKey("trees_bog");

	public static final ResourceKey<PlacedFeature> CORDGRASS_PATCH = createKey("cordgrass_patch");
	public static final ResourceKey<PlacedFeature> PENNYWORT_PATCH = createKey("pennywort_patch");

	public static void bootstap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

		context.register(
			TREES_BOG, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeaturesDatagen.TREES_BOG), 
				treePlacement()
			)
		);

		context.register(
			CORDGRASS_PATCH, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeaturesDatagen.CORDGRASS_PATCH), 
				foliagePlacement()
			)
		);
		context.register(
			PENNYWORT_PATCH, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeaturesDatagen.PENNYWORT_PATCH), 
				foliagePlacement()
			)
		);
	}

	public static ResourceKey<PlacedFeature> createKey(String name) {
		return ResourceKey.create(
			Registries.PLACED_FEATURE, 
			ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, name)
		);
	}

	public static List<PlacementModifier> treePlacement() {
		return ImmutableList.<PlacementModifier>builder()
			.add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
			.add(SurfaceWaterDepthFilter.forMaxDepth(2))
			.add(PlacementUtils.countExtra(24, 0.1F, 1))
			.add(PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get()))
			.build();
	}

	public static List<PlacementModifier> foliagePlacement() {
		return ImmutableList.<PlacementModifier>builder()
			.add(PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
			.add(CountPlacement.of(3))
			.add(RarityFilter.onAverageOnceEvery(3))
			.add(InSquarePlacement.spread())
			.add(PlacementUtils.filteredByBlockSurvival(Blocks.SHORT_GRASS))
			.build();
	}
}
