package corundum.wetland_whimsy.data;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.data.sub_providers.WetlandWhimsyBlockLootDatagen;
import corundum.wetland_whimsy.data.sub_providers.WetlandWhimsyVaultLootDatagen;
import corundum.wetland_whimsy.data.tags.WetlandWhimsyBlockTagsDatagen;
import corundum.wetland_whimsy.data.tags.WetlandWhimsyItemTagsDatagen;
import corundum.wetland_whimsy.data.worldgen.WetlandWhimsyConfiguredFeaturesDatagen;
import corundum.wetland_whimsy.data.worldgen.WetlandsWhimsyBiomesDatagen;
import corundum.wetland_whimsy.data.worldgen.WetlandsWhimsyPlacedFeaturesDatagen;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableProvider.SubProviderEntry;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class Datagen {
	public static void datagen(final GatherDataEvent event) {
		DataGenerator datagen = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		PackOutput output = datagen.getPackOutput();

		datagen.addProvider(event.includeClient(), new WetlandWhimsyBlockModelDatagen(output, fileHelper));
		datagen.addProvider(event.includeClient(), new WetlandWhimsyItemModelDatagen(output, fileHelper));
		datagen.addProvider(event.includeClient(), new WetlandWhimsyLanguageDatagen(output));

		datagen.addProvider(event.includeServer(), new WetlandWhimsyRecipeDatagen(output, lookupProvider));

		// Tags
		var blockTags = new WetlandWhimsyBlockTagsDatagen(output, lookupProvider, fileHelper);

		datagen.addProvider(event.includeClient(), blockTags);
		
		datagen.addProvider(
			event.includeClient(), 
			new WetlandWhimsyItemTagsDatagen(
				output, 
				lookupProvider, 
				blockTags.contentsGetter(), 
				fileHelper
			)
		);

		// Worldgen
		datagen.addProvider(
			event.includeClient(), 
			new DatapackBuiltinEntriesProvider(
				output, 
				lookupProvider, 
				new RegistrySetBuilder()
					.add(Registries.CONFIGURED_FEATURE, WetlandWhimsyConfiguredFeaturesDatagen::bootstap)
					.add(Registries.PLACED_FEATURE, WetlandsWhimsyPlacedFeaturesDatagen::bootstap)
					.add(Registries.BIOME, WetlandsWhimsyBiomesDatagen::bootstap),
				Collections.singleton(WetlandWhimsy.MODID)
			)
		);

		// Loot tables
		datagen.addProvider(
			event.includeServer(),
			new LootTableProvider(
				output, 
				Set.of(), 
				List.of(
					new SubProviderEntry(
						WetlandWhimsyBlockLootDatagen::new,
						LootContextParamSets.BLOCK
					),
					new SubProviderEntry(
						WetlandWhimsyVaultLootDatagen::new,
						LootContextParamSets.CHEST
					)
				), 
				event.getLookupProvider()
			)
		);
	}
}
