package uwu.juni.wetland_whimsy.content.blocks;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;

public class BloodcapMushroomBlock extends BushBlock implements BonemealableBlock {
	private static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 12.0, 12.0);
	
	public BloodcapMushroomBlock(Properties properties) {
		super(properties);
	}

	public VoxelShape getShape(
		@Nonnull BlockState a, 
		@Nonnull BlockGetter b, 
		@Nonnull BlockPos c, 
		@Nonnull CollisionContext d
	) {
		return SHAPE;
	}

	@Override
	public boolean isValidBonemealTarget(
		@Nonnull LevelReader a, 
		@Nonnull BlockPos b, 
		@Nonnull BlockState c,
		boolean d
	) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(
		@Nonnull Level level, 
		@Nonnull RandomSource random, 
		@Nonnull BlockPos pos, 
		@Nonnull BlockState state
	) {
		return true;
	}

	@Override
	public void performBonemeal(
		@Nonnull ServerLevel level, 
		@Nonnull RandomSource random, 
		@Nonnull BlockPos pos, 
		@Nonnull BlockState state
	) {
		tryGrow(level, pos, state, 18);
	}

	@Override
	public void tick(
		@Nonnull BlockState state, 
		@Nonnull ServerLevel level, 
		@Nonnull BlockPos pos, 
		@Nonnull RandomSource random
	) {
		if (random.nextInt(25) == 0) 
			tryGrow(level, pos, state, 4);
	}

	@Override
	public void entityInside(
		@Nonnull BlockState state, 
		@Nonnull Level level, 
		@Nonnull BlockPos pos, 
		@Nonnull Entity entity
	) {
		if (entity.getType() == EntityType.MOOSHROOM) 
			return;

		if (level.isClientSide || level.getDifficulty() == Difficulty.PEACEFUL)
			return;
		else if (entity instanceof LivingEntity lEntity)
			lEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 50));
	}

	@Override
	public void animateTick(
		@Nonnull BlockState state, 
		@Nonnull Level level, 
		@Nonnull BlockPos pos, 
		@Nonnull RandomSource random
	) {
		VoxelShape voxelshape = this.getShape(state, level, pos, CollisionContext.empty());
		Vec3 vec3 = voxelshape.bounds().getCenter();
		double d0 = (double)pos.getX() + vec3.x;
		double d1 = (double)pos.getZ() + vec3.z;

		for (int i = 0; i < 3; i++) {
			if (random.nextInt(0, 4) == 0) {
				level.addParticle(
					WetlandWhimsyParticleTypes.BLOODCAP_SPORES.get(),
					d0 + random.nextDouble() / 5.0,
					(double)pos.getY() + (0.65 - random.nextDouble()),
					d1 + random.nextDouble() / 5.0,
					0.0,
					0.0,
					0.0
				);
			}
		}
	}

	@Override
	public boolean canSurvive(
		@Nonnull BlockState state, 
		@Nonnull LevelReader level, 
		@Nonnull BlockPos pos
	) {
		var new_pos = pos.below();
		var new_state = level.getBlockState(new_pos);

		return new_state.is(BlockTags.LUSH_GROUND_REPLACEABLE) && new_state.isSolidRender(level, new_pos);
	}

	@Override
	protected boolean mayPlaceOn(
		@Nonnull BlockState state, 
		@Nonnull BlockGetter level, 
		@Nonnull BlockPos pos
	) {
		return state.isSolidRender(level, pos);
	}

	// Copied from the vanilla MushroomBlock code 
	private void tryGrow(Level level, BlockPos pos, BlockState state, int chance) {
		var random = ThreadLocalRandom.current();

		int i = chance;

		for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, -1, -4), pos.offset(4, 1, 4))) {
			if (level.getBlockState(blockpos).is(this)) {
				if (--i <= 0) {
					return;
				}
			}
		}

		BlockPos blockpos1 = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);

		for (int k = 0; k < chance; k++) {
			if (level.isEmptyBlock(blockpos1) && state.canSurvive(level, blockpos1)) {
				pos = blockpos1;
			}

			blockpos1 = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
		}

		if (level.isEmptyBlock(blockpos1) && state.canSurvive(level, blockpos1)) {
			level.setBlock(blockpos1, state, 2);
		}
	}
}
