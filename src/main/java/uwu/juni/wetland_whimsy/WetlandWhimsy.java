package uwu.juni.wetland_whimsy;

import com.mojang.logging.LogUtils;

import net.jadenxgamer.elysium_api.api.biome.ElysiumBiomeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import uwu.juni.wetland_whimsy.content.Creative;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.data.Datagen;
import uwu.juni.wetland_whimsy.data.worldgen.WetlandWhimsyBiomesDatagen;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyWoodTypes;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyFoliagePlacers;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyTrunkPlacers;

import org.slf4j.Logger;

@Mod(WetlandWhimsy.MODID)
public class WetlandWhimsy {
	public static final String MODID = "wetland_whimsy";
	public static final Logger LOGGER = LogUtils.getLogger();

	public WetlandWhimsy(FMLJavaModLoadingContext context) {
		IEventBus modEventBus = context.getModEventBus();
		LOGGER.info("Whimsical");

		modEventBus.addListener(Datagen::datagen);
		modEventBus.addListener(Creative::addCreative);

		WetlandWhimsyBlocks.BLOCKS.register(modEventBus);
		WetlandWhimsyItems.ITEMS.register(modEventBus);
		WetlandWhimsyFoliagePlacers.FOLIAGE_PLACERS.register(modEventBus);
		WetlandWhimsyTrunkPlacers.TRUNK_PLACERS.register(modEventBus);

		WetlandWhimsyBlocks.createSignItems(); // Signs are wacky 
		WetlandWhimsyWoodTypes.registerWoodTypes();

		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void nukeTheSwamps(ServerAboutToStartEvent event) {
		var registryAccess = event.getServer().registryAccess();

		ElysiumBiomeRegistry.replaceOverworldBiome(
			Biomes.SWAMP, 
			WetlandWhimsyBiomesDatagen.BOG, 
			1, 
			24, 
			new ResourceLocation(MODID, "nuketheswamps"),
			registryAccess
		);

		ElysiumBiomeRegistry.replaceOverworldBiome(
			WetlandWhimsyBiomesDatagen.BOG, 
			WetlandWhimsyBiomesDatagen.MARSH, 
			0.3, 
			16, 
			new ResourceLocation(MODID, "marshtomp"),
			registryAccess
		);
	}
}
