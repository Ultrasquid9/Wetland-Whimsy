package uwu.juni.wetland_whimsy.content;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.BaldCypressBoatEntity;
import uwu.juni.wetland_whimsy.content.items.BaldCypressBoatItem;
import uwu.juni.wetland_whimsy.content.items.DaggerItem;
import uwu.juni.wetland_whimsy.data.registries.WetlandWhimsyJukebox;
import net.minecraft.core.component.DataComponents;
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

	public static final DeferredItem<BaldCypressBoatItem> BALD_CYPRESS_BOAT = ITEMS.register(
		"bald_cypress_boat",
		() -> new BaldCypressBoatItem(
			false, 
			BaldCypressBoatEntity.Type.BALD_CYPRESS, 
			new Item.Properties()
		)
	);
	public static final DeferredItem<BaldCypressBoatItem> BALD_CYPRESS_CHEST_BOAT = ITEMS.register(
		"bald_cypress_chest_boat",
		() -> new BaldCypressBoatItem(
			true, 
			BaldCypressBoatEntity.Type.BALD_CYPRESS, 
			new Item.Properties()
		)
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
	public static final DeferredItem<Item> BLEMISH_ROD = ITEMS.registerSimpleItem(
		"blemish_rod"
	);
	public static final DeferredItem<Item> RUSTED_ARTIFACT = ITEMS.registerSimpleItem(
		"rusted_artifact",
		new Item.Properties().rarity(Rarity.RARE)
	);

	public static final DeferredItem<Item> BLEMISH_SPAWN_EGG = ITEMS.register(
		"blemish_spawn_egg",
		() -> new DeferredSpawnEggItem(
			WetlandWhimsyEntityTypes.BLEMISH, 
			0x4c6559, 
			0xbbc9b3, 
			new Item.Properties()
		)
	);
	public static final DeferredItem<Item> SWAMP_SPIDER_SPAWN_EGG = ITEMS.register(
		"swamp_spider_spawn_egg",
		() -> new DeferredSpawnEggItem(
			WetlandWhimsyEntityTypes.SWAMP_SPIDER, 
			0x584a45, 
			0x9a2f6c, 
			new Item.Properties()
		)
	);

	public static final DeferredItem<Item> DAGGER = ITEMS.register(
		"dagger",
		() -> new DaggerItem(
			new Item.Properties()
				.rarity(Rarity.EPIC)
				.durability(560)
				.component(DataComponents.TOOL, DaggerItem.createToolProperties())
				.attributes(DaggerItem.createAttributes(5F, -3.2F))
		)
	);
}
