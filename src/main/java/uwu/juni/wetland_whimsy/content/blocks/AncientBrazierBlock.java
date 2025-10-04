package uwu.juni.wetland_whimsy.content.blocks;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.blocks.entities.AncientBrazierBlockEntity;
import uwu.juni.wetland_whimsy.tags.WetlandWhimsyTags;

@ParametersAreNonnullByDefault
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
			return this != UNLIT;
		}
	}

	public static final MapCodec<AncientBrazierBlock> CODEC = simpleCodec(AncientBrazierBlock::new);

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

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new AncientBrazierBlockEntity(pos, state);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FLAME, Flame.UNLIT);
	}

	@Override
	protected VoxelShape getShape(BlockState a, BlockGetter b, BlockPos c, CollisionContext d) {
		return SHAPE;
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
		final var fail = ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		final var success = ItemInteractionResult.SUCCESS;

		if (stack.is(WetlandWhimsyTags.Items.INCENSE) && level.getBlockEntity(pos) instanceof AncientBrazierBlockEntity brazier) {
			var bool = brazier.trySetIncense(stack.getItem());

			if (!bool) {
				return fail;
			}

			if (!player.isCreative()) {
				stack.shrink(1);
			}

			if (level instanceof ServerLevel sLevel) {
				createParticles(sLevel, pos);
			}

			level.setBlock(pos, state.setValue(FLAME, Flame.UNLIT), 2);
			return success;
		}

		if (!canBeLit(state, pos, level)) {
			return fail;
		}

		if (stack.is(WetlandWhimsyTags.Items.FLAMMABLE)) {
			level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.setBlock(pos, state.setValue(FLAME, Flame.LIT), 11);

			return success;
		}

		return fail;
	}

	private void createParticles(ServerLevel level, BlockPos pos) {
		var random = level.getRandom();
		var be = level.getBlockEntity(pos);

		if (be instanceof AncientBrazierBlockEntity ab) {
			var color = ab.getIncense(level).get().color();

			for (var i = 0; i < random.nextInt(8, 12); i++) {
				level.sendParticles(
					new DustParticleOptions(color, 1), 
					pos.getX() + ((float)random.nextInt(2, 8)) / 10, 
					pos.getY() + .5, 
					pos.getZ() + ((float)random.nextInt(2, 8)) / 10, 
					1, 
					0, 
					0, 
					0, 
					.5
				);
			}
		}
	}

	@Override
	public void stepOn(
		Level level, 
		BlockPos pos, 
		BlockState state, 
		Entity entity
	) {
		if (!entity.isSteppingCarefully() && entity instanceof LivingEntity) {
			if (state.getValue(FLAME).isLit()) {
				entity.hurt(level.damageSources().hotFloor(), 1.0F);
			}
		}

		super.stepOn(level, pos, state, entity);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {	
		super.animateTick(state, level, pos, random);

		if (!state.getValue(FLAME).equals(Flame.LIT)) {
			return;
		}

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
	protected void onProjectileHit(
		Level level, 
		BlockState state, 
		BlockHitResult hit, 
		Projectile projectile
	) {
		var blockpos = hit.getBlockPos();

		if (
			!level.isClientSide
			&& projectile.isOnFire()
			&& projectile.mayInteract(level, blockpos)
			&& canBeLit(state, blockpos, level)
		) {
			level.setBlock(blockpos, state.setValue(FLAME, Flame.LIT), 11);
		}
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
		Level level, 
		BlockState state, 
		BlockEntityType<T> blockEntityType
	) {
		return createTickerHelper(
			blockEntityType, 
			WetlandWhimsyBlockEntities.ANCIENT_BRAZIER.get(), 
			level.isClientSide 
				? AncientBrazierBlockEntity::clientTick 
				: AncientBrazierBlockEntity::serverTick
		);
	}

	@Override
	protected RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType path) {
		return !state.getValue(FLAME).isLit();
	}

	private boolean canBeLit(BlockState state, BlockPos pos, Level level) {
		boolean hasIncense = level.getBlockEntity(pos) instanceof AncientBrazierBlockEntity ab && ab.hasIncense();
		return !state.getValue(FLAME).isLit() || hasIncense;
	}
}
