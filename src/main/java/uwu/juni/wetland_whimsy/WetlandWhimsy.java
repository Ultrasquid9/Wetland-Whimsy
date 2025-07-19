package uwu.juni.wetland_whimsy;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.mehvahdjukaar.supplementaries.common.utils.FlowerPotHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsySounds;
import uwu.juni.wetland_whimsy.data.Datagen;
import uwu.juni.wetland_whimsy.misc.Compat;
import uwu.juni.wetland_whimsy.misc.Creative;
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

	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

	private static final DeferredRegister<?>[] REGISTRIES = {
		WetlandWhimsyBlocks.BLOCKS,
		WetlandWhimsyBlockEntities.BLOCK_ENTITIES,
		WetlandWhimsyEntityTypes.ENTITIES,
		WetlandWhimsyItems.ITEMS,
		WetlandWhimsyParticleTypes.PARTICLE_TYPES,
		WetlandWhimsySounds.SOUNDS,
		WetlandWhimsyTreeDecorators.TREE_DECORATORS,
		WetlandWhimsyFoliagePlacers.FOLIAGE_PLACERS,
		WetlandWhimsyTrunkPlacers.TRUNK_PLACERS,
		WetlandWhimsyBiomeModifiers.BIOME_MODIFIERS
	};

	public static WetlandWhimsyConfig config;

	@SuppressWarnings({ "deprecated", "removal" })
	public WetlandWhimsy() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		LOGGER.info("Whimsical");

		AutoConfig.register(WetlandWhimsyConfig.class, Toml4jConfigSerializer::new);
		config = AutoConfig.getConfigHolder(WetlandWhimsyConfig.class).getConfig();

		modEventBus.addListener(this::commonSetup);
		modEventBus.addListener(Datagen::datagen);
		modEventBus.addListener(Creative::addCreative);
		modEventBus.addListener(WetlandWhimsyBlockEntities::blockEntityRendering);

		REGISTRY_HELPER.register(modEventBus);
		for (var registry : REGISTRIES)
			registry.register(modEventBus);

		WetlandWhimsyBlocks.createSignItems(); // Signs are wacky 
		WetlandWhimsyWoodTypes.registerWoodTypes();

		ModLoadingContext.get().registerExtensionPoint(
			ConfigScreenHandler.ConfigScreenFactory.class, 
			() -> {
				return new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> {
					return AutoConfig.getConfigScreen(WetlandWhimsyConfig.class, screen).get();
				});
			}
		);
	}

	public void commonSetup(FMLCommonSetupEvent event) {
		compat();

		event.enqueueWork(() -> { this.compost(); });
	}

	private void compost() {
		ComposterBlock.COMPOSTABLES.put(
			WetlandWhimsyBlocks.PENNYWORT.get().asItem(), 
			.15F
		);
		ComposterBlock.COMPOSTABLES.put(
			WetlandWhimsyBlocks.CORDGRASS.get().asItem(), 
			.4F
		);
		ComposterBlock.COMPOSTABLES.put(
			WetlandWhimsyBlocks.CORDGRASS_THATCH.get().asItem(), 
			.9F
		);
		ComposterBlock.COMPOSTABLES.put(
			WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get().asItem(), 
			.3F
		);
		ComposterBlock.COMPOSTABLES.put(
			WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get().asItem(), 
			.3F
		);
		ComposterBlock.COMPOSTABLES.put(
			WetlandWhimsyBlocks.BLOODCAP_MUSHROOM.get().asItem(), 
			.65F
		);
		ComposterBlock.COMPOSTABLES.put(
			WetlandWhimsyBlocks.ARIA_MUSHROOM.get().asItem(), 
			.65F
		);
		ComposterBlock.COMPOSTABLES.put(
			WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK.get().asItem(), 
			.85F
		);
		ComposterBlock.COMPOSTABLES.put(
			WetlandWhimsyBlocks.BLOODCAP_MUSHROOM_BLOCK.get().asItem(), 
			.85F
		);
		ComposterBlock.COMPOSTABLES.put(
			WetlandWhimsyBlocks.ARIA_SPORES.get().asItem(), 
			.2F
		);
	}

	private void compat() {
		if (Compat.SUPPLEMENTARIES) {
			var cordgrass_rl = rLoc("block/compat/cordgrass_box");
			var pennywort_rl = rLoc("block/compat/pennywort_box");
			var aria_rl = rLoc("block/compat/aria_box");
			
			FlowerPotHandler.CUSTOM_MODELS.add(cordgrass_rl);
			FlowerPotHandler.registerCustomSimpleFlower(
				WetlandWhimsyBlocks.CORDGRASS.get().asItem(),
				cordgrass_rl
			);
			FlowerPotHandler.CUSTOM_MODELS.add(pennywort_rl);
			FlowerPotHandler.registerCustomSimpleFlower(
				WetlandWhimsyBlocks.PENNYWORT.get().asItem(), 
				pennywort_rl
			);
			FlowerPotHandler.CUSTOM_MODELS.add(aria_rl);
			FlowerPotHandler.registerCustomSimpleFlower(
				WetlandWhimsyBlocks.ARIA_MUSHROOM.get().asItem(), 
				aria_rl
			);
		}
	}

	@SuppressWarnings("removal")
	public static ResourceLocation rLoc(String resource) {
		return new ResourceLocation(MODID, resource);
	}
}
