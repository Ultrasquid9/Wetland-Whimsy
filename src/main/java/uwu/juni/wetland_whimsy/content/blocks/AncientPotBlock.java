package uwu.juni.wetland_whimsy.content.blocks;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
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
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsySounds;
import uwu.juni.wetland_whimsy.content.blocks.entities.AncientPotBlockEntity;
import uwu.juni.wetland_whimsy.misc.WetlandWhimsyAdvancementTriggers;

@ParametersAreNonnullByDefault
public class AncientPotBlock extends BaseEntityBlock {
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
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new AncientPotBlockEntity(pos, state);
	}

	@Override
	public RenderShape getRenderShape(@Nonnull BlockState a) {
		return RenderShape.MODEL;
	}

	@Override
	public VoxelShape getShape(
		BlockState a, 
		BlockGetter b, 
		BlockPos c, 
		CollisionContext d
	) {
		return SHAPE;
	}
	

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		var state = super.getStateForPlacement(context);

		// I hate null
		if (state == null) 
			return null;

		return state.setValue(FACING, context.getHorizontalDirection());
	}

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

		if (!stack.is(WetlandWhimsyItems.ANCIENT_COIN.get()))
			return InteractionResult.FAIL;

		if (!player.isCreative())
			stack.setCount(stack.getCount() - 1);

		var entity = level.getBlockEntity(pos, WetlandWhimsyBlockEntities.ANCIENT_POT.get()).get();
		entity.increaseLootQuality();
		entity.setChanged();

		level.gameEvent(null, GameEvent.BLOCK_CHANGE, pos);

		level.playSound(
			null, 
			pos, 
			WetlandWhimsySounds.ANCIENT_POT_INSERT.get(), 
			SoundSource.BLOCKS, 
			1.0F, 
			1.0F
		);

		return InteractionResult.SUCCESS;
	}

	@Override
	public void playerWillDestroy(
		Level level, 
		BlockPos pos, 
		BlockState state, 
		Player player
	) {
		dropLoot(level, pos);

		if (player instanceof ServerPlayer sp && level.getBlockEntity(pos) instanceof AncientPotBlockEntity ap) {
			WetlandWhimsyAdvancementTriggers.ANCIENT_POT_TRIGGER.trigger(sp, ap.lootQuality());
		}

		super.playerWillDestroy(level, pos, state, player);
	}

	private void dropLoot(Level level, BlockPos pos) {
		if (level instanceof ServerLevel serverLevel) {
			var blockEntity = level.getBlockEntity(pos, WetlandWhimsyBlockEntities.ANCIENT_POT.get()).get();
			blockEntity.dropLoot(serverLevel, pos);

			serverLevel.sendParticles(
				WetlandWhimsyParticleTypes.ANCIENT_SOULS.get(),
				(double)pos.getX() + 0.5,
				(double)pos.getY() + 0.5,
				(double)pos.getZ() + 0.5,
				Math.min(WetlandWhimsy.config.ancientPotMaxParticleCount, blockEntity.lootQuality() - 1),
				0.2,
				0.2,
				0.2,
				0.0
			);
		}
	}
}
