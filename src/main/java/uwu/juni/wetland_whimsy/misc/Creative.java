package uwu.juni.wetland_whimsy.misc;

import static net.minecraft.world.item.crafting.Ingredient.of;

import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;

public class Creative {
	public static void addCreative(final BuildCreativeModeTabContentsEvent event) {
		CreativeModeTabContentsPopulator.mod(WetlandWhimsy.MODID)

			.tab(CreativeModeTabs.BUILDING_BLOCKS)
			.addItemsAfter(
				of(Items.CHERRY_BUTTON), 
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
			)
			.addItemsAfter(
				of(Items.CUT_RED_SANDSTONE_SLAB),
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
			)

			.tab(CreativeModeTabs.NATURAL_BLOCKS)
			.addItemsAfter(
				of(Items.CHERRY_LOG),
				WetlandWhimsyBlocks.BALD_CYPRESS_LOG
			)
			.addItemsAfter(
				of(Items.CHERRY_LEAVES),
				WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES
			)
			.addItemsAfter(
				of(Items.RED_MUSHROOM_BLOCK),
				WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK,
				WetlandWhimsyBlocks.BLOODCAP_MUSHROOM_BLOCK
			)
			.addItemsAfter(
				of(Items.CHERRY_SAPLING),
				WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING
			)
			.addItemsAfter(
				of(Items.HAY_BLOCK),
				WetlandWhimsyBlocks.CORDGRASS_THATCH
			)
			.addItemsAfter(
				of(Items.RED_MUSHROOM),
				WetlandWhimsyBlocks.BLOODCAP_MUSHROOM,
				WetlandWhimsyBlocks.ARIA_MUSHROOM,
				WetlandWhimsyBlocks.ARIA_SPORES
			)
			.addItemsAfter(
				of(Items.LARGE_FERN),
				WetlandWhimsyBlocks.CORDGRASS
			)
			.addItemsAfter(
				of(Items.NETHER_WART),
				WetlandWhimsyBlocks.PENNYWORT
			)

			.tab(CreativeModeTabs.FUNCTIONAL_BLOCKS)
			.addItemsAfter(
				of(Items.CHERRY_SIGN), 
				WetlandWhimsyBlocks.BALD_CYPRESS_SIGN
			)
			.addItemsAfter(
				of(Items.CHERRY_HANGING_SIGN), 
				WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN
			)
			.addItemsAfter(
				of(Items.SUSPICIOUS_GRAVEL), 
				WetlandWhimsyBlocks.SUSSY_MUD
			)
			.addItemsAfter(
				of(Items.SOUL_CAMPFIRE), 
				WetlandWhimsyBlocks.LIMESTONE_BRAZIER,
				WetlandWhimsyBlocks.SOUL_BRAZIER,
				WetlandWhimsyBlocks.ANCIENT_BRAZIER
			)
			.addItemsAfter(
				of(Items.DECORATED_POT), 
				WetlandWhimsyBlocks.ANCIENT_POT
			)

			.tab(CreativeModeTabs.REDSTONE_BLOCKS)
			.addItemsAfter(
				of(Items.LECTERN), 
				WetlandWhimsyBlocks.LIMESTONE_BRAZIER
			)

			.tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
			.addItemsAfter(
				of(Items.MUSIC_DISC_RELIC), 
				WetlandWhimsyItems.NUKE_THE_SWAMPS_MUSIC_DISC
			)
			.addItemsAfter(
				of(Items.CHERRY_CHEST_BOAT), 
				WetlandWhimsyItems.BALD_CYPRESS_BOAT.getFirst(),
				WetlandWhimsyItems.BALD_CYPRESS_BOAT.getSecond()
			)

			.tab(CreativeModeTabs.FOOD_AND_DRINKS)
			.addItemsAfter(
				of(Items.BEETROOT_SOUP), 
				WetlandWhimsyItems.PENNYWORT_SALAD
			)

			.tab(CreativeModeTabs.INGREDIENTS)
			.addItemsAfter(
				of(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE), 
				WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE
			)
			.addItemsAfter(
				of(Items.SNORT_POTTERY_SHERD), 
				WetlandWhimsyItems.ANCIENT_COIN
			);

		if (Compat.ENDERGETIC)
			CreativeModeTabContentsPopulator.mod(WetlandWhimsy.MODID)
				.tab(CreativeModeTabs.FUNCTIONAL_BLOCKS)
				.addItemsAfter(
					of(WetlandWhimsyBlocks.SOUL_BRAZIER.get()), 
					WetlandWhimsyBlocks.ENDER_BRAZIER.get()
				);
		if (Compat.CNC)
			CreativeModeTabContentsPopulator.mod(WetlandWhimsy.MODID)
				.tab(CreativeModeTabs.FUNCTIONAL_BLOCKS)
				.addItemsAfter(
					of(WetlandWhimsyBlocks.SOUL_BRAZIER.get()), 
					WetlandWhimsyBlocks.CUPRIC_BRAZIER.get()
				);
	}
}
