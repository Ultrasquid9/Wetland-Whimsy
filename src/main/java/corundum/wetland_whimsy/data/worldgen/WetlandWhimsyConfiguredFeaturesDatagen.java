package corundum.wetland_whimsy.data.worldgen;

import java.util.List;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import corundum.wetland_whimsy.worldgen.bald_cypress.BaldCypressFoliagePlacer;
import corundum.wetland_whimsy.worldgen.bald_cypress.BaldCypressTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;

public class WetlandWhimsyConfiguredFeaturesDatagen {
	public static final ResourceKey<ConfiguredFeature<?, ?>> BALD_CYPRESS_TREE_CONFIGURATION = ResourceKey.create(
		Registries.CONFIGURED_FEATURE, 
		ResourceLocation.fromNamespaceAndPath(WetlandWhimsy.MODID, "bald_cypress_tree")
	);

	public static void bootstap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		context.register(
			BALD_CYPRESS_TREE_CONFIGURATION, 
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
	}
}
