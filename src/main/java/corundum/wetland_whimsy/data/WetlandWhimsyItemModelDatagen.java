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

		// Block items that are 3d in the inventory
		this.withExistingParent(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG_ITEM.get().toString(), 
			modLoc("block/bald_cypress_log")
		);
		this.withExistingParent(
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG_ITEM.get().toString(), 
			modLoc("block/stripped_bald_cypress_log")
		);
		this.withExistingParent(
			WetlandWhimsyBlocks.BALD_CYPRESS_LEAVES_ITEM.get().toString(), 
			modLoc("block/bald_cypress_leaves")
		);
		this.withExistingParent(
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS_ITEM.get().toString(), 
			modLoc("block/bald_cypress_planks")
		);
		this.withExistingParent(
			WetlandWhimsyBlocks.BALD_CYPRESS_TRAPDOOR_ITEM.get().toString(), 
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
		this.basicItem(WetlandWhimsyBlocks.BALD_CYPRESS_DOOR_ITEM.get());
		this.basicItem(WetlandWhimsyBlocks.CORDGRASS_ITEM.get());
	}
}
