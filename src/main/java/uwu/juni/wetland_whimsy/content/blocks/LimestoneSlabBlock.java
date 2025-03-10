package uwu.juni.wetland_whimsy.content.blocks;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import uwu.juni.wetland_whimsy.misc.Compat;

public class LimestoneSlabBlock extends SlabBlock {
	public LimestoneSlabBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public String getDescriptionId() {
		var str = super.getDescriptionId();

		return Compat.shouldChangeLimestoneName()
			? str + ".compat"
			: str;
	}
}
