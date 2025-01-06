package uwu.juni.wetland_whimsy.worldgen.bald_cypress;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyFoliagePlacers;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

@SuppressWarnings("null")
public class BaldCypressFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<BaldCypressFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
		(parts) -> foliagePlacerParts(parts).apply(parts, BaldCypressFoliagePlacer::new)
	);

	public BaldCypressFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
		return random.nextInt(6, 9);
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

		var leafHeight = foliageHeight - random.nextInt(2, 4);

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
        return Mth.square(localX) + Mth.lerp(0.75, localY, range / 1.5) + Mth.square(localZ) > range + random.nextInt(3);
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return WetlandWhimsyFoliagePlacers.BALD_CYPRESS_FOLIAGE_PLACER.get();
	}
}
