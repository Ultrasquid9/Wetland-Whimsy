package uwu.juni.wetland_whimsy.worldgen.aria_mushroom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyFoliagePlacers;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

@SuppressWarnings("null")
public class AriaMushroomFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<AriaMushroomFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
		(parts) -> foliagePlacerParts(parts).apply(parts, AriaMushroomFoliagePlacer::new)
	);

	public AriaMushroomFoliagePlacer(IntProvider radius, IntProvider offset) {
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

		var leafHeight = foliageHeight - random.nextInt(1, 2);

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
		WetlandWhimsy.LOGGER.info(String.valueOf(localX) + " " + String.valueOf(localZ));

        return Mth.square(localX) + funniTreeMath(localY) + Mth.square(localZ) > range;
	}

	private float funniTreeMath(int y) {
		var val = Mth.sin(y * Mth.abs(y)) * 5;

		return val > 2 ? 999 : val;
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return WetlandWhimsyFoliagePlacers.ARIA_MUSHROOM_FOLIAGE_PLACER.get();
	}
}
