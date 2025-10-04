package uwu.juni.wetland_whimsy.misc;

import java.util.Map;

import net.mehvahdjukaar.supplementaries.Supplementaries;
import net.mehvahdjukaar.supplementaries.common.utils.FlowerPotHandler;
import net.neoforged.fml.ModList;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import vectorwing.farmersdelight.FarmersDelight;

public class WetlandWhimsyCompat {
	public static final boolean SUPPLEMENTARIES = ModList.get().isLoaded(Supplementaries.MOD_ID);
	public static final boolean FARMERS_DELIGHT = ModList.get().isLoaded(FarmersDelight.MODID);

	public static void compat() {
		if (SUPPLEMENTARIES) {
			supplementaries();
		}
	}

	private static void supplementaries() {
		final var flowers = Map.of(
			WetlandWhimsyBlocks.CORDGRASS, 
			WetlandWhimsy.rLoc("block/compat/cordgrass_box"),
			WetlandWhimsyBlocks.PENNYWORT, 
			WetlandWhimsy.rLoc("block/compat/pennywort_box"),
			WetlandWhimsyBlocks.ARIA_MUSHROOM, 
			WetlandWhimsy.rLoc("block/compat/aria_box")
		);

		for (var flower : flowers.entrySet()) {
			FlowerPotHandler.CUSTOM_MODELS.add(flower.getValue());
			FlowerPotHandler.registerCustomSimpleFlower(
				flower.getKey().get().asItem(), 
				flower.getValue()
			);	
		}
	}
}
