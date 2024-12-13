package corundum.wetland_whimsy.data;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

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

		// These three don't work with the normal simpleBlockItem method
		fenceInventory(
			WetlandWhimsyBlocks.BALD_CYPRESS_FENCE.getId().toString(), 
			modLoc("block/bald_cypress_planks")
		);
		buttonInventory(
			WetlandWhimsyBlocks.BALD_CYPRESS_BUTTON.getId().toString(), 
			modLoc("block/bald_cypress_planks")
		);
		this.withExistingParent(
			WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR.asItem().toString(), 
			modLoc("block/bald_cypress_trapdoor_bottom")
		);

		// Block items that are 2d in the inventory and use a block texture
		this.withExistingParent(
			WetlandWhimsyBlocks.BALD_CYPRESS_SAPLING.getId().toString(), 
			mcLoc("item/generated")
		)
		.texture("layer0", "block/bald_cypress_sapling");
		
		this.withExistingParent(
			WetlandWhimsyBlocks.PENNYWORT.getId().toString(), 
			mcLoc("item/generated")
		)
		.texture("layer0", "block/pennywort_leaves_bottom")
		.texture("layer1", "block/pennywort_leaves_top");

		// Block items that are 2d in the inventory and use a unique texture
		this.basicItem(WetlandWhimsyBlocks.BALD_CYPRESS_DOOR.asItem());
		this.basicItem(WetlandWhimsyBlocks.CORDGRASS.asItem());
	}
}
