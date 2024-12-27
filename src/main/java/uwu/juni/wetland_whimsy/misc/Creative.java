package uwu.juni.wetland_whimsy.misc;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;

public class Creative {
	public static void addCreative(final BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			insertItems(
				event,
				Items.CHERRY_BUTTON, 
				WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get().asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.get().asItem(), 
				WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get().asItem(),
				WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.get().asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get().asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS.get().asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_SLAB.get().asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_FENCE.get().asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE.get().asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.get().asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.get().asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE.get().asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON.get().asItem()
			);

			insertItems(
				event,
				Items.CUT_RED_SANDSTONE_SLAB,
				WetlandWhimsyBlocks.LIMESTONE.get().asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_STAIRS.get().asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_SLAB.get().asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_WALL.get().asItem(), 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE.get().asItem(), 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.get().asItem(), 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.get().asItem(), 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.get().asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_BRICKS.get().asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.get().asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.get().asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.get().asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_PILLAR.get().asItem()
			);
		}

		if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			insertItem(
				Items.CHERRY_LOG,
				WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get().asItem(), 
				event
			);
			insertItem(
				Items.CHERRY_LEAVES,
				WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get().asItem(), 
				event
			);
			insertItem(
				Items.CHERRY_SAPLING,
				WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.get().asItem(), 
				event
			);

			insertItem(
				Items.LARGE_FERN,
				WetlandWhimsyBlocks.CORDGRASS.get().asItem(), 
				event
			);
			insertItem(
				Items.NETHER_WART,
				WetlandWhimsyBlocks.PENNYWORT.get().asItem(), 
				event
			);
		}

		if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			insertItem(
				Items.CHERRY_HANGING_SIGN,
				WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get().asItem(), 
				event
			);
			insertItem(
				Items.CHERRY_HANGING_SIGN,
				WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get().asItem(), 
				event
			);
			insertItem(
				Items.SUSPICIOUS_GRAVEL,
				WetlandWhimsyBlocks.SUSSY_MUD.get().asItem(), 
				event
			);
		}

		if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
			insertItem(
				Items.BEETROOT_SOUP,
				WetlandWhimsyItems.PENNYWORT_SALAD.get(), 
				event
			);
		}

		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			insertItem(
				Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE, 
				WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get(), 
				event
			);
		}
	}

	private static void insertItem(Item previous, Item next, BuildCreativeModeTabContentsEvent event) {
		event.accept(next);
	}

	private static void insertItems(BuildCreativeModeTabContentsEvent event, Item start, Item... items) {
		var previous = start;

		for (var next : items) {
			insertItem(
				previous, 
				next, 
				event
			);

			previous = next;
		}
	}
}
