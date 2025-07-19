package uwu.juni.wetland_whimsy.data;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.misc.Compat;

public class WetlandWhimsyItemModelDatagen extends ItemModelProvider {
	public WetlandWhimsyItemModelDatagen(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, WetlandWhimsy.MODID, fileHelper);
	}

	@Override
	protected void registerModels() {
		// Block items

		// Simple block items
		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get());
		simpleBlockItem(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get());
		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_WOOD.get());
		simpleBlockItem(WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.get());

		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES.get());

		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS.get());
		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_STAIRS.get());
		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_SLAB.get());

		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_FENCE_GATE.get());
		simpleBlockItem(WetlandWhimsyBlocks.BALD_CYPRESS_PRESSURE_PLATE.get());

		simpleBlockItem(WetlandWhimsyBlocks.LIMESTONE.get());
		simpleBlockItem(WetlandWhimsyBlocks.POLISHED_LIMESTONE.get());
		simpleBlockItem(WetlandWhimsyBlocks.LIMESTONE_BRICKS.get());
		simpleBlockItem(WetlandWhimsyBlocks.LIMESTONE_STAIRS.get());
		simpleBlockItem(WetlandWhimsyBlocks.POLISHED_LIMESTONE_STAIRS.get());
		simpleBlockItem(WetlandWhimsyBlocks.LIMESTONE_BRICK_STAIRS.get());
		simpleBlockItem(WetlandWhimsyBlocks.LIMESTONE_SLAB.get());
		simpleBlockItem(WetlandWhimsyBlocks.POLISHED_LIMESTONE_SLAB.get());
		simpleBlockItem(WetlandWhimsyBlocks.LIMESTONE_BRICK_SLAB.get());
		simpleBlockItem(WetlandWhimsyBlocks.LIMESTONE_PILLAR.get());

		simpleBlockItem(WetlandWhimsyBlocks.CORDGRASS_THATCH.get());
		simpleBlockItem(WetlandWhimsyBlocks.ARIA_MUSHROOM_BLOCK.get());
		simpleBlockItem(WetlandWhimsyBlocks.BLOODCAP_MUSHROOM_BLOCK.get());

		simpleBlockItem(WetlandWhimsyBlocks.ANCIENT_POT.get());

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
			WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.get().asItem().toString(), 
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
			WetlandWhimsyBlocks.SUSSY_MUD.get().asItem().toString(), 
			modLoc("block/suspicious_mud_0")
		);

		// Block items that are 2d in the inventory and use a block texture
		withExistingParent(
			WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.getId().toString(), 
			mcLoc("item/generated")
		)
		.texture("layer0", "block/bald_cypress_sapling");
		
		withExistingParent(
			WetlandWhimsyBlocks.PENNYWORT.getId().toString(), 
			mcLoc("item/generated")
		)
		.texture("layer0", "block/pennywort_leaves_bottom")
		.texture("layer1", "block/pennywort_leaves_top");

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

		// Block items that are 2d in the inventory and use a unique texture
		basicItem(WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.get().asItem());
		basicItem(WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get().asItem());
		basicItem(WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get().asItem());
		basicItem(WetlandWhimsyBlocks.CORDGRASS.get().asItem());
		basicItem(WetlandWhimsyBlocks.LIMESTONE_BRAZIER.get().asItem());
		basicItem(WetlandWhimsyBlocks.SOUL_BRAZIER.get().asItem());

		if (Compat.ENDERGETIC)
			basicItem(WetlandWhimsyBlocks.ENDER_BRAZIER.get().get().asItem());
		if (Compat.CNC)
			basicItem(WetlandWhimsyBlocks.CUPRIC_BRAZIER.get().get().asItem());

		basicItem(WetlandWhimsyBlocks.ARIA_MUSHROOM.get().asItem());

		basicItem(WetlandWhimsyBlocks.ANCIENT_BRAZIER.get().asItem());

		// Items
		basicItem(WetlandWhimsyItems.PENNYWORT_SALAD.get());
		basicItem(WetlandWhimsyItems.DOTS_ARMOR_TRIM_SMITHING_TEMPLATE.get());
		basicItem(WetlandWhimsyItems.NUKE_THE_SWAMPS_MUSIC_DISC.get());
		basicItem(WetlandWhimsyItems.ANCIENT_COIN.get());
		
		basicItem(WetlandWhimsyItems.BALD_CYPRESS_BOAT.getFirst().get());
		basicItem(WetlandWhimsyItems.BALD_CYPRESS_BOAT.getSecond().get());

		spawnEgg(WetlandWhimsyItems.CRANE_SPAWN_EGG);
	}

	private void simpleBlockItem(Block block) {
		var name = ForgeRegistries.BLOCKS.getKey(block).getPath();

		withExistingParent(
			name, 
			this.modLoc("block/" + name)
		);
	}

	void spawnEgg(RegistryObject<? extends Item> item) {
		withExistingParent(
			item.getId().getPath(), 
			mcLoc("item/template_spawn_egg")
		);
	}
}
