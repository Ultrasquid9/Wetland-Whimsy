package uwu.juni.wetland_whimsy.worldgen;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.worldgen.bald_cypress.BaldCypressFoliagePlacer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WetlandWhimsyFoliagePlacers {
	public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(
		BuiltInRegistries.FOLIAGE_PLACER_TYPE, 
		WetlandWhimsy.MODID
	);

	public static final DeferredHolder<FoliagePlacerType<?>, ?> BALD_CYPRESS_FOLIAGE_PLACER = FOLIAGE_PLACERS.register(
		"bald_cypress_foliage_placer", 
		() -> new FoliagePlacerType<>(BaldCypressFoliagePlacer.CODEC)
	);
}
