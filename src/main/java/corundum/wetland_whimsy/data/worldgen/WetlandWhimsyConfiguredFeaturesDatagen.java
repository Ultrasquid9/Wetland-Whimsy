package corundum.wetland_whimsy.data.worldgen;

import java.util.List;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import corundum.wetland_whimsy.worldgen.bald_cypress.BaldCypressFoliagePlacer;
import corundum.wetland_whimsy.worldgen.bald_cypress.BaldCypressTrunkPlacer;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;

public class WetlandWhimsyConfiguredFeaturesDatagen {
	public static final ResourceKey<ConfiguredFeature<?, ?>> BALD_CYPRESS_TREE = createKey("bald_cypress_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CORDGRASS_PATCH = createKey("cordgrass_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PENNYWORT_PATCH = createKey("pennywort_patch");

	public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_SWAMP = createKey("trees_swamp");


	public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
		return ResourceKey.create(
			Registries.CONFIGURED_FEATURE, 
			ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, name)
		);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void bootstap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

		context.register(
			BALD_CYPRESS_TREE, 
			new ConfiguredFeature<>(
				Feature.TREE, 
				new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get()), 
					new BaldCypressTrunkPlacer(6, 2, 2),
					BlockStateProvider.simple(WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get()), 
					new BaldCypressFoliagePlacer(ConstantInt.of(4), ConstantInt.of(6)),
					new TwoLayersFeatureSize(0, 2, 4)
				)
				.decorators(List.of(new LeaveVineDecorator(0.2f), new TrunkVineDecorator()))
				.ignoreVines()
				.build()
			)
		);

		context.register(
			CORDGRASS_PATCH, 
			new ConfiguredFeature(
				Feature.FLOWER, 
				new RandomPatchConfiguration(
					64, 
					5, 
					3, 
					PlacementUtils.onlyWhenEmpty(
						Feature.SIMPLE_BLOCK, 
						new SimpleBlockConfiguration(
							BlockStateProvider.simple(WetlandWhimsyBlocks.CORDGRASS.get())
						)
					)
				)
			)
		);
		context.register(
			PENNYWORT_PATCH, 
			new ConfiguredFeature(
				Feature.FLOWER, 
				new RandomPatchConfiguration(
					32, 
					7, 
					3, 
					PlacementUtils.onlyWhenEmpty(
						Feature.SIMPLE_BLOCK, 
						new SimpleBlockConfiguration(
							BlockStateProvider.simple(WetlandWhimsyBlocks.PENNYWORT.get())
						)
					)
				)
			)
		);

		final Holder<ConfiguredFeature<?, ?>> HUGE_RED_MUSHROOM = configuredFeatures.getOrThrow(TreeFeatures.HUGE_RED_MUSHROOM);
		final Holder<ConfiguredFeature<?, ?>> HUGE_BROWN_MUSHROOM = configuredFeatures.getOrThrow(TreeFeatures.HUGE_BROWN_MUSHROOM);

		context.register(
			TREES_SWAMP, 
			new ConfiguredFeature(
				Feature.RANDOM_SELECTOR, 
				new RandomFeatureConfiguration(
					List.of(
						new WeightedPlacedFeature(
							PlacementUtils.inlinePlaced(
								configuredFeatures.getOrThrow(BALD_CYPRESS_TREE), 
								PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
							), 
							0.33F
						),
						new WeightedPlacedFeature(
							PlacementUtils.inlinePlaced(
								configuredFeatures.getOrThrow(CORDGRASS_PATCH) 
							), 
							0.1F
						),
						new WeightedPlacedFeature(
							PlacementUtils.inlinePlaced(
								configuredFeatures.getOrThrow(PENNYWORT_PATCH) 
							), 
							0.1F
						),
						new WeightedPlacedFeature(
							PlacementUtils.inlinePlaced(
								HUGE_RED_MUSHROOM, 
								PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
							), 
							0.05F
						),
						new WeightedPlacedFeature(
							PlacementUtils.inlinePlaced(
								HUGE_BROWN_MUSHROOM, 
								PlacementUtils.filteredByBlockSurvival(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get())
							), 
							0.1F
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
