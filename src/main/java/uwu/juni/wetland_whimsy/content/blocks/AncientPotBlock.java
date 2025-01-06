package uwu.juni.wetland_whimsy.content.blocks;

import javax.annotation.Nonnull;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsySounds;
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

	@Override
	protected ItemInteractionResult useItemOn(
		@Nonnull ItemStack stack, 
		@Nonnull BlockState state, 
		@Nonnull Level level, 
		@Nonnull BlockPos pos,
		@Nonnull Player player, 
		@Nonnull InteractionHand hand, 
		@Nonnull BlockHitResult hitResult
	) {
		if (!stack.is(WetlandWhimsyItems.ANCIENT_COIN))
			return ItemInteractionResult.FAIL;

		if (!player.isCreative())
			stack.consumeAndReturn(1, player);

		var entity = level.getBlockEntity(pos, WetlandWhimsyBlockEntities.ANCIENT_POT.get()).get();
		entity.increaseLootQuality();
		entity.setChanged();

		if (level instanceof ServerLevel serverlevel) {
			serverlevel.sendParticles(
				ParticleTypes.DUST_PLUME,
				(double)pos.getX() + 0.5,
				(double)pos.getY() + 1.2,
				(double)pos.getZ() + 0.5,
				7,
				0.0,
				0.0,
				0.0,
				0.0
			);
		}

		level.playSound(
			null, 
			pos, 
			WetlandWhimsySounds.ANCIENT_POT_INSERT.get(), 
			SoundSource.BLOCKS, 
			1.0F, 
			1.0F
		);

		return ItemInteractionResult.SUCCESS;
	}

	@Override
	public BlockState playerWillDestroy(
		@Nonnull Level level, 
		@Nonnull BlockPos pos, 
		@Nonnull BlockState state, 
		@Nonnull Player player
	) {
		dropLoot(level, pos);
		return super.playerWillDestroy(level, pos, state, player);
	}

	@Override
	protected void onProjectileHit(
		@Nonnull Level level, 
		@Nonnull BlockState state, 
		@Nonnull BlockHitResult result, 
		@Nonnull Projectile projectile
	) {
		BlockPos pos = result.getBlockPos();

		if (!level.isClientSide && projectile.mayInteract(level, pos) && projectile.mayBreak(level)) {
			dropLoot(level, pos);
			level.destroyBlock(pos, true, projectile);
		}
	}

	private void dropLoot(Level level, BlockPos pos) {
		if (!level.isClientSide())
			level.getBlockEntity(pos, WetlandWhimsyBlockEntities.ANCIENT_POT.get())
				.get()
				.dropLoot(level, pos);
	}
}
