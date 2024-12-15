package corundum.wetland_whimsy.data.worldgen;

import java.util.List;

import com.google.common.collect.ImmutableList;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

public class WetlandsWhimsyPlacedFeaturesDatagen {
	public static final ResourceKey<PlacedFeature> TREES_BOG = createKey("trees_bog");
	public static final ResourceKey<PlacedFeature> TREES_MARSH = createKey("trees_marsh");

	public static final ResourceKey<PlacedFeature> CORDGRASS_PATCH = createKey("cordgrass_patch");
	public static final ResourceKey<PlacedFeature> PENNYWORT_PATCH = createKey("pennywort_patch");
	public static final ResourceKey<PlacedFeature> MUD_POOL = createKey("mud_pool");
	public static final ResourceKey<PlacedFeature> SUPER_THICK_CORDGRASS_PATCH = createKey("super_thick_cordgrass_patch");
	public static final ResourceKey<PlacedFeature> LILYPAD_CLONE_CAUSE_FUCK_THE_FEATURE_CYCLE = createKey("lilypad_clone");

	public static void bootstap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

		context.register(
			TREES_BOG, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeaturesDatagen.TREES_BOG), 
				bogTreePlacement()
			)
		);

		context.register(
			TREES_MARSH, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeaturesDatagen.TREES_MARSH), 
				marshTreePlacement()
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

		context.register(
			MUD_POOL, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeaturesDatagen.MUD_POOL), 
				foliagePlacement()
			)
		);
		context.register(
			SUPER_THICK_CORDGRASS_PATCH, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeaturesDatagen.CORDGRASS_PATCH), 
				bogTreePlacement()
			)
		);
		context.register(
			LILYPAD_CLONE_CAUSE_FUCK_THE_FEATURE_CYCLE, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(VegetationFeatures.PATCH_WATERLILY), 
				VegetationPlacements.worldSurfaceSquaredWithCount(4)
			)
		);
	}

	public static ResourceKey<PlacedFeature> createKey(String name) {
		return ResourceKey.create(
			Registries.PLACED_FEATURE, 
			ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, name)
		);
	}

	public static List<PlacementModifier> bogTreePlacement() {
		return ImmutableList.<PlacementModifier>builder()
			.add(PlacementUtils.countExtra(28, 0.1F, 1))
			.add(InSquarePlacement.spread())
			.add(SurfaceWaterDepthFilter.forMaxDepth(2))
			.add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
			.add(BiomeFilter.biome())
			.add(PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get()))
			.build();
	}

	public static List<PlacementModifier> marshTreePlacement() {
		return ImmutableList.<PlacementModifier>builder()
			.add(CountPlacement.of(4))
			.add(RarityFilter.onAverageOnceEvery(5))
			.add(InSquarePlacement.spread())
			.add(SurfaceWaterDepthFilter.forMaxDepth(0))
			.add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
			.add(BiomeFilter.biome())
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
			.add(BiomeFilter.biome())
			.build();
	}
}
