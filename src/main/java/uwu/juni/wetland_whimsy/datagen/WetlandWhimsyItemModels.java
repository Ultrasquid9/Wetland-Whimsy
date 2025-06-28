package uwu.juni.wetland_whimsy.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.misc.WetlandWhimsyCompat;

public class WetlandWhimsyItemModels extends ItemModelProvider {
	public WetlandWhimsyItemModels(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, WetlandWhimsy.MODID, fileHelper);
	}

	@Override
	protected void registerModels() {
		// Block items

		// Simple block items
		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_LOG);
		simpleBlockItem(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG);
		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_WOOD);
		simpleBlockItem(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD);

		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES);

		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS);
		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS);
		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_SLAB);

		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE);
		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE);

		if (WetlandWhimsyCompat.FARMERS_DELIGHT)
			simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_CABINET.get());

		simpleBlockItem(WetlandWhimsyBlocks.LIMESTONE);
		simpleBlockItem(WetlandWhimsyBlocks.POLISHED_LIMESTONE);
		simpleBlockItem(WetlandWhimsyBlocks.LIMESTONE_BRICKS);
		simpleBlockItem(WetlandWhimsyBlocks.LIMESTONE_STAIRS);
		simpleBlockItem(WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS);
		simpleBlockItem(WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS);
		simpleBlockItem(WetlandWhimsyBlocks.LIMESTONE_SLAB);
		simpleBlockItem(WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB);
		simpleBlockItem(WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB);
		simpleBlockItem(WetlandWhimsyBlocks.LIMESTONE_PILLAR);

		simpleBlockItem(WetlandWhimsyBlocks.CORDGRASS_THATCH);
		simpleBlockItem(WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK);
		simpleBlockItem(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM_BLOCK);

		simpleBlockItem(WetlandWhimsyBlocks.ANCIENT_POT);

		// These ones don't work with the normal simpleBlockItem method
		fenceInventory(
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE.getId().toString(), 
			modLoc("block/bald_cypress_planks")
		);
		buttonInventory(
			WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON.getId().toString(), 
			modLoc("block/bald_cypress_planks")
		);
		withExistingParent(
			WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.asItem().toString(), 
			modLoc("block/bald_cypress_trapdoor_bottom")
		);

		wallInventory(
			WetlandWhimsyBlocks.LIMESTONE_WALL.getId().toString(), 
			modLoc("block/limestone")
		);
		wallInventory(
			WetlandWhimsyBlocks.POLISHED_LIMESTONE_WALL.getId().toString(), 
			modLoc("block/polished_limestone")
		);
		wallInventory(
			WetlandWhimsyBlocks.LIMESTONE_BRICK_WALL.getId().toString(), 
			modLoc("block/limestone_bricks")
		);

		withExistingParent(
			WetlandWhimsyBlocks.SUSSY_MUD.asItem().toString(), 
			modLoc("block/suspicious_mud_0")
		);

		// Block items that are 2d in the inventory and use a block texture
		flatBlock(WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING, "bald_cypress_sapling");
		flatBlock(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM, "bloodcap_mushroom");
		flatBlock(WetlandWhimsyBlocks.ARIA_SPORES, "aria_spores");
		flatBlock(
			WetlandWhimsyBlocks.PENNYWORT, 
			"pennywort_leaves_bottom",
			"pennywort_leaves_top"
		);

		// Block items that are 2d in the inventory and use a unique texture
		basicItem(WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get().asItem());
		basicItem(WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get().asItem());
		basicItem(WetlandWhimsyBlocks.BALD_CYPRESS_DOOR);
		basicItem(WetlandWhimsyBlocks.CORDGRASS);
		basicItem(WetlandWhimsyBlocks.ARIA_MUSHROOM);
		basicItem(WetlandWhimsyBlocks.LIMESTONE_BRAZIER);
		basicItem(WetlandWhimsyBlocks.SOUL_BRAZIER);
		basicItem(WetlandWhimsyBlocks.ANCIENT_BRAZIER);

		// Items
		basicItem(WetlandWhimsyItems.PENNYWORT_SALAD);
		basicItem(WetlandWhimsyItems.CARROT_SEEDS);
		basicItem(WetlandWhimsyItems.BALD_CYPRESS_BOAT);
		basicItem(WetlandWhimsyItems.BALD_CYPRESS_CHEST_BOAT);
		basicItem(WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE);
		basicItem(WetlandWhimsyItems.DISC);
		basicItem(WetlandWhimsyItems.ANCIENT_COIN);
		basicItem(WetlandWhimsyItems.GROWTH_POTTERY_SHERD);
		basicItem(WetlandWhimsyItems.SEALED_POTTERY_SHERD);
		basicItem(WetlandWhimsyItems.BLEMISH_ROD);
		basicItem(WetlandWhimsyItems.SLUDGE_CHARGE);
		basicItem(WetlandWhimsyItems.RUSTED_ARTIFACT);
		basicItem(WetlandWhimsyItems.DAGGER);

		basicItemWithTexture(
			WetlandWhimsyItems.BASIC_INCENSE, 
			"incense/basic_incense"
		);
		basicItemWithTexture(
			WetlandWhimsyItems.BOILING_INCENSE, 
			"incense/boiling_incense"
		);
		basicItemWithTexture(
			WetlandWhimsyItems.BRINE_INCENSE, 
			"incense/brine_incense"
		);
		basicItemWithTexture(
			WetlandWhimsyItems.ROT_INCENSE, 
			"incense/rot_incense"
		);
		basicItemWithTexture(
			WetlandWhimsyItems.WEBBED_INCENSE, 
			"incense/webbed_incense"
		);

		withExistingParent(
			WetlandWhimsyItems.DAGGER.getId().getPath(), 
			mcLoc("item/handheld")
		)
		.texture("layer0", "item/dagger");

		spawnEgg(WetlandWhimsyItems.BLEMISH_SPAWN_EGG);
		spawnEgg(WetlandWhimsyItems.CRANE_SPAWN_EGG);
		spawnEgg(WetlandWhimsyItems.SWAMP_SPIDER_SPAWN_EGG);
	}

	void flatBlock(DeferredBlock<?> block, String... textures) {
		var builder = withExistingParent(
			block.getId().toString(), 
			mcLoc("item/generated")
		);

		var layer = 0;
		for (var texture : textures) {
			builder.texture("layer" + layer, "block/" + texture);
			layer++;
		}
	}

	void basicItemWithTexture(DeferredItem<?> item, String texture) {
		withExistingParent(
			item.getId().toString(), 
			mcLoc("item/generated")
		)
		.texture("layer0", "item/" + texture);
	}

	void spawnEgg(DeferredItem<?> item) {
		withExistingParent(
			item.getId().getPath(), 
			mcLoc("item/template_spawn_egg")
		);
	}

	void simpleBlockItem(DeferredBlock<?> block) { simpleBlockItem(block.get()); }
	void basicItem(DeferredBlock<?> block) { basicItem(block.asItem()); }
	void basicItem(DeferredItem<?> item) { basicItem(item.get()); }
}
