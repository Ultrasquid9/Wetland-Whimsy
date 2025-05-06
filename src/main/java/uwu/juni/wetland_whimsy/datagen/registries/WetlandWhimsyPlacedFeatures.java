package uwu.juni.wetland_whimsy.datagen.registries;

import java.util.List;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceRelativeThresholdFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import net.minecraft.world.level.material.Fluids;

public class WetlandWhimsyPlacedFeatures {
	public static final ResourceKey<PlacedFeature> TREES_BOG = createKey("trees_bog");
	public static final ResourceKey<PlacedFeature> TREES_MARSH = createKey("trees_marsh");
	public static final ResourceKey<PlacedFeature> LIMESTONE_DISK_SWAMP = createKey("limestone_disk_swamp");
	public static final ResourceKey<PlacedFeature> LIMESTONE_DISK_MARSH = createKey("limestone_disk_marsh");
	public static final ResourceKey<PlacedFeature> MUD_PATCH_SWAMP = createKey("mud_patch_swamp");
	public static final ResourceKey<PlacedFeature> MUD_PATCH_MARSH = createKey("mud_patch_marsh");
	public static final ResourceKey<PlacedFeature> MUD_POOL_SWAMP = createKey("mud_pool_swamp");
	public static final ResourceKey<PlacedFeature> MUD_POOL_MARSH = createKey("mud_pool_marsh");

	public static final ResourceKey<PlacedFeature> CORDGRASS_PATCH = createKey("cordgrass_patch");
	public static final ResourceKey<PlacedFeature> PENNYWORT_PATCH = createKey("pennywort_patch");
	public static final ResourceKey<PlacedFeature> BLOODCAP_PATCH = createKey("bloodcap_patch");
	public static final ResourceKey<PlacedFeature> BLOODCAP_PATCH_SURFACE = createKey("bloodcap_patch_surface");
	public static final ResourceKey<PlacedFeature> HUGE_ARIA = createKey("huge_aria");
	public static final ResourceKey<PlacedFeature> MUD_BLOB = createKey("mud_blob");
	public static final ResourceKey<PlacedFeature> SUPER_THICK_CORDGRASS_PATCH = createKey("super_thick_cordgrass_patch");
	public static final ResourceKey<PlacedFeature> FERN_CLONE_CAUSE_FUCK_THE_FEATURE_CYCLE = createKey("fern_clone");

	public static void bootstap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

		context.register(
			TREES_BOG, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.TREES_BOG), 
				bogTreePlacement()
			)
		);

		context.register(
			TREES_MARSH, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.TREES_MARSH), 
				marshTreePlacement()
			)
		);

		context.register(
			CORDGRASS_PATCH, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.CORDGRASS_PATCH), 
				foliagePlacement()
			)
		);
		context.register(
			PENNYWORT_PATCH, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.PENNYWORT_PATCH), 
				foliagePlacement()
			)
		);
		context.register(
			BLOODCAP_PATCH, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.BLOODCAP_PATCH), 
				List.of(
					CountPlacement.of(UniformInt.of(20, 50)),
					PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
					InSquarePlacement.spread(),
					SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -64),
					BiomeFilter.biome()
				)
			)
		);
		context.register(
			BLOODCAP_PATCH_SURFACE, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.BLOODCAP_PATCH), 
				foliagePlacement()
			)
		);
		context.register(
			HUGE_ARIA, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.HUGE_ARIA_MUSHROOM), 
				List.of(
					RarityFilter.onAverageOnceEvery(3),
					InSquarePlacement.spread(),
					SurfaceWaterDepthFilter.forMaxDepth(0),
					PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
					BiomeFilter.biome(),
					PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
				)
			)
		);

		context.register(
			LIMESTONE_DISK_SWAMP, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.LIMESTONE_DISK), 
				List.of(
					InSquarePlacement.spread(),
					PlacementUtils.HEIGHTMAP_TOP_SOLID,
					BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)),
					BiomeFilter.biome()
				)
			)
		);
		context.register(
			LIMESTONE_DISK_MARSH, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.LIMESTONE_DISK), 
				List.of(
					InSquarePlacement.spread(),
					PlacementUtils.HEIGHTMAP_TOP_SOLID,
					BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)),
					BiomeFilter.biome()
				)
			)
		);
		context.register(
			MUD_BLOB, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.MUD_BLOB), 
				List.of(
					PlacementUtils.countExtra(2, 0.5F, 1),
					InSquarePlacement.spread(),
					SurfaceWaterDepthFilter.forMaxDepth(2),
					PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
					BiomeFilter.biome()
				)
			)
		);
		context.register(
			MUD_PATCH_SWAMP, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.MUD_PATCH), 
				foliagePlacement()
			)
		);
		context.register(
			MUD_PATCH_MARSH, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.MUD_PATCH), 
				foliagePlacement()
			)
		);
		context.register(
			MUD_POOL_SWAMP, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.MUD_POOL), 
				foliagePlacement()
			)
		);
		context.register(
			MUD_POOL_MARSH, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.MUD_POOL), 
				foliagePlacement()
			)
		);
		context.register(
			SUPER_THICK_CORDGRASS_PATCH, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.CORDGRASS_PATCH), 
				marshFoliagePlacement()
			)
		);
		context.register(
			FERN_CLONE_CAUSE_FUCK_THE_FEATURE_CYCLE, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(VegetationFeatures.PATCH_LARGE_FERN), 
				List.of(
					RarityFilter.onAverageOnceEvery(4),
					InSquarePlacement.spread(),
					PlacementUtils.HEIGHTMAP,
					BiomeFilter.biome()
				)
			)
		);
	}

	public static ResourceKey<PlacedFeature> createKey(String name) {
		return ResourceKey.create(
			Registries.PLACED_FEATURE, 
			WetlandWhimsy.rLoc(name)
		);
	}

	public static List<PlacementModifier> bogTreePlacement() {
		return List.of(
			PlacementUtils.countExtra(2, 0.5F, 3),
			InSquarePlacement.spread(),
			SurfaceWaterDepthFilter.forMaxDepth(2),
			PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
			BiomeFilter.biome(),
			PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
		);
	}

	public static List<PlacementModifier> marshTreePlacement() {
		return List.of(
			CountPlacement.of(2),
			RarityFilter.onAverageOnceEvery(7),
			InSquarePlacement.spread(),
			SurfaceWaterDepthFilter.forMaxDepth(0),
			PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
			BiomeFilter.biome(),
			PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
		);
	}

	public static List<PlacementModifier> foliagePlacement() {
		return List.of(
			PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
			CountPlacement.of(3),
			RarityFilter.onAverageOnceEvery(3),
			InSquarePlacement.spread(),
			PlacementUtils.filteredByBlockSurvival(Blocks.SHORT_GRASS),
			BiomeFilter.biome()
		);
	}

	public static List<PlacementModifier> marshFoliagePlacement() {
		return List.of(
			PlacementUtils.countExtra(17, 0.5F, 6),
			InSquarePlacement.spread(),
			SurfaceWaterDepthFilter.forMaxDepth(2),
			PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
			BiomeFilter.biome(),
			PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
		);
	}
}
