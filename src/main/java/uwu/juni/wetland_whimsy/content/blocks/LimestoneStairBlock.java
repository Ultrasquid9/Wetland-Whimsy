package uwu.juni.wetland_whimsy.content.blocks;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import uwu.juni.wetland_whimsy.misc.Compat;

public class LimestoneStairBlock extends StairBlock {
	public LimestoneStairBlock(BlockState state, BlockBehaviour.Properties properties) {
		super(() -> state, properties);
	}

	@Override
	public String getDescriptionId() {
		var str = super.getDescriptionId();

		return Compat.shouldChangeLimestoneName()
			? str + ".compat"
			: str;
	}
}
