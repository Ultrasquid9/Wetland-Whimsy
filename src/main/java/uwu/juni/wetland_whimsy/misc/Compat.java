package uwu.juni.wetland_whimsy.misc;

import net.minecraftforge.fml.ModList;

public class Compat {
	public static boolean SUPPLEMENTARIES = ModList.get().isLoaded("supplementaries");
	public static boolean ENDERGETIC = ModList.get().isLoaded("endergetic");
	public static boolean BRAZIER = ModList.get().isLoaded("brazier");
}
