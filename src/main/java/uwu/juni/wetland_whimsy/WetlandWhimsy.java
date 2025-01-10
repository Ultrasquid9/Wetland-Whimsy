package uwu.juni.wetland_whimsy;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import uwu.juni.wetland_whimsy.client.WetlandWhimsyParticles;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsySounds;
import uwu.juni.wetland_whimsy.data.Datagen;
import uwu.juni.wetland_whimsy.data.registries.WetlandWhimsyBiomes;
import uwu.juni.wetland_whimsy.misc.Config;
import uwu.juni.wetland_whimsy.misc.Creative;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyWoodTypes;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyBiomeModifiers;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyFoliagePlacers;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyTreeDecorators;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyTrunkPlacers;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import eu.midnightdust.lib.config.MidnightConfig;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;

@Mod(WetlandWhimsy.MODID)
public class WetlandWhimsy {
	public static final String MODID = "wetland_whimsy";
	public static final Logger LOGGER = LogUtils.getLogger();

	public WetlandWhimsy(IEventBus modEventBus, ModContainer modContainer, Dist dist) {
		LOGGER.info("Whimsical");

		MidnightConfig.init(MODID, Config.class);

		modEventBus.addListener(Datagen::datagen);
		modEventBus.addListener(Creative::addCreative);

		WetlandWhimsyBlocks.BLOCKS.register(modEventBus);
		WetlandWhimsyBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
		WetlandWhimsyItems.ITEMS.register(modEventBus);
		WetlandWhimsyParticleTypes.PARTICLE_TYPES.register(modEventBus);
		WetlandWhimsySounds.SOUNDS.register(modEventBus);
		WetlandWhimsyFoliagePlacers.FOLIAGE_PLACERS.register(modEventBus);
		WetlandWhimsyTrunkPlacers.TRUNK_PLACERS.register(modEventBus);
		WetlandWhimsyTreeDecorators.TREE_DECORATORS.register(modEventBus);
		WetlandWhimsyBiomeModifiers.BIOME_MODIFIERS.register(modEventBus);

		WetlandWhimsyBlocks.createSignItems(); // Signs are wacky 
		WetlandWhimsyWoodTypes.registerWoodTypes();

		this.eventSetup(modEventBus);

		if (dist == Dist.CLIENT)
			modEventBus.addListener(WetlandWhimsyParticles::registerParticleProviders);

		marshification();
	}

	public void eventSetup(IEventBus bussin) {
		bussin.addListener(WetlandWhimsyBlockEntities::handleBlockEntities);
	}

	private void marshification() {
		if (!Config.generateMarsh)
			return;

		BiomePlacement.addSubOverworld(
			Biomes.SWAMP, 
			WetlandWhimsyBiomes.MARSH, 
			CriterionBuilder.deviationMax(BiomeParameterTargets.CONTINENTALNESS, -0.44f)
		);
	}
}
