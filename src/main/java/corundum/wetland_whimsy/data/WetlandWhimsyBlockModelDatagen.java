package corundum.wetland_whimsy.data;

import corundum.wetland_whimsy.WetlandWhimsy;
import corundum.wetland_whimsy.content.WetlandWhimsyBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class WetlandWhimsyBlockModelDatagen extends BlockStateProvider {
	public WetlandWhimsyBlockModelDatagen(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, WetlandWhimsy.MODID, fileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.axisBlock(
			WetlandWhimsyBlocks.BALD_CYPRESS_LOG.get(), 
			modLoc("block/bald_cypress_log"), 
			modLoc("block/bald_cypress_log")
		);
	}
}
