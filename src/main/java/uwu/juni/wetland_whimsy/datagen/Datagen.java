package uwu.juni.wetland_whimsy.datagen;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableProvider.SubProviderEntry;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.datagen.loot.WetlandWhimsyBlockLoot;
import uwu.juni.wetland_whimsy.datagen.loot.WetlandWhimsyEntityLoot;
import uwu.juni.wetland_whimsy.datagen.loot.WetlandWhimsyMiscLoot;
import uwu.juni.wetland_whimsy.datagen.registries.WetlandWhimsyArmorTrims;
import uwu.juni.wetland_whimsy.datagen.registries.WetlandWhimsyBiomes;
import uwu.juni.wetland_whimsy.datagen.registries.WetlandWhimsyConfiguredFeatures;
import uwu.juni.wetland_whimsy.datagen.registries.WetlandWhimsyJukebox;
import uwu.juni.wetland_whimsy.datagen.registries.WetlandWhimsyPlacedFeatures;
import uwu.juni.wetland_whimsy.datagen.registries.WetlandWhimsyStructurePools;
import uwu.juni.wetland_whimsy.datagen.registries.WetlandWhimsyStructureSets;
import uwu.juni.wetland_whimsy.datagen.registries.WetlandWhimsyStructures;
import uwu.juni.wetland_whimsy.datagen.tags.WetlandWhimsyBiomeTags;
import uwu.juni.wetland_whimsy.datagen.tags.WetlandWhimsyBlockTags;
import uwu.juni.wetland_whimsy.datagen.tags.WetlandWhimsyEntityTags;
import uwu.juni.wetland_whimsy.datagen.tags.WetlandWhimsyItemTags;
import uwu.juni.wetland_whimsy.datagen.tags.WetlandWhimsyStructureTags;

public class Datagen {
	public static void datagen(GatherDataEvent event) {
		var datagen = event.getGenerator();
		var fileHelper = event.getExistingFileHelper();
		var lookupProvider = event.getLookupProvider();
		var output = datagen.getPackOutput();

		datagen.addProvider(event.includeClient(), new WetlandWhimsyBlockModels(output, fileHelper));
		datagen.addProvider(event.includeClient(), new WetlandWhimsyItemModels(output, fileHelper));
		datagen.addProvider(event.includeClient(), new WetlandWhimsyLanguage(output));
		datagen.addProvider(event.includeServer(), new WetlandWhimsyRecipes(output, lookupProvider));

		// Registry-based datagen 
		datagen.addProvider(
			event.includeServer(), 
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

					.add(Registries.JUKEBOX_SONG, WetlandWhimsyJukebox::bootstap)
					.add(Registries.TRIM_PATTERN, WetlandWhimsyArmorTrims::bootstap),
				Collections.singleton(WetlandWhimsy.MODID)
			)
		);

		// Tags
		var blockTags = new WetlandWhimsyBlockTags(output, lookupProvider, fileHelper);
		datagen.addProvider(event.includeServer(), blockTags);		
		datagen.addProvider(
			event.includeServer(), 
			new WetlandWhimsyItemTags(
				output, 
				lookupProvider, 
				blockTags.contentsGetter(), 
				fileHelper
			)
		);

		datagen.addProvider(event.includeServer(), new WetlandWhimsyBiomeTags(output, lookupProvider, fileHelper));
		datagen.addProvider(event.includeServer(), new WetlandWhimsyEntityTags(output, lookupProvider, fileHelper));
		datagen.addProvider(event.includeServer(), new WetlandWhimsyStructureTags(output, lookupProvider, fileHelper));

		// Loot tables
		datagen.addProvider(
			event.includeServer(),
			new LootTableProvider(
				output, 
				Set.of(), 
				List.of(
					new SubProviderEntry(
						WetlandWhimsyBlockLoot::new,
						LootContextParamSets.BLOCK
					),
					new SubProviderEntry(
						WetlandWhimsyMiscLoot::new,
						LootContextParamSets.CHEST
					),
					new SubProviderEntry(
						WetlandWhimsyEntityLoot::new,
						LootContextParamSets.ENTITY
					)
				), 
				event.getLookupProvider()
			)
		);

		// Others
		datagen.addProvider(event.includeServer(), new WetlandWhimsyDatamaps(output, lookupProvider));
	}
}
