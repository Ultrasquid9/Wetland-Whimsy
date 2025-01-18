package uwu.juni.wetland_whimsy;

import org.slf4j.Logger;

import com.google.common.collect.ImmutableList;
import com.mojang.logging.LogUtils;

import uwu.juni.wetland_whimsy.client.WetlandWhimsyClient;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
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
import net.neoforged.neoforge.registries.DeferredRegister;
import eu.midnightdust.lib.config.MidnightConfig;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;

@Mod(WetlandWhimsy.MODID)
public class WetlandWhimsy {
	public static final String MODID = "wetland_whimsy";
	public static final Logger LOGGER = LogUtils.getLogger();

	private static final ImmutableList<DeferredRegister<?>> REGISTRIES = ImmutableList.of(
		WetlandWhimsyBlocks.BLOCKS,
		WetlandWhimsyBlockEntities.BLOCK_ENTITY_TYPES,
		WetlandWhimsyEntityTypes.ENTITIES,
		WetlandWhimsyItems.ITEMS,
		WetlandWhimsyParticleTypes.PARTICLE_TYPES,
		WetlandWhimsySounds.SOUNDS,
		WetlandWhimsyFoliagePlacers.FOLIAGE_PLACERS,
		WetlandWhimsyTrunkPlacers.TRUNK_PLACERS,
		WetlandWhimsyTreeDecorators.TREE_DECORATORS,
		WetlandWhimsyBiomeModifiers.BIOME_MODIFIERS
	);

	public WetlandWhimsy(IEventBus modEventBus, ModContainer modContainer, Dist dist) {
		LOGGER.info("Whimsical");

		MidnightConfig.init(MODID, Config.class);

		for (var registry : REGISTRIES) 
			registry.register(modEventBus);

		WetlandWhimsyBlocks.createSignItems(); // Signs are wacky 
		WetlandWhimsyWoodTypes.registerWoodTypes();

		this.bussin(modEventBus);

		if (dist == Dist.CLIENT)
			WetlandWhimsyClient.clientBussin(modEventBus);

		marshification();
	}

	public void bussin(IEventBus bussin) {
		bussin.addListener(Datagen::datagen);
		bussin.addListener(Creative::addCreative);

		bussin.addListener(WetlandWhimsyEntityTypes::registerAttributes);
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
