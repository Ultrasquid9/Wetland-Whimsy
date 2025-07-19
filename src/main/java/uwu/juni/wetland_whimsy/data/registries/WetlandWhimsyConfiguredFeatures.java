package uwu.juni.wetland_whimsy.data.registries;

import java.util.List;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.blocks.PennywortBlock;
import uwu.juni.wetland_whimsy.worldgen.aria_mushroom.AriaMushroomFoliagePlacer;
import uwu.juni.wetland_whimsy.worldgen.aria_mushroom.AriaMushroomTreeDecorator;
import uwu.juni.wetland_whimsy.worldgen.bald_cypress.BaldCypressFoliagePlacer;
import uwu.juni.wetland_whimsy.worldgen.bald_cypress.BaldCypressTrunkPlacer;
import uwu.juni.wetland_whimsy.worldgen.bloodcap_mushroom.BloodcapMushroomFoliagePlacer;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class WetlandWhimsyConfiguredFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> BALD_CYPRESS_TREE = createKey("bald_cypress_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_ARIA_MUSHROOM = createKey("huge_aria_mushroom");
	public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_BLOODCAP_MUSHROOM = createKey("huge_bloodcap_mushroom");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BLOODCAP_PATCH = createKey("bloodcap_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CORDGRASS_PATCH = createKey("cordgrass_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PENNYWORT_PATCH = createKey("pennywort_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PENNYWORT_PATCH_SMALL = createKey("pennywort_single");
	public static final ResourceKey<ConfiguredFeature<?, ?>> LIMESTONE_DISK = createKey("limestone_disk");
	public static final ResourceKey<ConfiguredFeature<?, ?>> MUD_DISK = createKey("mud_disk");
	public static final ResourceKey<ConfiguredFeature<?, ?>> MUD_POOL = createKey("mud_pool");
	public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_BOG = createKey("trees_bog");
	public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_MARSH = createKey("trees_marsh");


	public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
		return ResourceKey.create(
			Registries.CONFIGURED_FEATURE, 
			WetlandWhimsy.rLoc(name)
		);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void bootstap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

		SimpleWeightedRandomList.Builder<BlockState> pennywortRandomState = new SimpleWeightedRandomList.Builder<>();
		for (int i = 1; i <= 4; i++) {
			for (var dir : Direction.Plane.HORIZONTAL) {
				pennywortRandomState.add(
					WetlandWhimsyBlocks.PENNYWORT.get()
						.defaultBlockState()
						.setValue(PennywortBlock.FACING, dir)
						.setValue(PennywortBlock.PENNYWORT_COUNT, i),
					1
				);
			}
		}

		context.register(
			BALD_CYPRESS_TREE, 
			new ConfiguredFeature(
				Feature.TREE, 
				new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get()), 
					new BaldCypressTrunkPlacer(6, 2, 2),
					BlockStateProvider.simple(WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get()), 
					new BaldCypressFoliagePlacer(ConstantInt.of(4), ConstantInt.of(6)),
					new TwoLayersFeatureSize(1, 0, 1)
				)
				.decorators(List.of(
					new LeaveVineDecorator(0.2f), 
					new TrunkVineDecorator(),
					new AriaMushroomTreeDecorator(0.15F)
				))
				.ignoreVines()
				.build()
			)
		);

		final var MUSHROOM_STEM = Blocks.MUSHROOM_STEM
			.defaultBlockState()
			.setValue(HugeMushroomBlock.UP, Boolean.valueOf(false))
			.setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false));

		context.register(
			HUGE_BLOODCAP_MUSHROOM, 
			new ConfiguredFeature<>(
				Feature.TREE, 
				new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(MUSHROOM_STEM),
					new StraightTrunkPlacer(3, 0, 1),

					BlockStateProvider.simple(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM_BLOCK.get()), 
					new BloodcapMushroomFoliagePlacer(ConstantInt.of(4), ConstantInt.of(6)),

					new TwoLayersFeatureSize(1, 0, 1)
				)
				.ignoreVines()
				.build()
			)
		);
		context.register(
			HUGE_ARIA_MUSHROOM, 
			new ConfiguredFeature(
				Feature.TREE, 
				new TreeConfiguration.TreeConfigurationBuilder(

					BlockStateProvider.simple(MUSHROOM_STEM),
					new StraightTrunkPlacer(4, 1, 2),

					BlockStateProvider.simple(WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK.get()), 
					new AriaMushroomFoliagePlacer(ConstantInt.of(4), ConstantInt.of(6)),

					new TwoLayersFeatureSize(1, 0, 1)
				)
				.decorators(List.of(
					new AriaMushroomTreeDecorator(1F)
				))
				.ignoreVines()
				.build()
			)
		);

		context.register(
			BLOODCAP_PATCH,
			new ConfiguredFeature(
				Feature.RANDOM_PATCH, 
				new RandomPatchConfiguration(
					8, 
					4, 
					3, 
					PlacementUtils.onlyWhenEmpty(
						Feature.SIMPLE_BLOCK, 
						new SimpleBlockConfiguration(BlockStateProvider.simple(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM.get()))
					)
				)
			)
		);
		context.register(
			CORDGRASS_PATCH, 
			new ConfiguredFeature(
				Feature.FLOWER, 
				new RandomPatchConfiguration(
					64, 
					4, 
					3, 
					PlacementUtils.onlyWhenEmpty(
						Feature.SIMPLE_BLOCK, 
						new SimpleBlockConfiguration(BlockStateProvider.simple(WetlandWhimsyBlocks.CORDGRASS.get()))
					)
				)
			)
		);
		context.register(
			PENNYWORT_PATCH, 
			new ConfiguredFeature(
				Feature.FLOWER, 
				new RandomPatchConfiguration(
					64, 
					10, 
					4, 
					PlacementUtils.onlyWhenEmpty(
						Feature.SIMPLE_BLOCK, 
						new SimpleBlockConfiguration(new WeightedStateProvider(pennywortRandomState))
					)
				)
			)
		);
		context.register(
			PENNYWORT_PATCH_SMALL,
			new ConfiguredFeature(
				Feature.FLOWER, 
				new RandomPatchConfiguration(
					4, 
					3, 
					4, 
					PlacementUtils.onlyWhenEmpty(
						Feature.SIMPLE_BLOCK, 
						new SimpleBlockConfiguration(new WeightedStateProvider(pennywortRandomState))
					)
				)
			)
		);

		context.register(
			LIMESTONE_DISK, 
			new ConfiguredFeature(
				Feature.DISK, 
				new DiskConfiguration(
					RuleBasedBlockStateProvider.simple(WetlandWhimsyBlocks.LIMESTONE.get()), 
					BlockPredicate.matchesBlocks(List.of(
						Blocks.DIRT, 
						Blocks.STONE,
						Blocks.DIORITE,
						Blocks.GRANITE,
						Blocks.ANDESITE,
						WetlandWhimsyBlocks.LIMESTONE.get()
					)), 
					UniformInt.of(3, 6), 
					4
				)
			)
		);
		context.register(
			MUD_DISK, 
			new ConfiguredFeature(
				Feature.DISK, 
				new DiskConfiguration(
					RuleBasedBlockStateProvider.simple(Blocks.MUD), 
					BlockPredicate.matchesTag(BlockTags.LUSH_GROUND_REPLACEABLE), 
					UniformInt.of(5, 7), 
					3
				)
			)
		);
		context.register(
			MUD_POOL, 
			new ConfiguredFeature(
				Feature.WATERLOGGED_VEGETATION_PATCH, 
				new VegetationPatchConfiguration(
					BlockTags.LUSH_GROUND_REPLACEABLE, 
					BlockStateProvider.simple(Blocks.MUD), 
					PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(PENNYWORT_PATCH_SMALL)), 
					CaveSurface.FLOOR, 
					ConstantInt.of(3), 
					0.8f, 
					5, 
					0.1f, 
					UniformInt.of(5, 8), 
					0.7f
				)
			)
		);

		final Holder<ConfiguredFeature<?, ?>> HUGE_RED_MUSHROOM = configuredFeatures.getOrThrow(TreeFeatures.HUGE_RED_MUSHROOM);
		final Holder<ConfiguredFeature<?, ?>> HUGE_BROWN_MUSHROOM = configuredFeatures.getOrThrow(TreeFeatures.HUGE_BROWN_MUSHROOM);
		final Holder<ConfiguredFeature<?, ?>> BIRCH = configuredFeatures.getOrThrow(TreeFeatures.BIRCH);
		final Holder<ConfiguredFeature<?, ?>> SPRUCE = configuredFeatures.getOrThrow(TreeFeatures.SPRUCE);

		context.register(
			TREES_BOG, 
			new ConfiguredFeature(
				Feature.RANDOM_SELECTOR, 
				new RandomFeatureConfiguration(
					List.of(
						new WeightedPlacedFeature(
							PlacementUtils.inlinePlaced(
								configuredFeatures.getOrThrow(BALD_CYPRESS_TREE), 
								PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
							), 
							0.4F
						),
						new WeightedPlacedFeature(
							PlacementUtils.inlinePlaced(
								configuredFeatures.getOrThrow(HUGE_ARIA_MUSHROOM), 
								PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
							), 
							0.03F
						),
						new WeightedPlacedFeature(
							PlacementUtils.inlinePlaced(
								HUGE_RED_MUSHROOM, 
								PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
							), 
							0.06F
						),
						new WeightedPlacedFeature(
							PlacementUtils.inlinePlaced(
								HUGE_BROWN_MUSHROOM, 
								PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
							), 
							0.15F
						)
					),
					PlacementUtils.inlinePlaced(
						configuredFeatures.getOrThrow(BALD_CYPRESS_TREE), 
						PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
					)
				)
			)
		);


		context.register(
			TREES_MARSH, 
			new ConfiguredFeature(
				Feature.RANDOM_SELECTOR, 
				new RandomFeatureConfiguration(
					List.of(
						new WeightedPlacedFeature(
							PlacementUtils.inlinePlaced(
								configuredFeatures.getOrThrow(BALD_CYPRESS_TREE), 
								PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
							), 
							0.30F
						),
						new WeightedPlacedFeature(
							PlacementUtils.inlinePlaced(
								BIRCH, 
								PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
							), 
							0.50F
						),
						new WeightedPlacedFeature(
							PlacementUtils.inlinePlaced(
								SPRUCE, 
								PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
							), 
							0.50F
						)
					),
					PlacementUtils.inlinePlaced(
						configuredFeatures.getOrThrow(BALD_CYPRESS_TREE), 
						PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
					)
				)
			)
		);
	}
}
