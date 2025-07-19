package uwu.juni.wetland_whimsy.content.features;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

@ParametersAreNonnullByDefault
public class BlobPatchFeature extends Feature<BlobPatchConfig> {
	static final double ONE_THIRD = 0.333333333;
	static final double TWO_THRIDS = 0.666666666;

	public BlobPatchFeature() {
		super(BlobPatchConfig.CODEC);
	}

	@Override
	public boolean place(FeaturePlaceContext<BlobPatchConfig> context) {
		var cfg = context.config();
		var rand = context.random();
		var radius = (int)(cfg.radius().sample(rand) * 2);

		return randomRecursiveSphere(
			cfg,
			rand,
			context.level(),
			context.origin(),
			calcNewRadius(radius, rand),
			0
		);
	}

	public boolean randomRecursiveSphere(
		BlobPatchConfig cfg,
		RandomSource rand, 
		WorldGenLevel level,
		BlockPos origin,
		int radius,
		int recursion
	) {
		if (radius <= 2 || recursion >= 4) {
			return false;
		}

		final var pos1 = origin.offset(radius, radius, radius);
		final var pos2 = origin.offset(-radius, -radius, -radius);

		var flag = false;

		for (var pos : BlockPos.betweenClosed(pos1, pos2)) {
			final var dist = pos.distSqr(origin);

			if (dist > radius || !cfg.target().test(level, pos)) {
				continue;
			} else if ((int)dist == radius && rand.nextInt(1, recursion + 2) == 1) {
				flag |= randomRecursiveSphere(
					cfg, 
					rand, 
					level, 
					pos, 
					calcNewRadius(radius, rand), 
					recursion + 1
				);
			}

			if (dist > radius * ONE_THIRD) {
				if (rand.nextBoolean()) {
					continue;
				}
			}
			if (dist > radius * TWO_THRIDS) {
				if (rand.nextBoolean()) {
					continue;
				}
			}

			level.setBlock(
				pos, 
				cfg.stateProvider().getState(level, rand, pos), 
				2
			);
			markAboveForPostProcessing(level, pos);
			flag = true;
		}

		return flag;
	}

	public int calcNewRadius(int prevRadius, RandomSource rand) {
		return (int)((double)(prevRadius + rand.nextInt(-1, 1)) * TWO_THRIDS);
	}
}
