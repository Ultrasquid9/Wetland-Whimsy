package uwu.juni.wetland_whimsy;

import org.slf4j.Logger;

import com.google.common.collect.ImmutableList;
import com.mojang.logging.LogUtils;
import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;

import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.client.WetlandWhimsyClient;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyAdvancementTriggers;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyPredicates;
import uwu.juni.wetland_whimsy.content.WetlandWhimsySounds;
import uwu.juni.wetland_whimsy.datagen.Datagen;
import uwu.juni.wetland_whimsy.datagen.registries.WetlandWhimsyBiomes;
import uwu.juni.wetland_whimsy.datapacks.Datapacks;
import uwu.juni.wetland_whimsy.misc.Compat;
import uwu.juni.wetland_whimsy.misc.Config;
import uwu.juni.wetland_whimsy.misc.Creative;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyWoodTypes;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyBiomeModifiers;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyFoliagePlacers;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyTreeDecorators;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyTrunkPlacers;

@Mod(WetlandWhimsy.MODID)
public class WetlandWhimsy {
	public static final String MODID = "wetland_whimsy";
	public static final Logger LOGGER = LogUtils.getLogger();

	private static final ImmutableList<DeferredRegister<?>> REGISTRIES = ImmutableList.of(
		WetlandWhimsyAdvancementTriggers.TRIGGERS,
		WetlandWhimsyBlocks.BLOCKS,
		WetlandWhimsyBlockEntities.BLOCK_ENTITY_TYPES,
		WetlandWhimsyEntityTypes.ENTITIES,
		WetlandWhimsyItems.ITEMS,
		WetlandWhimsyParticleTypes.PARTICLE_TYPES,
		WetlandWhimsyPredicates.PREDICATES,
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

		bussin(modEventBus);
		Compat.compat(modEventBus);

		if (dist == Dist.CLIENT)
			WetlandWhimsyClient.clientBussin(modEventBus);

		marshification();
	}

	private void bussin(IEventBus bussin) {
		bussin.addListener(Datapacks::datapackRegistry);
		bussin.addListener(Datagen::datagen);
		bussin.addListener(Creative::new);

		bussin.addListener(WetlandWhimsyEntityTypes::registerAttributes);
		bussin.addListener(WetlandWhimsyEntityTypes::registerSpawnPlacements);
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

	/// Create a ResourceLocation with the "wetland_whimsy" namespace
	public static ResourceLocation rLoc(String loc) {
		return ResourceLocation.fromNamespaceAndPath(
			MODID,
			loc
		);
	}

	/// Create a DeferredRegister with the "wetland_whimsy" namespace
	public static <T> DeferredRegister<T> registry(ResourceKey<Registry<T>> registry) {
		return DeferredRegister.create(registry, MODID);
	}
}
