package uwu.juni.wetland_whimsy.content.blocks;

import javax.annotation.Nonnull;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import uwu.juni.wetland_whimsy.content.blocks.entities.AncientPotBlockEntity;

public class AncientPotBlock extends BaseEntityBlock {
	public static final MapCodec<AncientPotBlock> CODEC = simpleCodec(AncientPotBlock::new);
	protected static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

	public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;

	public AncientPotBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected void createBlockStateDefinition(@Nonnull Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new AncientPotBlockEntity(pos, state);
	}

	@Override
	protected RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	protected VoxelShape getShape(
		@Nonnull BlockState state, 
		@Nonnull BlockGetter level, 
		@Nonnull BlockPos pos, 
		@Nonnull CollisionContext context
	) {
		return SHAPE;
	}

	public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {
		var state = super.getStateForPlacement(context);

		// I hate null
		if (state == null) 
			return null;

		return state.setValue(FACING, context.getHorizontalDirection());
	}
}
