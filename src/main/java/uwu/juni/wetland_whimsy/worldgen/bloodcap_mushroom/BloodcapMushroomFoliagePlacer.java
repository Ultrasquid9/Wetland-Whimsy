package uwu.juni.wetland_whimsy.worldgen.bloodcap_mushroom;

import javax.annotation.ParametersAreNonnullByDefault;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyFoliagePlacers;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

@ParametersAreNonnullByDefault
public class BloodcapMushroomFoliagePlacer extends FoliagePlacer {
	public static final Codec<BloodcapMushroomFoliagePlacer> CODEC = RecordCodecBuilder.create(
		instance -> foliagePlacerParts(instance)
			.apply(instance, BloodcapMushroomFoliagePlacer::new)
	);

	public BloodcapMushroomFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
		return random.nextInt(4, 6);
	}

	@Override
	protected void createFoliage(
		LevelSimulatedReader level, 
		FoliageSetter blockSetter, 
		RandomSource random,
		TreeConfiguration config, 
		int maxFreeTreeHeight, 
		FoliageAttachment attachment, 
		int foliageHeight,
		int foliageRadius, 
		int offset
	) {
		var pos = attachment.pos();

		var leafHeight = foliageHeight - random.nextInt(2, 3);

		for (int i = 0; i < foliageHeight; i++) {
			this.placeLeavesRow(level, blockSetter, random, config, pos, offset, i - leafHeight, false);
		}
	}

	@Override
	protected boolean shouldSkipLocation(
		RandomSource random, 
		int localX, 
		int localY, 
		int localZ, 
		int range,
		boolean large
	) {
		final var dist = Mth.sqrt(Mth.square(localX) + Mth.square(localZ));
		var should = false;

		should |= dist > random.nextInt(10, 17 + Mth.abs(localY)) / 10F;

		if (localY >= 0) {
			final var thing = Mth.abs(localY - 1) + 2;
			should |= dist > random.nextInt(5, thing * 5) / 10;
		}

		return should;
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return WetlandWhimsyFoliagePlacers.BLOODCAP_MUSHROOM_FOLIAGE_PLACER.get();
	}
}
