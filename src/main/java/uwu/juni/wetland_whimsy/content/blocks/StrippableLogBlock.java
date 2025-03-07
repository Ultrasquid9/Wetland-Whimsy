package uwu.juni.wetland_whimsy.content.blocks;

import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;

@ParametersAreNonnullByDefault
public class StrippableLogBlock extends RotatedPillarBlock {
	public StrippableLogBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public boolean isFlammable(BlockState a, BlockGetter b, BlockPos c, Direction d) {
		return true;
	}
	
	@Override
	public int getFlammability(BlockState a, BlockGetter b, BlockPos c, Direction d) {
		return 5;
	}

	@Override
	public int getFireSpreadSpeed(BlockState a, BlockGetter b, BlockPos c, Direction d) {
		return 5;
	}

	@Override
	public BlockState getToolModifiedState(
		BlockState state, 
		UseOnContext context, 
		ItemAbility itemAbility,
		boolean simulate
	) {
		// Stripping
		// Just like ur mom
		if (context.getItemInHand().getItem() instanceof AxeItem) {
			if (state.is(WetlandWhimsyBlocks.BALD_CYPRESS_LOG)) {
				return WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			} else if (state.is(WetlandWhimsyBlocks.BALD_CYPRESS_WOOD)) {
				return WetlandWhimsyBlocks.STRIPPED_BALD_CYPRESS_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			}
		}

		return super.getToolModifiedState(state, context, itemAbility, simulate);
	}
}
