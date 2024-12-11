package corundum.wetland_whimsy;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import corundum.wetland_whimsy.content.WetlandWhimsyItems;
import corundum.wetland_whimsy.data.WetlandWhimsyBlockModelDatagen;
import corundum.wetland_whimsy.data.WetlandWhimsyItemModelDatagen;
import corundum.wetland_whimsy.data.WetlandWhimsyLanguageDatagen;
import corundum.wetland_whimsy.data.sub_providers.WetlandWhimsyBlockLootDatagen;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableProvider.SubProviderEntry;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(WetlandWhimsy.MODID)
public class WetlandWhimsy {
	public static final String MODID = "wetland_whimsy";
	private static final Logger LOGGER = LogUtils.getLogger();

	public WetlandWhimsy(IEventBus modEventBus, ModContainer modContainer) {
		LOGGER.info("Whimsical");

		WetlandWhimsyBlocks.BLOCKS.register(modEventBus);
		WetlandWhimsyItems.ITEMS.register(modEventBus);

		modEventBus.addListener(this::datagen);
		modEventBus.addListener(this::addCreative);
	}

	private void addCreative(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
			event.accept(WetlandWhimsyBlocks.BALD_CYPRESS_LOG_ITEM);
	}

	private void datagen(final GatherDataEvent event) {
		DataGenerator datagen = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();
		PackOutput output = datagen.getPackOutput();

		datagen.addProvider(event.includeClient(), new WetlandWhimsyBlockModelDatagen(output, fileHelper));
		datagen.addProvider(event.includeClient(), new WetlandWhimsyItemModelDatagen(output, fileHelper));
		datagen.addProvider(event.includeClient(), new WetlandWhimsyLanguageDatagen(output));

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
