package uwu.juni.wetland_whimsy.content.blocks;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AriaMushroomBlock extends BushBlock implements BonemealableBlock {
	private static Direction[] DIRECTIONS = {
		Direction.NORTH,
		Direction.SOUTH,
		Direction.EAST,
		Direction.WEST
	};

	protected static final MapCodec<AriaMushroomBlock> CODEC = simpleCodec(AriaMushroomBlock::new);

	public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;

	protected static final VoxelShape SHAPE_NORTH = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 5.0);
	protected static final VoxelShape SHAPE_SOUTH = Block.box(0.0, 0.0, 11.0, 16.0, 16.0, 16.0);
	protected static final VoxelShape SHAPE_EAST = Block.box(11.0, 0.0, 0.0, 16.0, 16.0, 16.0);
	protected static final VoxelShape SHAPE_WEST = Block.box(0.0, 0.0, 0.0, 5.0, 16.0, 16.0);

	public AriaMushroomBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected void createBlockStateDefinition(@Nonnull Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	protected MapCodec<? extends BushBlock> codec() {
		return CODEC;
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {
		var x = super.getStateForPlacement(context);
		if (x == null) return null;

		var dir = context.getClickedFace();

		for (var d : DIRECTIONS)
			if (d == dir)
				return x.setValue(FACING, d);

		return x.setValue(FACING, context.getHorizontalDirection());
	}

	@Override
	protected VoxelShape getShape(
		@Nonnull BlockState state, 
		@Nonnull BlockGetter level, 
		@Nonnull BlockPos pos, 
		@Nonnull CollisionContext context
	) { 
		return switch (state.getValue(FACING)) {
			case Direction.NORTH -> SHAPE_NORTH;
			case Direction.SOUTH -> SHAPE_SOUTH;
			case Direction.EAST -> SHAPE_EAST;
			case Direction.WEST -> SHAPE_WEST;
			default -> SHAPE_NORTH;
		};
	}

	@Override
	public boolean isValidBonemealTarget(
		@Nonnull LevelReader level, 
		@Nonnull BlockPos pos, 
		@Nonnull BlockState state
	) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(
		@Nonnull Level level, 
		@Nonnull RandomSource random, 
		@Nonnull BlockPos pos, 
		@Nonnull BlockState state
	) {
		return true;
	}

	@Override
	public void performBonemeal(
		@Nonnull ServerLevel level, 
		@Nonnull RandomSource random, 
		@Nonnull BlockPos pos, 
		@Nonnull BlockState state
	) {
		tryGrow(level, pos, state, 18);
	}

	@Override
	protected void randomTick(
		@Nonnull BlockState state, 
		@Nonnull ServerLevel level, 
		@Nonnull BlockPos pos, 
		@Nonnull RandomSource random
	) {
		if (random.nextInt(25) == 0) 
			tryGrow(level, pos, state, 4);
	}

	@Override
	protected boolean canSurvive(
		@Nonnull BlockState state, 
		@Nonnull LevelReader level, 
		@Nonnull BlockPos pos
	) {
		var new_pos = pos.relative(state.getValue(FACING));
		return level.getBlockState(new_pos).isSolidRender(level, new_pos);
	}

	// Modified version of vanilla's MushroomBlock code 
	private void tryGrow(Level level, BlockPos pos, BlockState state, int chance) {
		var random = ThreadLocalRandom.current();

		int i = chance;

		for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-3, -3, -3), pos.offset(3, 3, 3))) {
			if (level.getBlockState(blockpos).is(this)) {
				if (--i <= 0) {
					return;
				}
			}
		}

		Function<BlockPos, BlockPos> blockPosFn = (oldPos) -> {
			return oldPos.offset(
				random.nextInt(3) - random.nextInt(3),
				random.nextInt(3) - random.nextInt(3),
				random.nextInt(3) - random.nextInt(3)
			);
		};

		BlockPos blockpos1 = blockPosFn.apply(pos);

		for (int k = 0; k < chance; k++) {
			if (level.isEmptyBlock(blockpos1) && canSurvive(level, blockpos1, state)) {
				pos = blockpos1;
			}

			blockpos1 = blockPosFn.apply(pos);
		}

		if (level.isEmptyBlock(blockpos1) && canSurvive(level, blockpos1, state)) {
			level.setBlock(blockpos1, getValidDir(level, blockpos1, state), 2);
		}
	}

	private boolean canSurvive(Level level, BlockPos pos, BlockState state) {
		for (var d : DIRECTIONS) {
			state = state.setValue(FACING, d);

			if (state.canSurvive(level, pos))
				return true;
		}

		return false;
	}

	private BlockState getValidDir(Level level, BlockPos pos, BlockState state) {
		for (var d : DIRECTIONS) {
			state = state.setValue(FACING, d);

			if (state.canSurvive(level, pos))
				return state;
		}

		return state;
	}
}
