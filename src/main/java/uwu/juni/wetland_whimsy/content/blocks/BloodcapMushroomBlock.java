package uwu.juni.wetland_whimsy.content.blocks;

import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
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
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;
import uwu.juni.wetland_whimsy.data.registries.WetlandWhimsyConfiguredFeatures;

@ParametersAreNonnullByDefault
public class BloodcapMushroomBlock extends BushBlock implements BonemealableBlock {
	private static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 12.0, 12.0);
	
	public BloodcapMushroomBlock(Properties properties) {
		super(properties);
	}

	public VoxelShape getShape(
		BlockState a, 
		BlockGetter b, 
		BlockPos c, 
		CollisionContext d
	) {
		return SHAPE;
	}

	@Override
	public boolean isValidBonemealTarget(
		LevelReader a, 
		BlockPos b, 
		BlockState c,
		boolean d
	) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(
		Level level, 
		RandomSource random, 
		BlockPos pos, 
		BlockState state
	) {
		return true;
	}

	@Override
	public void performBonemeal(
		ServerLevel level, 
		RandomSource random, 
		BlockPos pos, 
		BlockState state
	) {
		growMushroom(level, pos, state, random);
	}

	@Override
	public void tick(
		BlockState state, 
		ServerLevel level, 
		BlockPos pos, 
		RandomSource random
	) {
		if (random.nextInt(25) == 0) {
			tryGrow(level, pos, state, random, 4);
		}
	}

	@Override
	public void entityInside(
		BlockState state, 
		Level level, 
		BlockPos pos, 
		Entity entity
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
		BlockState state, 
		Level level, 
		BlockPos pos, 
		RandomSource random
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
		BlockState state, 
		LevelReader level, 
		BlockPos pos
	) {
		var new_pos = pos.below();
		var new_state = level.getBlockState(new_pos);

		return new_state.is(BlockTags.LUSH_GROUND_REPLACEABLE) && new_state.isSolidRender(level, new_pos);
	}

	@Override
	protected boolean mayPlaceOn(
		BlockState state, 
		BlockGetter level, 
		BlockPos pos
	) {
		return state.isSolidRender(level, pos);
	}

	// Copied from the vanilla MushroomBlock code 
	private void tryGrow(
		Level level,
		BlockPos pos,
		BlockState state,
		RandomSource random,
		int chance
	) {
		for (var blockpos : BlockPos.betweenClosed(pos.offset(-4, -1, -4), pos.offset(4, 1, 4))) {
			if (level.getBlockState(blockpos).is(this) && --chance <= 0) {
				return;
			}
		}

		var blockpos1 = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);

		for (var i = 0; i < chance; i++) {
			if (level.isEmptyBlock(blockpos1) && state.canSurvive(level, blockpos1)) {
				pos = blockpos1;
			}

			blockpos1 = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
		}

		if (level.isEmptyBlock(blockpos1) && state.canSurvive(level, blockpos1)) {
			level.setBlock(blockpos1, state, 2);
		}
	}

	// Also copied from the vanilla MushroomBlock code 
	boolean growMushroom(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {		
		Optional<? extends Holder<ConfiguredFeature<?, ?>>> optional = level.registryAccess()
			.registryOrThrow(Registries.CONFIGURED_FEATURE)
			.getHolder(WetlandWhimsyConfiguredFeatures.HUGE_BLOODCAP_MUSHROOM);

		level.removeBlock(pos, false);
		if (optional.get().value().place(level, level.getChunkSource().getGenerator(), random, pos)) {
			return true;
		} else {
			level.setBlock(pos, state, 3);
			return false;
		}
	}
}
