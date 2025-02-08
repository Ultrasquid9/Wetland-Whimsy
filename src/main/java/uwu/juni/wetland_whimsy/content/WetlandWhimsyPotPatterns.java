package uwu.juni.wetland_whimsy.content;

import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class WetlandWhimsyPotPatterns {
	public static final DeferredRegister<DecoratedPotPattern> PATTERNS = WetlandWhimsy.registry(
		Registries.DECORATED_POT_PATTERN
	);

	public static final Supplier<DecoratedPotPattern> GROWTH = pattern("growth_pottery_pattern");
	public static final Supplier<DecoratedPotPattern> SEALED = pattern("sealed_pottery_pattern");

	public static HashMap<Item, ResourceKey<DecoratedPotPattern>> WW_POT_PATTERNS = new HashMap<>();

	private static Supplier<DecoratedPotPattern> pattern(String name) {
		return PATTERNS.register(
			name, 
			() -> Registry.register(
				BuiltInRegistries.DECORATED_POT_PATTERN, 
				name, 
				new DecoratedPotPattern(WetlandWhimsy.rLoc(name))
			)
		);
	}

	public static void initPotPatterns() {
		BiConsumer<Supplier<DecoratedPotPattern>, ItemLike> addItem = (patterm, item) -> {
			WW_POT_PATTERNS.put(
				item.asItem(), 
				ResourceKey.create(
					Registries.DECORATED_POT_PATTERN, 
					patterm.get().assetId()
				)
			);
		}; 

		addItem.accept(GROWTH, WetlandWhimsyItems.GROWTH_POTTERY_SHERD);
		addItem.accept(SEALED, WetlandWhimsyItems.SEALED_POTTERY_SHERD);
	}
}
