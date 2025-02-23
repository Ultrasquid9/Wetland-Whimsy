package uwu.juni.wetland_whimsy.content.blocks;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
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
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.blocks.entities.AncientBrazierBlockEntity;
import uwu.juni.wetland_whimsy.misc.Compat;

public class AncientBrazierBlock extends BaseEntityBlock {
	public enum Flame implements StringRepresentable {
		LIT,
		SMOLDERING,
		UNLIT;

		@Override
		public String getSerializedName() {
			return switch (this) {
				case LIT -> "lit";
				case SMOLDERING -> "smoldering";
				case UNLIT -> "unlit";
			};
		}

		private boolean isLit() {
			return switch (this) {
				case UNLIT -> false;
				default -> true;
			};
		}
	}

	private static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0);

	public static final EnumProperty<AncientBrazierBlock.Flame> FLAME = EnumProperty.create(
		"flame", 
		AncientBrazierBlock.Flame.class, 
		Flame.LIT, 
		Flame.SMOLDERING, 
		Flame.UNLIT
	);

	public AncientBrazierBlock(Properties properties) {
		super(properties);

		registerDefaultState(
			stateDefinition.any().setValue(FLAME, Flame.UNLIT)
		);
	}

	@Override
	protected void createBlockStateDefinition(@Nonnull Builder<Block, BlockState> builder) {
		builder.add(FLAME);
	}

	@SuppressWarnings("null")
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new AncientBrazierBlockEntity(pos, state);
	}

	@Override
	public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {
		return defaultBlockState().setValue(FLAME, Flame.UNLIT);
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

		if (state.getValue(FLAME).isLit())
			return InteractionResult.PASS;

		if (stack.is(Items.FLINT_AND_STEEL) || stack.is(Items.FIRE_CHARGE)) {
			level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.setBlock(pos, state.setValue(FLAME, Flame.LIT), 3);

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	@SuppressWarnings("null")
	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		if (!entity.isSteppingCarefully() && entity instanceof LivingEntity)
			if (state.getValue(FLAME).isLit())
				entity.hurt(level.damageSources().hotFloor(), 1.0F);

		super.stepOn(level, pos, state, entity);
	}

	@SuppressWarnings("null")
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {	
		super.animateTick(state, level, pos, random);

		if (!state.getValue(FLAME).equals(Flame.LIT))
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
			&& !state.getValue(FLAME).isLit()
		) {
			level.setBlock(blockpos, state.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
		}
	}

	@SuppressWarnings("null")
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
		return createTickerHelper(
			blockEntityType, 
			WetlandWhimsyBlockEntities.ANCIENT_BRAZIER.get(), 
			level.isClientSide 
				? AncientBrazierBlockEntity::clientTick 
				: AncientBrazierBlockEntity::serverTick
		);
	}

	@Override
	public boolean isPathfindable(
		@Nonnull BlockState state, 
		@Nonnull BlockGetter x, 
		@Nonnull BlockPos y,
		@Nonnull PathComputationType z
	) {
		return !state.getValue(FLAME).isLit();
	}

	@Override
	public RenderShape getRenderShape(@Nonnull BlockState a) {
		return RenderShape.MODEL;
	}

	@Override
	public String getDescriptionId() {
		var str = super.getDescriptionId();

		return Compat.BRAZIER
			? str + ".compat"
			: str;
	}
}
