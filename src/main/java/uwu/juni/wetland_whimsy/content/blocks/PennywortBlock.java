package uwu.juni.wetland_whimsy.content.blocks;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@ParametersAreNonnullByDefault
public class PennywortBlock extends FlowerBlock implements BonemealableBlock {
	public static final int MAX_PENNYWORTS = 4;
	public static final IntegerProperty PENNYWORT_COUNT = BlockStateProperties.FLOWER_AMOUNT;
	public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;

	protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 5.0, 14.0);

	public PennywortBlock(Holder<MobEffect> effect, float time, BlockBehaviour.Properties properties) {
		super(effect, time, properties);

		registerDefaultState(
			stateDefinition.any()
				.setValue(FACING, Direction.NORTH)
				.setValue(PENNYWORT_COUNT, 1)
		);
	}

	@Override
	protected VoxelShape getShape(BlockState a, BlockGetter b, BlockPos c, CollisionContext d) {
		return SHAPE;
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(PENNYWORT_COUNT, FACING);
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		var state = context.getLevel().getBlockState(context.getClickedPos());

		if (state.is(this)) {
			var count = state.getValue(PENNYWORT_COUNT);

			return state.setValue(PENNYWORT_COUNT, Integer.valueOf(count != 4 ? count + 1 : 4));
		}

		var s = super.getStateForPlacement(context);
		return s == null 
			? s
			: s.setValue(FACING, context.getHorizontalDirection());
	}

	@Override
	protected boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		return !useContext.isSecondaryUseActive() && useContext.getItemInHand().is(this.asItem()) && state.getValue(PENNYWORT_COUNT) < 4
			? true
			: super.canBeReplaced(state, useContext);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader a, BlockPos b, BlockState c) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level a, RandomSource b, BlockPos c, BlockState d) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		var count = state.getValue(PENNYWORT_COUNT);

		if (count == 4)
			popResource(level, pos, new ItemStack(this));
		else 
			level.setBlock(pos, state.setValue(PENNYWORT_COUNT, Integer.valueOf(count + 1)), 2);
	}
}
