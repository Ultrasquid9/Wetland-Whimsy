package uwu.juni.wetland_whimsy.data;

import java.util.Collections;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.data.tags.WetlandWhimsyBiomeTagsDatagen;
import uwu.juni.wetland_whimsy.data.tags.WetlandWhimsyBlockTagsDatagen;
import uwu.juni.wetland_whimsy.data.tags.WetlandWhimsyItemTagsDatagen;
import uwu.juni.wetland_whimsy.data.worldgen.WetlandWhimsyConfiguredFeaturesDatagen;
import uwu.juni.wetland_whimsy.data.worldgen.WetlandWhimsyBiomesDatagen;
import uwu.juni.wetland_whimsy.data.worldgen.WetlandWhimsyPlacedFeaturesDatagen;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;

public class Datagen {
	public static void datagen(GatherDataEvent event) {
		var datagen = event.getGenerator();
		var fileHelper = event.getExistingFileHelper();
		var lookupProvider = event.getLookupProvider();
		var output = datagen.getPackOutput();

		datagen.addProvider(event.includeClient(), new WetlandWhimsyBlockModelDatagen(output, fileHelper));
		datagen.addProvider(event.includeClient(), new WetlandWhimsyItemModelDatagen(output, fileHelper));
		datagen.addProvider(event.includeClient(), new WetlandWhimsyLanguageDatagen(output));
		datagen.addProvider(event.includeServer(), new WetlandWhimsyRecipeDatagen(output, lookupProvider));

		// Worldgen
		datagen.addProvider(
			event.includeClient(), 
			new DatapackBuiltinEntriesProvider(
				output, 
				lookupProvider, 
				new RegistrySetBuilder()
					.add(Registries.CONFIGURED_FEATURE, WetlandWhimsyConfiguredFeaturesDatagen::bootstap)
					.add(Registries.PLACED_FEATURE, WetlandWhimsyPlacedFeaturesDatagen::bootstap)
					.add(Registries.BIOME, WetlandWhimsyBiomesDatagen::bootstap),
				Collections.singleton(WetlandWhimsy.MODID)
			)
		);

		// Tags
		var blockTags = new WetlandWhimsyBlockTagsDatagen(output, lookupProvider, fileHelper);
		datagen.addProvider(event.includeServer(), blockTags);		
		datagen.addProvider(
			event.includeServer(), 
			new WetlandWhimsyItemTagsDatagen(
				output, 
				lookupProvider, 
				blockTags.contentsGetter(), 
				fileHelper
			)
		);

		datagen.addProvider(event.includeServer(), new WetlandWhimsyBiomeTagsDatagen(output, lookupProvider, fileHelper));

		// Loot tables
/* 		datagen.addProvider(
			event.includeServer(),
			new LootTableProvider(
				output, 
				Set.of(), 
				List.of(
					new LootTableProvider.SubProviderEntry(
						WetlandWhimsyBlockLootDatagen::new,
						LootContextParamSets.BLOCK
					),
					new LootTableProvider.SubProviderEntry(
						WetlandWhimsyVaultLootDatagen::new,
						LootContextParamSets.CHEST
					)
				), 
				event.getLookupProvider()
			)
		);*/
	}
}
