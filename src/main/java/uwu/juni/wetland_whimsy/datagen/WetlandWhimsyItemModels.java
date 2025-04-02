package uwu.juni.wetland_whimsy.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.misc.Compat;

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

		if (Compat.FARMERS_DELIGHT)
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
		withExistingParent(
			WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.getId().toString(), 
			mcLoc("item/generated")
		)
		.texture("layer0", "block/bald_cypress_sapling");

		withExistingParent(
			WetlandWhimsyBlocks.BLOODCAP_MUSHROOM.getId().toString(), 
			mcLoc("item/generated")
		)
		.texture("layer0", "block/bloodcap_mushroom");

		withExistingParent(
			WetlandWhimsyBlocks.ARIA_SPORES.getId().toString(), 
			mcLoc("item/generated")
		)
		.texture("layer0", "block/aria_spores");
		
		withExistingParent(
			WetlandWhimsyBlocks.PENNYWORT.getId().toString(), 
			mcLoc("item/generated")
		)
		.texture("layer0", "block/pennywort_leaves_bottom")
		.texture("layer1", "block/pennywort_leaves_top");

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
			modLoc("item/incense/basic_incense")
		);
		basicItemWithTexture(
			WetlandWhimsyItems.BOILING_INCENSE, 
			modLoc("item/incense/boiling_incense")
		);
		basicItemWithTexture(
			WetlandWhimsyItems.BRINE_INCENSE, 
			modLoc("item/incense/brine_incense")
		);
		basicItemWithTexture(
			WetlandWhimsyItems.ROT_INCENSE, 
			modLoc("item/incense/rot_incense")
		);
		basicItemWithTexture(
			WetlandWhimsyItems.WEBBED_INCENSE, 
			modLoc("item/incense/webbed_incense")
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

	void basicItemWithTexture(DeferredItem<?> item, ResourceLocation texture) {
		withExistingParent(
			item.getId().toString(), 
			mcLoc("item/generated")
		)
		.texture("layer0", texture);
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
