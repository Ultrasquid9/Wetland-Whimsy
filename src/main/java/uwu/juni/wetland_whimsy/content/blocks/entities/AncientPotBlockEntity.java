package uwu.juni.wetland_whimsy.content.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;

public class AncientPotBlockEntity extends BlockEntity {
	public AncientPotBlockEntity(BlockPos pos, BlockState state) {
		super(WetlandWhimsyBlockEntities.ANCIENT_POT.get(), pos, state);
	}
}
