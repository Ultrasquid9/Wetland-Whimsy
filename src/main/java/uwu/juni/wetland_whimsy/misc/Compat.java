package uwu.juni.wetland_whimsy.misc;

import net.minecraftforge.fml.ModList;

public class Compat {
	public static boolean BRAZIER = ModList.get().isLoaded("brazier");
	public static boolean CNC = ModList.get().isLoaded("caverns_and_chasms");
	public static boolean CONNECTOR = ModList.get().isLoaded("connectormod");
	public static boolean ENDERGETIC = ModList.get().isLoaded("endergetic");
	public static boolean FARMERS_DELIGHT = ModList.get().isLoaded("farmersdelight");
	public static boolean SUPPLEMENTARIES = ModList.get().isLoaded("supplementaries");

	public static boolean shouldChangeBrazierName() {
		return BRAZIER || CNC;
	}

	public static boolean isCabinetSafe() {
		return FARMERS_DELIGHT && !CONNECTOR; // Sinytra Connector causes a crash for some reason
	}
}
