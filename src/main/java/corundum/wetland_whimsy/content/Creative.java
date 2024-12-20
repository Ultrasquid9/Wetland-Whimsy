package corundum.wetland_whimsy.content;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class Creative {
	public static void addCreative(final BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			insertItem(
				Items.CHERRY_BUTTON, 
				WetlandWhimsyBlocks.BALD_CYPRESS_LOG.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.BALD_CYPRESS_LOG.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.asItem(), 
				WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.asItem(), 
				WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_SLAB.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.BALD_CYPRESS_SLAB.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_FENCE.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.BALD_CYPRESS_FENCE.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE.asItem(), 
				WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON.asItem(), 
				event
			);

			insertItem(
				Items.CUT_RED_SANDSTONE_SLAB,
				WetlandWhimsyBlocks.LIMESTONE.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.LIMESTONE.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_STAIRS.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.LIMESTONE_STAIRS.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_SLAB.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.LIMESTONE_SLAB.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_WALL.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.LIMESTONE_WALL.asItem(), 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.POLISHED_LIMESTONE.asItem(), 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.asItem(), 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.asItem(), 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_BRICKS.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.LIMESTONE.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.asItem(), 
				event
			);
			insertItem(
				WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.asItem(), 
				WetlandWhimsyBlocks.LIMESTONE_PILLAR.asItem(), 
				event
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
				Items.CHERRY_SAPLING,
				WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.asItem(), 
				event
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
				Items.CHERRY_HANGING_SIGN,
				WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.asItem(), 
				event
			);
			insertItem(
				Items.CHERRY_HANGING_SIGN,
				WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.asItem(), 
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
}
