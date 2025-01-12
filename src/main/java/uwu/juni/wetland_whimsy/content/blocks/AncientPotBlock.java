package uwu.juni.wetland_whimsy.content.blocks;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyItems;
import uwu.juni.wetland_whimsy.content.WetlandWhimsySounds;
import uwu.juni.wetland_whimsy.content.blocks.entities.AncientPotBlockEntity;

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
		@Nonnull BlockState a, 
		@Nonnull BlockGetter b, 
		@Nonnull BlockPos c, 
		@Nonnull CollisionContext d
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
	public InteractionResult use(
		@Nonnull BlockState state, 
		@Nonnull Level level, 
		@Nonnull BlockPos pos, 
		@Nonnull Player player, 
		@Nonnull InteractionHand hand, 
		@Nonnull BlockHitResult result
	) {
		var stack = player.getItemInHand(hand);

		if (!stack.is(WetlandWhimsyItems.ANCIENT_COIN.get()))
			return InteractionResult.FAIL;

		if (!player.isCreative())
			stack.setCount(stack.getCount() - 1);

		var entity = level.getBlockEntity(pos, WetlandWhimsyBlockEntities.ANCIENT_POT.get()).get();
		entity.increaseLootQuality();
		entity.setChanged();

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
		@Nonnull Level level, 
		@Nonnull BlockPos pos, 
		@Nonnull BlockState state, 
		@Nonnull Player player
	) {
		dropLoot(level, pos);
		super.playerWillDestroy(level, pos, state, player);
	}

	private void dropLoot(Level level, BlockPos pos) {
		if (level instanceof ServerLevel serverLevel) {
			var blockEntity = level.getBlockEntity(pos, WetlandWhimsyBlockEntities.ANCIENT_POT.get()).get();
			blockEntity.dropLoot(level, pos);

/* 			serverLevel.sendParticles(
				ParticleTypes.DUST_PLUME,
				(double)pos.getX() + 0.5,
				(double)pos.getY() + 0.5,{
	"nuke_the_swamps": {
		"sounds": [
		{
			"name": "wetland_whimsy:nuke_the_swamps",
			"stream": true
		}
		]
	},

	"pot_break": {
		"sounds": [
			{
				"name": "wetland_whimsy:pot_break_1",
				"stream": true
			},
			{
				"name": "wetland_whimsy:pot_break_2",
				"stream": true
			},
			{
				"name": "wetland_whimsy:pot_break_3",
				"stream": true
			}
		]
	},
	"pot_insert": {
		"sounds": [
			{
				"name": "wetland_whimsy:pot_insert_1",
				"stream": true
			},
			{
				"name": "wetland_whimsy:pot_insert_2",
				"stream": true
			},
			{
				"name": "wetland_whimsy:pot_insert_3",
				"stream": true
			}
		]
	}
}

				(double)pos.getZ() + 0.5,
				10,
				0.2,
				0.2,
				0.2,
				0.0
			); */
/* 			serverLevel.sendParticles(
				WetlandWhimsyParticleTypes.ANCIENT_SOULS.get(),
				(double)pos.getX() + 0.5,
				(double)pos.getY() + 0.5,
				(double)pos.getZ() + 0.5,
				Math.min(Config.ancientPotMaxParticleCount, blockEntity.lootQuality() - 1),
				0.2,
				0.2,
				0.2,
				0.0
			); */
		}
	}
}
