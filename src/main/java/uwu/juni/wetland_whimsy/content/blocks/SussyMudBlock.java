package uwu.juni.wetland_whimsy.content.blocks;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import uwu.juni.wetland_whimsy.content.blocks.blockentities.SussyMudBlockEntity;

@SuppressWarnings("null")
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
			properties,
			brush, 
			complete
		);
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SussyMudBlockEntity(pos, state);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState a, BlockGetter b, BlockPos c, CollisionContext d) {
		return SHAPE;
	}

	@Override
	public VoxelShape getBlockSupportShape(BlockState a, BlockGetter b, BlockPos c) {
		return Shapes.block();
	}

	@Override
	public VoxelShape getVisualShape(BlockState a, BlockGetter b, BlockPos c, CollisionContext d) {
		return Shapes.block();
	}
}
