package uwu.juni.wetland_whimsy.content;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.data.registries.WetlandWhimsyJukebox;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SmithingTemplateItem;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WetlandWhimsyItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WetlandWhimsy.MODID);

	public static final DeferredItem<Item> PENNYWORT_SALAD = ITEMS.registerSimpleItem(
		"pennywort_salad", 
		new Item.Properties()
			.food(
				new FoodProperties.Builder()
					.alwaysEdible()
					.nutrition(2)
					.saturationModifier(4f)
					.usingConvertsTo(Items.BOWL)
					.build()
			)
			.stacksTo(1)
	);

	public static final DeferredItem<Item> DOTS_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register(
		"dots_armor_trim_smithing_template",
		() -> SmithingTemplateItem.createArmorTrimTemplate(
			WetlandWhimsy.rLoc("dots")
		)
	);

	public static final DeferredItem<Item> DISC = ITEMS.registerSimpleItem(
		"disc",
		new Item.Properties()
			.stacksTo(1)
			.rarity(Rarity.RARE)
			.jukeboxPlayable(WetlandWhimsyJukebox.NUKE_THE_SWAMPS)
	);

	public static final DeferredItem<Item> ANCIENT_COIN = ITEMS.registerSimpleItem(
		"ancient_coin"
	);

	public static final DeferredItem<Item> SLUDGE_SPAWN_EGG = ITEMS.register(
		"sludge_spawn_egg",
		() -> new DeferredSpawnEggItem(
			WetlandWhimsyEntityTypes.SLUDGE, 
			0x4c6559, 
			0xbbc9b3, 
			new Item.Properties()
		)
	);
}
