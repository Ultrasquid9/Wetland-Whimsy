package corundum.wetland_whimsy;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import corundum.wetland_whimsy.content.Creative;
import corundum.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import corundum.wetland_whimsy.content.WetlandWhimsyItems;
import corundum.wetland_whimsy.data.Datagen;
import corundum.wetland_whimsy.data.worldgen.WetlandWhimsyBiomesDatagen;
import corundum.wetland_whimsy.tags.WetlandWhimsyWoodTypes;
import corundum.wetland_whimsy.worldgen.WetlandWhimsyFoliagePlacers;
import corundum.wetland_whimsy.worldgen.WetlandWhimsyTrunkPlacers;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;

@Mod(WetlandWhimsy.MODID)
public class WetlandWhimsy {
	public static final String MODID = "wetland_whimsy";
	public static final Logger LOGGER = LogUtils.getLogger();

	public WetlandWhimsy(IEventBus modEventBus, ModContainer modContainer) {
		LOGGER.info("Whimsical");

		modEventBus.addListener(Datagen::datagen);
		modEventBus.addListener(Creative::addCreative);

		WetlandWhimsyBlocks.BLOCKS.register(modEventBus);
		WetlandWhimsyItems.ITEMS.register(modEventBus);
		WetlandWhimsyFoliagePlacers.FOLIAGE_PLACERS.register(modEventBus);
		WetlandWhimsyTrunkPlacers.TRUNK_PLACERS.register(modEventBus);

		WetlandWhimsyBlocks.createSignItems(); // Signs are wacky 
		WetlandWhimsyWoodTypes.registerWoodTypes();

		this.eventSetup(modEventBus);

		nukeTheSwamps();
	}

	public void eventSetup(IEventBus bussin) {
		bussin.addListener(WetlandWhimsyBlockEntities::handleBlockEntities);
	}

	private void nukeTheSwamps() {
		BiomePlacement.replaceOverworld(Biomes.SWAMP, WetlandWhimsyBiomesDatagen.BOG);

		BiomePlacement.addSubOverworld(
			WetlandWhimsyBiomesDatagen.BOG, 
			WetlandWhimsyBiomesDatagen.MARSH, 
			CriterionBuilder.deviationMax(BiomeParameterTargets.CONTINENTALNESS, -0.44f)
		);
	}
}
