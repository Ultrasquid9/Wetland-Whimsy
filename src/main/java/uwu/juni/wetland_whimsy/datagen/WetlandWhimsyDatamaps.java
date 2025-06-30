package uwu.juni.wetland_whimsy.datagen;

import java.util.concurrent.CompletableFuture;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

@ParametersAreNonnullByDefault
public class WetlandWhimsyDatamaps extends DataMapProvider {
	public WetlandWhimsyDatamaps(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

	@Override
	protected void gather(HolderLookup.Provider provider) {
		builder(NeoForgeDataMaps.COMPOSTABLES)
			.add(createKey(WetlandWhimsyBlocks.PENNYWORT), new Compostable(.15F), false)
			.add(createKey(WetlandWhimsyBlocks.CORDGRASS), new Compostable(.4F), false)
			.add(createKey(WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES), new Compostable(.3F), false)
			.add(createKey(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM), new Compostable(.65F), false)
			.add(createKey(WetlandWhimsyBlocks.ARIA_MUSHROOM), new Compostable(.65F), false)
			.add(createKey(WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK), new Compostable(.85F), false)
			.add(createKey(WetlandWhimsyBlocks.ARIA_SPORES), new Compostable(.2F), false)
			.add(createKey(WetlandWhimsyBlocks.CORDGRASS_THATCH), new Compostable(.9F), false)
			.add(createKey(WetlandWhimsyItems.CARROT_SEEDS), new Compostable(.3F), false)
			.build();

		builder(NeoForgeDataMaps.FURNACE_FUELS)
			.add(WetlandWhimsyTags.Items.BALD_CYPRESS_LOGS, new FurnaceFuel(300), false)
			.add(createKey(WetlandWhimsyBlocks.CORDGRASS_THATCH), new FurnaceFuel(420), false)
			.build();
	}

	ResourceKey<Item> createKey(DeferredHolder<?, ?> item) {
		return ResourceKey.create(
			Registries.ITEM, 
			item.getId()
		);
	}
}
