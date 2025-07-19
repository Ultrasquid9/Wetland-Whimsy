package uwu.juni.wetland_whimsy.content;

import java.util.HashMap;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class WetlandWhimsyPotPatterns {
	public static final DeferredRegister<String> PATTERNS = DeferredRegister.create(
		Registries.DECORATED_POT_PATTERNS,
		WetlandWhimsy.MODID
	);

	public static final ResourceKey<String> GROWTH = pattern("growth_pottery_pattern");
	public static final ResourceKey<String> SEALED = pattern("sealed_pottery_pattern");

	public static HashMap<Item, ResourceKey<String>> WW_POT_PATTERNS = new HashMap<>();

	private static ResourceKey<String> pattern(String name) {
		PATTERNS.register(name, () -> name);

		return ResourceKey.create(Registries.DECORATED_POT_PATTERNS, WetlandWhimsy.rLoc(name));
	}

	public static void initPotPatterns() {
		WW_POT_PATTERNS.put(WetlandWhimsyItems.GROWTH_POTTERY_SHERD.get(), GROWTH);
		WW_POT_PATTERNS.put(WetlandWhimsyItems.SEALED_POTTERY_SHERD.get(), SEALED);
	}
}
