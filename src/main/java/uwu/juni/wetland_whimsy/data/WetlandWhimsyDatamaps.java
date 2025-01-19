package uwu.juni.wetland_whimsy.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

public class WetlandWhimsyDatamaps extends DataMapProvider {
	public WetlandWhimsyDatamaps(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void gather() {
		this.builder(NeoForgeDataMaps.COMPOSTABLES)
			.add(WetlandWhimsyBlocks.PENNYWORT.asItem().builtInRegistryHolder(), new Compostable(.15F), false)
			.add(WetlandWhimsyBlocks.CORDGRASS.asItem().builtInRegistryHolder(), new Compostable(.6F), false)
			.add(WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.asItem().builtInRegistryHolder(), new Compostable(.3F), false)
			.add(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.asItem().builtInRegistryHolder(), new Compostable(.3F), false)
			.build();

		this.builder(NeoForgeDataMaps.FURNACE_FUELS)
			.add(WetlandWhimsyTags.Items.BALD_CYPRESS_LOGS, new FurnaceFuel(300), false)
			.build();
	}
}
