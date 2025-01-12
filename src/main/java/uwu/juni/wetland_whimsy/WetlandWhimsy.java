package uwu.juni.wetland_whimsy;

import com.mojang.logging.LogUtils;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import uwu.juni.wetland_whimsy.client.WetlandWhimsyParticles;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsySounds;
import uwu.juni.wetland_whimsy.data.Datagen;
import uwu.juni.wetland_whimsy.misc.Creative;
import uwu.juni.wetland_whimsy.misc.WetlandWhimsyConfig;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyWoodTypes;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyBiomeModifiers;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyFoliagePlacers;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyTreeDecorators;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyTrunkPlacers;

import org.slf4j.Logger;

@Mod(WetlandWhimsy.MODID)
public class WetlandWhimsy {
	public static final String MODID = "wetland_whimsy";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

	public static WetlandWhimsyConfig config;

	public WetlandWhimsy() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		LOGGER.info("Whimsical");

		AutoConfig.register(WetlandWhimsyConfig.class, Toml4jConfigSerializer::new);
		config = AutoConfig.getConfigHolder(WetlandWhimsyConfig.class).getConfig();

		modEventBus.addListener(this::commonSetup);
		modEventBus.addListener(Datagen::datagen);
		modEventBus.addListener(Creative::addCreative);
		modEventBus.addListener(WetlandWhimsyBlockEntities::blockEntityRendering);
		modEventBus.addListener(WetlandWhimsyParticles::registerParticleProviders);

		REGISTRY_HELPER.register(modEventBus);
		WetlandWhimsyBlocks.BLOCKS.register(modEventBus);
		WetlandWhimsyBlockEntities.BLOCK_ENTITIES.register(modEventBus);
		WetlandWhimsyItems.ITEMS.register(modEventBus);
		WetlandWhimsyParticleTypes.PARTICLE_TYPES.register(modEventBus);
		WetlandWhimsySounds.SOUNDS.register(modEventBus);
		WetlandWhimsyTreeDecorators.TREE_DECORATORS.register(modEventBus);
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
