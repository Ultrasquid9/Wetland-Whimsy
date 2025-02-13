package uwu.juni.wetland_whimsy.misc;

import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModCreativeTabs;

public class Creative {
	private final BuildCreativeModeTabContentsEvent event;
	
	public Creative(BuildCreativeModeTabContentsEvent event) {
		this.event = event;

		if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			insertItems(
				Items.CHERRY_BUTTON, 
				WetlandWhimsyBlocks.BALD_CYPRESS_LOG, 
				WetlandWhimsyBlocks.BALD_CYPRESS_WOOD, 
				WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG,
				WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD, 
				WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS, 
				WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS, 
				WetlandWhimsyBlocks.BALD_CYPRESS_SLAB, 
				WetlandWhimsyBlocks.BALD_CYPRESS_FENCE, 
				WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE, 
				WetlandWhimsyBlocks.BALD_CYPRESS_DOOR, 
				WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR, 
				WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE, 
				WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON
			);

			insertItems(
				Items.CUT_RED_SANDSTONE_SLAB,
				WetlandWhimsyBlocks.LIMESTONE, 
				WetlandWhimsyBlocks.LIMESTONE_STAIRS, 
				WetlandWhimsyBlocks.LIMESTONE_SLAB, 
				WetlandWhimsyBlocks.LIMESTONE_WALL, 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE, 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS, 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB, 
				WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL, 
				WetlandWhimsyBlocks.LIMESTONE_BRICKS, 
				WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS, 
				WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB, 
				WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL, 
				WetlandWhimsyBlocks.LIMESTONE_PILLAR
			);
		}

		if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			insertItems(
				Items.CHERRY_LOG,
				WetlandWhimsyBlocks.BALD_CYPRESS_LOG
			);
			insertItems(
				Items.CHERRY_LEAVES,
				WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES
			);
			insertItems(
				Items.RED_MUSHROOM_BLOCK,
				WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK
			);
			insertItems(
				Items.CHERRY_SAPLING,
				WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING
			);
			insertItems(
				Items.HAY_BLOCK,
				WetlandWhimsyBlocks.CORDGRASS_THATCH
				
			);

			insertItems(
				Items.RED_MUSHROOM, 
				WetlandWhimsyBlocks.BLOODCAP_MUSHROOM,
				WetlandWhimsyBlocks.ARIA_MUSHROOM,
				WetlandWhimsyBlocks.ARIA_SPORES
			);

			insertItems(
				Items.LARGE_FERN,
				WetlandWhimsyBlocks.CORDGRASS
			);
			insertItems(
				Items.NETHER_WART,
				WetlandWhimsyBlocks.PENNYWORT
			);
		}

		if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			insertItems(
				Items.DECORATED_POT,
				WetlandWhimsyBlocks.ANCIENT_POT
			);
			insertItems(
				Items.CHERRY_HANGING_SIGN,
				WetlandWhimsyBlocks.BALD_CYPRESS_SIGN
			);
			insertItems(
				Items.CHERRY_HANGING_SIGN,
				WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN
			);
			insertItems(
				Items.SUSPICIOUS_GRAVEL,
				WetlandWhimsyBlocks.SUSSY_MUD
			);
			insertItems(
				Items.SOUL_CAMPFIRE,
				WetlandWhimsyBlocks.LIMESTONE_BRAZIER, 
				WetlandWhimsyBlocks.SOUL_BRAZIER,
				WetlandWhimsyBlocks.ANCIENT_BRAZIER
			);
		}

		if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
			insertItems(
				Items.DECORATED_POT,
				WetlandWhimsyBlocks.LIMESTONE_BRAZIER
			);
		}

		if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
			insertItems(
				Items.BEETROOT_SOUP,
				WetlandWhimsyItems.PENNYWORT_SALAD
			);
		}

		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			insertItems(
				Items.CHERRY_CHEST_BOAT, 
				WetlandWhimsyItems.BALD_CYPRESS_BOAT,
				WetlandWhimsyItems.BALD_CYPRESS_CHEST_BOAT
			);
			insertItems(
				Items.MUSIC_DISC_RELIC, 
				WetlandWhimsyItems.DISC
			);
			insertItems(
				Items.WIND_CHARGE, 
				WetlandWhimsyItems.SLUDGE_CHARGE
			);
		}

		if (event.getTabKey() == CreativeModeTabs.COMBAT) {
			insertItems(
				Items.MACE, 
				WetlandWhimsyItems.DAGGER
			);
			insertItems(
				Items.TOTEM_OF_UNDYING, 
				WetlandWhimsyItems.BASIC_INCENSE,
				WetlandWhimsyItems.BOILING_INCENSE,
				WetlandWhimsyItems.BRINE_INCENSE,
				WetlandWhimsyItems.ROT_INCENSE,
				WetlandWhimsyItems.WEBBED_INCENSE
			);
			insertItems(
				Items.WIND_CHARGE, 
				WetlandWhimsyItems.SLUDGE_CHARGE
			);
		}

		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			insertItems(
				Items.BREEZE_ROD, 
				WetlandWhimsyItems.BLEMISH_ROD
			);
			insertItems(
				Items.HEAVY_CORE, 
				WetlandWhimsyItems.RUSTED_ARTIFACT,
				WetlandWhimsyItems.BASIC_INCENSE,
				WetlandWhimsyItems.BOILING_INCENSE,
				WetlandWhimsyItems.BRINE_INCENSE,
				WetlandWhimsyItems.ROT_INCENSE,
				WetlandWhimsyItems.WEBBED_INCENSE
			);
			insertItems(
				Items.SNORT_POTTERY_SHERD, 
				WetlandWhimsyItems.GROWTH_POTTERY_SHERD,
				WetlandWhimsyItems.SEALED_POTTERY_SHERD
			);
			insertItems(
				Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE, 
				WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE
			);
			insertItems(
				Items.OMINOUS_TRIAL_KEY, 
				WetlandWhimsyItems.ANCIENT_COIN
			);
		}

		if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			insertItems(
				Items.TRIAL_SPAWNER, 
				WetlandWhimsyBlocks.ANCIENT_BRAZIER
			);
			insertItems(
				Items.BLAZE_SPAWN_EGG, 
				WetlandWhimsyItems.BLEMISH_SPAWN_EGG
			);
			insertItems(
				Items.COW_SPAWN_EGG, 
				WetlandWhimsyItems.CRANE_SPAWN_EGG
			);
			insertItems(
				Items.STRIDER_SPAWN_EGG, 
				WetlandWhimsyItems.SWAMP_SPIDER_SPAWN_EGG
			);
		}

		if (Compat.FARMERS_DELIGHT)
			if (event.getTab() == ModCreativeTabs.TAB_FARMERS_DELIGHT.get()) {
				insertItems(
					ModBlocks.CHERRY_CABINET.get(), 
					WetlandWhimsyBlocks.BALD_CYPRESS_CABINET.get()
				);
			}
	}

	private void insertItems(ItemLike start, ItemLike... items) {
		var previous = start;

		for (var next : items) {
			event.insertAfter(
				new ItemStack(previous), 
				new ItemStack(next),
				TabVisibility.PARENT_AND_SEARCH_TABS
			);

			previous = next;
		}
	}
}
