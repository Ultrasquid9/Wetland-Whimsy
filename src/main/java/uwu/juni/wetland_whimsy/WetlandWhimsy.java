package uwu.juni.wetland_whimsy;

import com.mojang.logging.LogUtils;

import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsySounds;
import uwu.juni.wetland_whimsy.data.Datagen;
import uwu.juni.wetland_whimsy.misc.Creative;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyWoodTypes;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyBiomeModifiers;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyFoliagePlacers;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyTrunkPlacers;

import org.slf4j.Logger;

@Mod(WetlandWhimsy.MODID)
public class WetlandWhimsy {
	public static final String MODID = "wetland_whimsy";
	public static final Logger LOGGER = LogUtils.getLogger();

	public WetlandWhimsy() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		LOGGER.info("Whimsical");

		modEventBus.addListener(this::commonSetup);
		modEventBus.addListener(Datagen::datagen);
		modEventBus.addListener(Creative::addCreative);
		modEventBus.addListener(WetlandWhimsyBlockEntities::blockEntityRendering);

		WetlandWhimsyBlocks.BLOCKS.register(modEventBus);
		WetlandWhimsyBlockEntities.BLOCK_ENTITIES.register(modEventBus);
		WetlandWhimsyItems.ITEMS.register(modEventBus);
		WetlandWhimsySounds.SOUNDS.register(modEventBus);
		WetlandWhimsyFoliagePlacers.FOLIAGE_PLACERS.register(modEventBus);
		WetlandWhimsyTrunkPlacers.TRUNK_PLACERS.register(modEventBus);
		WetlandWhimsyBiomeModifiers.BIOME_MODIFIERS.register(modEventBus);

		WetlandWhimsyBlocks.createSignItems(); // Signs are wacky 
		WetlandWhimsyWoodTypes.registerWoodTypes();
	}

	public void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			this.compost();
		});
	}

	private void compost() {
		ComposterBlock.COMPOSTABLES.put(
			WetlandWhimsyBlocks.PENNYWORT.get().asItem(), 
			.15F
		);
		ComposterBlock.COMPOSTABLES.put(
			WetlandWhimsyBlocks.CORDGRASS.get().asItem(), 
			.6F
		);
		ComposterBlock.COMPOSTABLES.put(
			WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get().asItem(), 
			.3F
		);
		ComposterBlock.COMPOSTABLES.put(
			WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get().asItem(), 
			.3F
		);
	}
}
