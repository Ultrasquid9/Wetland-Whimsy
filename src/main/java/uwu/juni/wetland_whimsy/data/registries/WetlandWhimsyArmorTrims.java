package uwu.juni.wetland_whimsy.data.registries;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.armortrim.TrimPattern;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;

public class WetlandWhimsyArmorTrims {
	public static final ResourceKey<TrimPattern> DOTS = ResourceKey.create(
		Registries.TRIM_PATTERN, 
		WetlandWhimsy.rLoc("dots")
	);

	public static void bootstap(BootstrapContext<TrimPattern> context) {
		context.register(
			DOTS, 
			new TrimPattern(
				DOTS.location(), 
				WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE, 
				Component.translatable(Util.makeDescriptionId("trim_pattern", DOTS.location())), 
				false
			)
		);
	}
}
