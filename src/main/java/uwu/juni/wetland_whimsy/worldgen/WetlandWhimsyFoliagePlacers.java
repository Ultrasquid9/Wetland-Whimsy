package uwu.juni.wetland_whimsy.worldgen;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.worldgen.aria_mushroom.AriaMushroomFoliagePlacer;
import uwu.juni.wetland_whimsy.worldgen.bald_cypress.BaldCypressFoliagePlacer;
import uwu.juni.wetland_whimsy.worldgen.bloodcap_mushroom.BloodcapMushroomFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WetlandWhimsyFoliagePlacers {
	public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(
		ForgeRegistries.FOLIAGE_PLACER_TYPES, 
		WetlandWhimsy.MODID
	);

	public static final RegistryObject<FoliagePlacerType<?>> BALD_CYPRESS_FOLIAGE_PLACER = FOLIAGE_PLACERS.register(
		"bald_cypress_foliage_placer", 
		() -> new FoliagePlacerType<>(BaldCypressFoliagePlacer.CODEC)
	);
	public static final RegistryObject<FoliagePlacerType<?>> ARIA_MUSHROOM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register(
		"aria_mushroom_foliage_placer", 
		() -> new FoliagePlacerType<>(AriaMushroomFoliagePlacer.CODEC)
	);
	public static final RegistryObject<FoliagePlacerType<?>> BLOODCAP_MUSHROOM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register(
		"bloodcap_mushroom_foliage_placer", 
		() -> new FoliagePlacerType<>(BloodcapMushroomFoliagePlacer.CODEC)
	);
}
