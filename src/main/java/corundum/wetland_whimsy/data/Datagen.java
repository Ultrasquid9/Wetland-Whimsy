package corundum.wetland_whimsy.data;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import corundum.wetland_whimsy.data.sub_providers.WetlandWhimsyBlockLootDatagen;
import corundum.wetland_whimsy.data.tags.WetlandWhimsyBlockTagsDatagen;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableProvider.SubProviderEntry;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
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

		datagen.addProvider(event.includeClient(), new WetlandWhimsyBlockTagsDatagen(output, lookupProvider, fileHelper));

		datagen.addProvider(
			event.includeServer(),
			new LootTableProvider(
				output, 
				Set.of(), 
				List.of(
					new SubProviderEntry(
						WetlandWhimsyBlockLootDatagen::new,
						LootContextParamSets.BLOCK
					)
				), 
				event.getLookupProvider()
			)
		);
	}
}
