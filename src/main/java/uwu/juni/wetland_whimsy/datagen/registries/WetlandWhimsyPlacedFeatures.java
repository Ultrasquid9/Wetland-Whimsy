package uwu.juni.wetland_whimsy.datagen.registries;

import java.util.List;

import com.google.common.collect.ImmutableList;

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
	public static final ResourceKey<PlacedFeature> MUD_POOL_SWAMP = createKey("mud_pool_swamp");
	public static final ResourceKey<PlacedFeature> MUD_POOL_MARSH = createKey("mud_pool_marsh");

	public static final ResourceKey<PlacedFeature> CORDGRASS_PATCH = createKey("cordgrass_patch");
	public static final ResourceKey<PlacedFeature> PENNYWORT_PATCH = createKey("pennywort_patch");
	public static final ResourceKey<PlacedFeature> BLOODCAP_PATCH = createKey("bloodcap_patch");
	public static final ResourceKey<PlacedFeature> BLOODCAP_PATCH_SURFACE = createKey("bloodcap_patch_surface");
	public static final ResourceKey<PlacedFeature> HUGE_ARIA = createKey("huge_aria");
	public static final ResourceKey<PlacedFeature> MUD_DISK = createKey("mud_disk");
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
				ImmutableList.<PlacementModifier>builder()
					.add(CountPlacement.of(UniformInt.of(20, 50)))
					.add(PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT)
					.add(InSquarePlacement.spread())
					.add(SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -64))
					.add(BiomeFilter.biome())
					.build()
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
				ImmutableList.<PlacementModifier>builder()
					.add(RarityFilter.onAverageOnceEvery(3))
					.add(InSquarePlacement.spread())
					.add(SurfaceWaterDepthFilter.forMaxDepth(0))
					.add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
					.add(BiomeFilter.biome())
					.add(PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get()))
					.build()
			)
		);

		context.register(
			LIMESTONE_DISK_SWAMP, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.LIMESTONE_DISK), 
				ImmutableList.<PlacementModifier>builder()
					.add(InSquarePlacement.spread())
					.add(PlacementUtils.HEIGHTMAP_TOP_SOLID)
					.add(BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)))
					.add(BiomeFilter.biome())
					.build()
			)
		);
		context.register(
			LIMESTONE_DISK_MARSH, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.LIMESTONE_DISK), 
				ImmutableList.<PlacementModifier>builder()
					.add(InSquarePlacement.spread())
					.add(PlacementUtils.HEIGHTMAP_TOP_SOLID)
					.add(BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)))
					.add(BiomeFilter.biome())
					.build()
			)
		);
		context.register(
			MUD_DISK, 
			new PlacedFeature(
				configuredFeatures.getOrThrow(WetlandWhimsyConfiguredFeatures.MUD_DISK), 
				ImmutableList.<PlacementModifier>builder()
					.add(PlacementUtils.countExtra(2, 0.5F, 1))
					.add(InSquarePlacement.spread())
					.add(SurfaceWaterDepthFilter.forMaxDepth(2))
					.add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
					.add(BiomeFilter.biome())
					.build()
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
				ImmutableList.<PlacementModifier>builder()
					.add(RarityFilter.onAverageOnceEvery(4))
					.add(InSquarePlacement.spread())
					.add(PlacementUtils.HEIGHTMAP)
					.add(BiomeFilter.biome())
					.build()
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
		return ImmutableList.<PlacementModifier>builder()
			.add(PlacementUtils.countExtra(2, 0.5F, 3))
			.add(InSquarePlacement.spread())
			.add(SurfaceWaterDepthFilter.forMaxDepth(2))
			.add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
			.add(BiomeFilter.biome())
			.add(PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get()))
			.build();
	}

	public static List<PlacementModifier> marshTreePlacement() {
		return ImmutableList.<PlacementModifier>builder()
			.add(CountPlacement.of(2))
			.add(RarityFilter.onAverageOnceEvery(7))
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

	public static List<PlacementModifier> marshFoliagePlacement() {
		return ImmutableList.<PlacementModifier>builder()
			.add(PlacementUtils.countExtra(17, 0.5F, 6))
			.add(InSquarePlacement.spread())
			.add(SurfaceWaterDepthFilter.forMaxDepth(2))
			.add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
			.add(BiomeFilter.biome())
			.add(PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get()))
			.build();
	}
}
