package uwu.juni.wetland_whimsy.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
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
			.add(WetlandWhimsyBlocks.CORDGRASS.asItem().builtInRegistryHolder(), new Compostable(.4F), false)
			.add(WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.asItem().builtInRegistryHolder(), new Compostable(.3F), false)
			.add(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM.asItem().builtInRegistryHolder(), new Compostable(.65F), false)
			.add(WetlandWhimsyBlocks.ARIA_MUSHROOM.asItem().builtInRegistryHolder(), new Compostable(.65F), false)
			.add(WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK.asItem().builtInRegistryHolder(), new Compostable(.85F), false)
			.add(WetlandWhimsyBlocks.CORDGRASS_THATCH.asItem().builtInRegistryHolder(), new Compostable(.9F), false)
			.build();

		this.builder(NeoForgeDataMaps.FURNACE_FUELS)
			.add(WetlandWhimsyTags.Items.BALD_CYPRESS_LOGS, new FurnaceFuel(300), false)
			.add(ResourceKey.create(Registries.ITEM, WetlandWhimsy.rLoc("cordgrass_thatch")), new FurnaceFuel(420), false)
			.build();
	}
}
