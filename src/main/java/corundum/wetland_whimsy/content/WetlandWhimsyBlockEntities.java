package corundum.wetland_whimsy.content;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

public class WetlandWhimsyBlockEntities {
	public static void handleSignEntities(BlockEntityTypeAddBlocksEvent event) {
		event.modify(
			BlockEntityType.SIGN, 
			WetlandWhimsyBlocks.BALD_CYPRESS_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_SIGN.get()
		);

		event.modify(
			BlockEntityType.HANGING_SIGN, 
			WetlandWhimsyBlocks.BALD_CYPRESS_HANGING_SIGN.get(),
			WetlandWhimsyBlocks.BALD_CYPRESS_WALL_HANGING_SIGN.get()
		);
	}
}
