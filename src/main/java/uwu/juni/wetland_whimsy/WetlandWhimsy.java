package uwu.juni.wetland_whimsy;

import org.slf4j.Logger;

import com.google.common.collect.ImmutableList;
import com.mojang.logging.LogUtils;
import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyAdvancementTriggers;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyFeatures;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyPotPatterns;
import uwu.juni.wetland_whimsy.content.WetlandWhimsySounds;
import uwu.juni.wetland_whimsy.datagen.registries.WetlandWhimsyBiomes;
import uwu.juni.wetland_whimsy.misc.WetlandWhimsyCompat;
import uwu.juni.wetland_whimsy.misc.WetlandWhimsyConfig;
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
		WetlandWhimsyFeatures.FEATURES,
		WetlandWhimsyItems.ITEMS,
		WetlandWhimsyParticleTypes.PARTICLE_TYPES,
		WetlandWhimsyPotPatterns.PATTERNS,
		WetlandWhimsySounds.SOUNDS,
		WetlandWhimsyFoliagePlacers.FOLIAGE_PLACERS,
		WetlandWhimsyTrunkPlacers.TRUNK_PLACERS,
		WetlandWhimsyTreeDecorators.TREE_DECORATORS,
		WetlandWhimsyBiomeModifiers.BIOME_MODIFIERS
	);

	public WetlandWhimsy(IEventBus bussin, ModContainer mc, Dist dist) {
		LOGGER.info("Whimsical");

		config(mc, dist);

		for (var registry : REGISTRIES) 
			registry.register(bussin);

		WetlandWhimsyBlocks.createSignItems(); // Signs are wacky 
		WetlandWhimsyWoodTypes.registerWoodTypes();

		bussin.addListener(WetlandWhimsy::commonSetup);
	}

	void config(ModContainer mc, Dist dist) {
		mc.registerConfig(ModConfig.Type.COMMON, WetlandWhimsyConfig.SPEC);
		if (dist == Dist.CLIENT)
			mc.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
	}

	static void commonSetup(FMLCommonSetupEvent a) {
		WetlandWhimsyPotPatterns.initPotPatterns();
		WetlandWhimsyCompat.compat();
		marshification();
	}

	static void marshification() {
		if (!WetlandWhimsyConfig.GENERATE_MARSH.get())
			return;

		BiomePlacement.addSubOverworld(
			Biomes.SWAMP, 
			WetlandWhimsyBiomes.MARSH, 
			CriterionBuilder.deviationMax(BiomeParameterTargets.CONTINENTALNESS, -0.44f)
		);
	}

	/// Create a ResourceLocation with the "wetland_whimsy" namespace
	public static ResourceLocation rLoc(String loc) {
		return ResourceLocation.fromNamespaceAndPath(MODID, loc);
	}

	/// Create a DeferredRegister with the "wetland_whimsy" namespace
	public static <T> DeferredRegister<T> registry(ResourceKey<Registry<T>> registry) {
		return DeferredRegister.create(registry, MODID);
	}
}
