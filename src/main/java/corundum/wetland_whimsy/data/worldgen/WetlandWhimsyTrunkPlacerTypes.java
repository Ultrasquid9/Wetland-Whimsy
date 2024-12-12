package corundum.wetland_whimsy.data.worldgen;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.data.worldgen.bald_cypress.BaldCypressTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WetlandWhimsyTrunkPlacerTypes {
	public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(
		Registries.TRUNK_PLACER_TYPE, 
		WetlandWhimsy.MODID
	);
	
	public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<BaldCypressTrunkPlacer>> BALD_CYPRESS_TRUNK_PLACER = TRUNK_PLACERS.register(
		"bald_cypress_trunk_placer", 
		() -> new TrunkPlacerType<>(BaldCypressTrunkPlacer.CODEC)
	);
}
