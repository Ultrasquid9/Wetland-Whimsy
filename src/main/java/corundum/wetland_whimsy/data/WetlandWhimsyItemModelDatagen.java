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
		this.withExistingParent(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG_ITEM.get().toString(), 
			modLoc("block/bald_cypress_log")
		);

		this.withExistingParent(
			WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG_ITEM.get().toString(), 
			modLoc("block/stripped_bald_cypress_log")
		);

		this.withExistingParent(
			WetlandWhimsyBlocks.BALD_CYPRESS_PLANKS_ITEM.get().toString(), 
			modLoc("block/bald_cypress_planks")
		);
	}
}
