package uwu.juni.wetland_whimsy.data;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.data.registries.*;
import uwu.juni.wetland_whimsy.data.sub_providers.WetlandWhimsyBlockLootDatagen;
import uwu.juni.wetland_whimsy.data.sub_providers.WetlandWhimsyVaultLootDatagen;
import uwu.juni.wetland_whimsy.data.tags.WetlandWhimsyBiomeTagsDatagen;
import uwu.juni.wetland_whimsy.data.tags.WetlandWhimsyBlockTagsDatagen;
import uwu.juni.wetland_whimsy.data.tags.WetlandWhimsyItemTagsDatagen;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableProvider.SubProviderEntry;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

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

		// Registry-based datagen 
		datagen.addProvider(
			event.includeClient(), 
			new DatapackBuiltinEntriesProvider(
				output, 
				lookupProvider, 
				new RegistrySetBuilder()
					.add(Registries.CONFIGURED_FEATURE, WetlandWhimsyConfiguredFeatures::bootstap)
					.add(Registries.PLACED_FEATURE, WetlandWhimsyPlacedFeatures::bootstap)
					.add(Registries.BIOME, WetlandWhimsyBiomes::bootstap)

					.add(Registries.STRUCTURE_SET, WetlandWhimsyStructureSets::bootstap)
					.add(Registries.STRUCTURE, WetlandWhimsyStructures::bootstap)
					.add(Registries.TEMPLATE_POOL, WetlandWhimsyStructurePools::bootstap)

					.add(Registries.JUKEBOX_SONG, WetlandWhimsyJukebox::bootstap),
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

		// Others
		datagen.addProvider(event.includeServer(), new WetlandWhimsyDatamapDatagen(output, lookupProvider));
	}
}
