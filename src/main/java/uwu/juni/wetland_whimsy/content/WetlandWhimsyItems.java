package uwu.juni.wetland_whimsy.content;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WetlandWhimsyItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WetlandWhimsy.MODID);

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

	public static final RegistryObject<Item> DOTS_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register(
		"dots_armor_trim_smithing_template",
		() -> new Item(new Item.Properties())
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
}
