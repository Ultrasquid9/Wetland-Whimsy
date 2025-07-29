package uwu.juni.wetland_whimsy.content;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class WetlandWhimsyItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WetlandWhimsy.MODID);
	public static final ItemSubRegistryHelper HELPER = WetlandWhimsy.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> PENNYWORT_SALAD = ITEMS.register(
		"pennywort_salad", 
		() -> new Item(
				new Item.Properties()
					.food(
						new FoodProperties.Builder()
							.alwaysEat()
							.saturationMod(4f)
							.nutrition(2)
							.build()
					)
					.stacksTo(1))
	);

	public static final RegistryObject<Item> GROWTH_POTTERY_SHERD = simpleItem("growth_pottery_sherd");
	public static final RegistryObject<Item> SEALED_POTTERY_SHERD = simpleItem("sealed_pottery_sherd");

	public static final RegistryObject<Item> DOTS_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register(
		"dots_armor_trim_smithing_template",
		() -> SmithingTemplateItem.createArmorTrimTemplate(WetlandWhimsy.rLoc("dots"))
	);

	public static final RegistryObject<Item> NUKE_THE_SWAMPS_MUSIC_DISC = ITEMS.register(
		"nuke_the_swamps_music_disc",
		() -> new RecordItem(
			6, 
			WetlandWhimsySounds.NUKE_THE_SWAMPS, 
			new Item.Properties()
				.rarity(Rarity.RARE)
				.stacksTo(1), 
			4440
		)
	);

	public static final RegistryObject<Item> CRANE_SPAWN_EGG = ITEMS.register(
		"crane_spawn_egg",
		() -> new ForgeSpawnEggItem(
			WetlandWhimsyEntityTypes.CRANE, 
			0xECEBE5, 
			0xB72D18, 
			new Item.Properties()
		)
	);

	public static final RegistryObject<Item> BLEMISH_SPAWN_EGG = ITEMS.register(
		"blemish_spawn_egg",
		() -> new ForgeSpawnEggItem(
			WetlandWhimsyEntityTypes.BLEMISH, 
			0x565E3D,
			0xBECB8F,
			new Item.Properties()
		)
	);

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> BALD_CYPRESS_BOAT = HELPER.createBoatAndChestBoatItem(
		"bald_cypress", 
		WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS
	);

	public static final RegistryObject<Item> ANCIENT_COIN = ITEMS.register(
		"ancient_coin",
		() -> new Item(new Item.Properties())
	);

	static RegistryObject<Item> simpleItem(String name) {
		return ITEMS.register(name, () -> new Item(new Item.Properties()));
	}
}
