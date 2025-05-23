package uwu.juni.wetland_whimsy.content.blocks;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@ParametersAreNonnullByDefault
public class BrazierBlock extends Block implements SimpleWaterloggedBlock {
	static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0);

	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public BrazierBlock(Properties properties) {
		super(properties);

		registerDefaultState(
			stateDefinition.any()
				.setValue(LIT, Boolean.valueOf(true))
				.setValue(WATERLOGGED, Boolean.valueOf(false))
		);
	}

	@Override
	protected void createBlockStateDefinition(@Nonnull Builder<Block, BlockState> builder) {
		builder.add(LIT, WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {
		var water = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;

		return defaultBlockState()
			.setValue(WATERLOGGED, Boolean.valueOf(water))
			.setValue(LIT, Boolean.valueOf(!water));
	}

	@Override
	protected VoxelShape getShape(BlockState a, BlockGetter b, BlockPos c, CollisionContext d) {
		return SHAPE;
	}

	@Override
	public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
		if (state.getValue(BlockStateProperties.WATERLOGGED)) 
			return false;

		if (state.getValue(LIT) && !level.isClientSide())
			level.playSound(null, pos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);

		level.setBlock(pos, state.setValue(WATERLOGGED, Boolean.valueOf(true)).setValue(LIT, Boolean.valueOf(false)), 3);
		level.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(level));
		return true;
	}

	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) 
			? Fluids.WATER.getSource(false) 
			: super.getFluidState(state);
	}

	@Override
	protected ItemInteractionResult useItemOn(
		ItemStack stack, 
		BlockState state, 
		Level level, 
		BlockPos pos,
		Player player, 
		InteractionHand hand,
		BlockHitResult hitResult
	) {
		if (stack.is(ItemTags.SHOVELS) && state.getValue(LIT)) {
			level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 3);

			return ItemInteractionResult.SUCCESS;
		}

		if (state.getValue(WATERLOGGED) || state.getValue(LIT))
			return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

		if (stack.is(Items.FLINT_AND_STEEL) || stack.is(Items.FIRE_CHARGE)) {
			level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(true)), 3);

			return ItemInteractionResult.SUCCESS;
		}

		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		if (!entity.isSteppingCarefully() && entity instanceof LivingEntity && state.getValue(LIT))
			entity.hurt(level.damageSources().hotFloor(), 1.0F);

		super.stepOn(level, pos, state, entity);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (!state.getValue(LIT))
			return;

		if (random.nextInt(10) == 0) {
			level.playLocalSound(
				pos.getX() + 0.5,
				pos.getY() + 0.5,
				pos.getZ() + 0.5,
				SoundEvents.CAMPFIRE_CRACKLE,
				SoundSource.BLOCKS,
				0.5F + random.nextFloat(),
				random.nextFloat() * 0.7F + 0.6F,
				false
			);
		}
	}

	@Override
	protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
		var blockpos = hit.getBlockPos();

		if (
			!level.isClientSide
			&& projectile.isOnFire()
			&& projectile.mayInteract(level, blockpos)
			&& !state.getValue(LIT)
			&& !state.getValue(WATERLOGGED)
		) {
			level.setBlock(blockpos, state.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
		}
	}

	@Override
	protected boolean isSignalSource(@Nonnull BlockState state) {
		return true;
	}

	@Override
	protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return state.getValue(LIT) ? 15 : 0;
	}

	@Override
	protected int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return direction != Direction.DOWN ? state.getSignal(level, pos, direction) : 0;
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType path) {
		return !state.getValue(LIT);
	}
}
