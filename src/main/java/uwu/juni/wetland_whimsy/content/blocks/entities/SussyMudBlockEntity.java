package uwu.juni.wetland_whimsy.content.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;

public class SussyMudBlockEntity extends BrushableBlockEntity {
	public SussyMudBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	public BlockEntityType<?> getType() {
		return WetlandWhimsyBlockEntities.SUSSY_MUD.get();
	}
}
