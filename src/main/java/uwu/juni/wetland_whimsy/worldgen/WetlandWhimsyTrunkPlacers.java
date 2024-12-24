package uwu.juni.wetland_whimsy.worldgen;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.worldgen.bald_cypress.BaldCypressTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class WetlandWhimsyTrunkPlacers {
	public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(
		Registries.TRUNK_PLACER_TYPE, 
		WetlandWhimsy.MODID
	);
	
	public static final RegistryObject<TrunkPlacerType<BaldCypressTrunkPlacer>> BALD_CYPRESS_TRUNK_PLACER = TRUNK_PLACERS.register(
		"bald_cypress_trunk_placer", 
		() -> new TrunkPlacerType<>(BaldCypressTrunkPlacer.CODEC)
	);
}
