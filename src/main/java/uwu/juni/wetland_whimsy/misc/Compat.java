package uwu.juni.wetland_whimsy.misc;

import net.minecraftforge.fml.ModList;

public class Compat {
	public static boolean ALEXSCAVES = ModList.get().isLoaded("alexscaves");
	public static boolean BRAZIER = ModList.get().isLoaded("brazier");
	public static boolean CNC = ModList.get().isLoaded("caverns_and_chasms");
	public static boolean CREATE = ModList.get().isLoaded("create");
	public static boolean ENDERGETIC = ModList.get().isLoaded("endergetic");
	public static boolean SUPPLEMENTARIES = ModList.get().isLoaded("supplementaries");
	public static boolean QUARK = ModList.get().isLoaded("quark");

	public static boolean shouldChangeLimestoneName() {
		return ALEXSCAVES || CREATE || QUARK;
	}
}
