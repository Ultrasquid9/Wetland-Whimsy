package uwu.juni.wetland_whimsy.misc;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;

public class Creative {
	public static void addCreative(final BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			insertItems(
				event,
				Items.CHERRY_BUTTON, 
				WetlandWhimsyBlocks.BALD_CYPRESS_LOG.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.asItem(), 
				WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.asItem(),
				WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_SLAB.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_FENCE.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON.asItem()
			);

			insertItems(
				event,
				Items.CUT_RED_SANDSTONE_SLAB,
				WetlandWhimsyBlocks.LIMESTONE.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_STAIRS.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_SLAB.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_WALL.asItem(), 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE.asItem(), 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.asItem(), 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.asItem(), 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_BRICKS.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_PILLAR.asItem()
			);
		}

		if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			insertItem(
				Items.CHERRY_LOG,
				WetlandWhimsyBlocks.BALD_CYPRESS_LOG.asItem(), 
				event
			);
			insertItem(
				Items.CHERRY_LEAVES,
				WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.asItem(), 
				event
			);
			insertItem(
				Items.RED_MUSHROOM_BLOCK,
				WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK.asItem(), 
				event
			);
			insertItem(
				Items.CHERRY_SAPLING,
				WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.asItem(), 
				event
			);

			insertItems(
				event, 
				Items.RED_MUSHROOM, 
				WetlandWhimsyBlocks.BLOODCAP_MUSHROOM.asItem(),
				WetlandWhimsyBlocks.ARIA_MUSHROOM.asItem(),
				WetlandWhimsyBlocks.ARIA_SPORES.asItem()
			);

			insertItem(
				Items.LARGE_FERN,
				WetlandWhimsyBlocks.CORDGRASS.asItem(), 
				event
			);
			insertItem(
				Items.NETHER_WART,
				WetlandWhimsyBlocks.PENNYWORT.asItem(), 
				event
			);
		}

		if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			insertItem(
				Items.DECORATED_POT,
				WetlandWhimsyBlocks.ANCIENT_POT.asItem(), 
				event
			);
			insertItem(
				Items.CHERRY_HANGING_SIGN,
				WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.asItem(), 
				event
			);
			insertItem(
				Items.CHERRY_HANGING_SIGN,
				WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.asItem(), 
				event
			);
			insertItem(
				Items.SUSPICIOUS_GRAVEL,
				WetlandWhimsyBlocks.SUSSY_MUD.asItem(), 
				event
			);
			insertItems(
				event,
				Items.SOUL_CAMPFIRE,
				WetlandWhimsyBlocks.LIMESTONE_BRAZIER.asItem(), 
				WetlandWhimsyBlocks.SOUL_BRAZIER.asItem(),
				WetlandWhimsyBlocks.ANCIENT_BRAZIER.asItem()
			);
		}

		if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
			insertItem(
				Items.DECORATED_POT,
				WetlandWhimsyBlocks.LIMESTONE_BRAZIER.asItem(), 
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

		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			insertItem(
				Items.MUSIC_DISC_RELIC, 
				WetlandWhimsyItems.DISC.get(), 
				event
			);
		}

		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			insertItem(
				Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE, 
				WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get(), 
				event
			);
			insertItem(
				Items.OMINOUS_TRIAL_KEY, 
				WetlandWhimsyItems.ANCIENT_COIN.get(), 
				event
			);
		}

		if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			insertItem(
				Items.TRIAL_SPAWNER, 
				WetlandWhimsyBlocks.ANCIENT_BRAZIER.asItem(), 
				event
			);
		}
	}

	private static void insertItem(Item previous, Item next, BuildCreativeModeTabContentsEvent event) {
		event.insertAfter(
			new ItemStack(previous), 
			new ItemStack(next),
			TabVisibility.PARENT_AND_SEARCH_TABS
		);
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
