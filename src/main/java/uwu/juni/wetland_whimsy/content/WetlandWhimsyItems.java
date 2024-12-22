package uwu.juni.wetland_whimsy.content;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WetlandWhimsyItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WetlandWhimsy.MODID);

	public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem(
		"example_item", 
		new Item.Properties().food(
			new FoodProperties.Builder()
				.alwaysEdible()
				.nutrition(1)
				.saturationModifier(2f)
				.build()
		)
	);
}