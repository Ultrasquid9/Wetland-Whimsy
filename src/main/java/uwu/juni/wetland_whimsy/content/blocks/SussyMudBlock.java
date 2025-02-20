package uwu.juni.wetland_whimsy.content.blocks;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@ParametersAreNonnullByDefault
public class SussyMudBlock extends BrushableBlock {
    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0);

	public SussyMudBlock(
		Block block,
		SoundEvent brush,
		SoundEvent complete,
		BlockBehaviour.Properties properties
	) {
		super(
			block, 
			brush, 
			complete, 
			properties
		);
	}

	@Override
	protected VoxelShape getCollisionShape(BlockState a, BlockGetter b, BlockPos c, CollisionContext d) {
		return SHAPE;
	}

	@Override
	protected VoxelShape getVisualShape(BlockState a, BlockGetter b, BlockPos c, CollisionContext d) {
		return Shapes.block();
	}

	@Override
	protected VoxelShape getBlockSupportShape(BlockState a, BlockGetter b, BlockPos c) {
		return Shapes.block();
	}
}
