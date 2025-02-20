package uwu.juni.wetland_whimsy.content.blocks;

import javax.annotation.ParametersAreNonnullByDefault;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@ParametersAreNonnullByDefault
public class CordgrassBlock extends BushBlock implements BonemealableBlock {
	protected static final MapCodec<CordgrassBlock> CODEC = simpleCodec(CordgrassBlock::new);
	protected static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 24.0, 15.0);

	public CordgrassBlock(BlockBehaviour.Properties properties) {
		super(properties);
		registerDefaultState(this.defaultBlockState());
	}

	@Override
	protected VoxelShape getShape(BlockState a, BlockGetter b, BlockPos c, CollisionContext d) {
		return SHAPE;
	}

	@Override
	protected MapCodec<? extends BushBlock> codec() {
		return CODEC;
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
		popResource(level, pos, new ItemStack(this));
	}
}
