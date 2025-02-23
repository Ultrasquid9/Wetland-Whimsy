package uwu.juni.wetland_whimsy.content.blocks;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import uwu.juni.wetland_whimsy.misc.Compat;

public class BrazierBlock extends Block {
	private static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0);

	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public BrazierBlock(Properties properties) {
		super(properties);

		registerDefaultState(
			stateDefinition.any().setValue(LIT, Boolean.valueOf(true))
		);
	}

	@Override
	protected void createBlockStateDefinition(@Nonnull Builder<Block, BlockState> builder) {
		builder.add(LIT);
	}

	@Override
	public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {
		var water = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;

		return defaultBlockState()
			.setValue(LIT, Boolean.valueOf(!water));
	}

	@SuppressWarnings("null")
	@Override
	public VoxelShape getShape(BlockState a, BlockGetter b, BlockPos c, CollisionContext d) {
		return SHAPE;
	}

	@SuppressWarnings("null")
	@Override
	public InteractionResult use(
		BlockState state, 
		Level level, 
		BlockPos pos, 
		Player player, 
		InteractionHand hand, 
		BlockHitResult result
	) {
		var stack = player.getItemInHand(hand);

		if (stack.is(ItemTags.SHOVELS) && state.getValue(LIT)) {
			level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 3);

			return InteractionResult.SUCCESS;
		}

		if (state.getValue(LIT))
			return InteractionResult.PASS;

		if (stack.is(Items.FLINT_AND_STEEL) || stack.is(Items.FIRE_CHARGE)) {
			level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(true)), 3);

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	@SuppressWarnings("null")
	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		if (!entity.isSteppingCarefully() && entity instanceof LivingEntity && state.getValue(LIT))
			entity.hurt(level.damageSources().hotFloor(), 1.0F);

		super.stepOn(level, pos, state, entity);
	}

	@SuppressWarnings("null")
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (!state.getValue(LIT))
			return;

		if (random.nextInt(10) == 0) {
			level.playLocalSound(
				(double)pos.getX() + 0.5,
				(double)pos.getY() + 0.5,
				(double)pos.getZ() + 0.5,
				SoundEvents.CAMPFIRE_CRACKLE,
				SoundSource.BLOCKS,
				0.5F + random.nextFloat(),
				random.nextFloat() * 0.7F + 0.6F,
				false
			);
		}
	}

	@SuppressWarnings("null")
	@Override
	public void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
		BlockPos blockpos = hit.getBlockPos();

		if (
			!level.isClientSide
			&& projectile.isOnFire()
			&& projectile.mayInteract(level, blockpos)
			&& !state.getValue(LIT)
		) {
			level.setBlock(blockpos, state.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
		}
	}

	@Override
	public boolean isSignalSource(@Nonnull BlockState state) {
		return true;
	}

	@SuppressWarnings("null")
	@Override
	public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return state.getValue(LIT) ? 15 : 0;
	}

	@SuppressWarnings("null")
	@Override
	public int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return direction != Direction.DOWN ? state.getSignal(level, pos, direction) : 0;
	}

	@Override
	public String getDescriptionId() {
		var str = super.getDescriptionId();

		return Compat.BRAZIER
			? str + ".compat"
			: str;
	}
}
