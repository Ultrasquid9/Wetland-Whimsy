package uwu.juni.wetland_whimsy.misc;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.lib.apache.commons.tuple.Pair;

import net.mehvahdjukaar.supplementaries.common.utils.FlowerPotHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import vectorwing.farmersdelight.FarmersDelight;

public class Compat {
	public static boolean SUPPLEMENTARIES = ModList.get().isLoaded("supplementaries");
	public static boolean FARMERS_DELIGHT = ModList.get().isLoaded(FarmersDelight.MODID);

	public static void compat(IEventBus bussin) {
		if (SUPPLEMENTARIES)
			bussin.addListener(Compat::supplementaries);
	}

	private static void supplementaries(FMLCommonSetupEvent event) {
		var flowers = ImmutableList.of(
			Pair.of(
				WetlandWhimsyBlocks.CORDGRASS, 
				WetlandWhimsy.rLoc("block/compat/cordgrass_box")
			),
			Pair.of(
				WetlandWhimsyBlocks.PENNYWORT, 
				WetlandWhimsy.rLoc("block/compat/pennywort_box")
			),
			Pair.of(
				WetlandWhimsyBlocks.ARIA_MUSHROOM, 
				WetlandWhimsy.rLoc("block/compat/aria_box")
			)
		);

		for (var flower : flowers) {
			FlowerPotHandler.CUSTOM_MODELS.add(flower.getRight());
			FlowerPotHandler.registerCustomSimpleFlower(
				flower.getLeft().get().asItem(), 
				flower.getRight()
			);	
		}
	}
}
