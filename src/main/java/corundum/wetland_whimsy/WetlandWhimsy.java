package corundum.wetland_whimsy;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import corundum.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import corundum.wetland_whimsy.content.WetlandWhimsyItems;
import corundum.wetland_whimsy.data.Datagen;
import corundum.wetland_whimsy.data.worldgen.WetlandWhimsyTrunkPlacerTypes;
import corundum.wetland_whimsy.tags.WetlandWhimsyWoodTypes;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(WetlandWhimsy.MODID)
public class WetlandWhimsy {
	public static final String MODID = "wetland_whimsy";
	public static final Logger LOGGER = LogUtils.getLogger();

	public WetlandWhimsy(IEventBus modEventBus, ModContainer modContainer) {
		LOGGER.info("Whimsical");

		WetlandWhimsyBlocks.BLOCKS.register(modEventBus);
		WetlandWhimsyItems.ITEMS.register(modEventBus);
		WetlandWhimsyTrunkPlacerTypes.TRUNK_PLACERS.register(modEventBus);

		WetlandWhimsyBlocks.createSignItems(); // Signs are wacky 

		modEventBus.addListener(Datagen::datagen);
		modEventBus.addListener(this::addCreative);

		this.eventSetup(modEventBus);

		WetlandWhimsyWoodTypes.registerWoodTypes();
	}

	public void eventSetup(IEventBus bussin) {
		bussin.addListener(WetlandWhimsyBlockEntities::handleSignEntities);
	}

	private void addCreative(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
			event.accept(WetlandWhimsyBlocks.BALD_CYPRESS_LOG);
	}
}
