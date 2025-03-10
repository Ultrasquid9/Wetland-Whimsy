package uwu.juni.wetland_whimsy.content;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SmithingTemplateItem;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.BaldCypressBoatEntity;
import uwu.juni.wetland_whimsy.content.items.AK47Item;
import uwu.juni.wetland_whimsy.content.items.BaldCypressBoatItem;
import uwu.juni.wetland_whimsy.content.items.DaggerItem;
import uwu.juni.wetland_whimsy.content.items.SludgeChargeItem;
import uwu.juni.wetland_whimsy.datagen.registries.WetlandWhimsyJukebox;

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

	public static final DeferredItem<Item> GROWTH_POTTERY_SHERD = item("growth_pottery_sherd");
	public static final DeferredItem<Item> SEALED_POTTERY_SHERD = item("sealed_pottery_sherd");

	public static final DeferredItem<Item> ANCIENT_COIN = item("ancient_coin");
	public static final DeferredItem<Item> BLEMISH_ROD = item("blemish_rod");
	public static final DeferredItem<SludgeChargeItem> SLUDGE_CHARGE = ITEMS.register(
		"sludge_charge",
		() -> new SludgeChargeItem(new Item.Properties())
	);
	public static final DeferredItem<Item> RUSTED_ARTIFACT = ITEMS.registerSimpleItem(
		"rusted_artifact",
		new Item.Properties()
			.stacksTo(1)
			.rarity(Rarity.RARE)
	);

	public static final DeferredItem<Item> CRANE_SPAWN_EGG = ITEMS.register(
		"crane_spawn_egg",
		() -> new DeferredSpawnEggItem(
			WetlandWhimsyEntityTypes.CRANE, 
			0xECEBE5, 
			0xB72D18, 
			new Item.Properties()
		)
	);
	public static final DeferredItem<Item> BLEMISH_SPAWN_EGG = ITEMS.register(
		"blemish_spawn_egg",
		() -> new DeferredSpawnEggItem(
			WetlandWhimsyEntityTypes.BLEMISH, 
			0x565E3D, 
			0xBECB8F, 
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
	public static final DeferredItem<Item> FLORIDA_MAN_SPAWN_EGG = ITEMS.register(
		"florida_man_spawn_egg",
		() -> new DeferredSpawnEggItem(
			WetlandWhimsyEntityTypes.FLORIDA_MAN, 
			0xFF0000, 
			0xFF00FF, 
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
				.attributes(DaggerItem.createAttributes(2F, -3.2F))
		)
	);

	public static final DeferredItem<Item> BULLET = item("bullet");
	public static final DeferredItem<Item> AK47 = ITEMS.register(
		"ak47",
		() -> new AK47Item(
			new Item.Properties()
				.rarity(Rarity.RARE)
				.durability(1000)
				.stacksTo(1)
		)
	);

	public static final DeferredItem<Item> BASIC_INCENSE = item("basic_incense");
	public static final DeferredItem<Item> BOILING_INCENSE = item("boiling_incense");
	public static final DeferredItem<Item> BRINE_INCENSE = item("brine_incense");
	public static final DeferredItem<Item> ROT_INCENSE = item("rot_incense");
	public static final DeferredItem<Item> WEBBED_INCENSE = item("webbed_incense");

	public static DeferredItem<Item> item(String name) {
		return ITEMS.registerSimpleItem(name);
	}
}
