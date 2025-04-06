package uwu.juni.wetland_whimsy.worldgen.bald_cypress;

import java.util.List;
import java.util.function.BiConsumer;

import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyTrunkPlacers;

@ParametersAreNonnullByDefault
public class BaldCypressTrunkPlacer extends TrunkPlacer {
	public static final MapCodec<BaldCypressTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
		instance -> trunkPlacerParts(instance).apply(instance, BaldCypressTrunkPlacer::new)
	);
	
	public BaldCypressTrunkPlacer(int height, int heightRandA, int heightRandB) {
		super(height, heightRandA, heightRandB);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return WetlandWhimsyTrunkPlacers.BALD_CYPRESS_TRUNK_PLACER.get();
	}

	@Override
	public List<FoliageAttachment> placeTrunk(
		LevelSimulatedReader level, 
		BiConsumer<BlockPos, BlockState> blockSetter,
		RandomSource random, 
		int height, 
		BlockPos pos, 
		TreeConfiguration config
	) {
		TrunkPlacer.setDirtAt(level, blockSetter, random, pos.below(), config);
		root(level, blockSetter, random, pos, config);

		for (int i = 0; i < height; i++) {
			placeLog(level, blockSetter, random, pos.above(i), config);		
			
			if (!(i > 2 && i < height - 2 && i % 2 != 0))
				continue;

			for (var dir : Direction.Plane.HORIZONTAL)
				if (random.nextDouble() < ((float)i / height) - 0.25)
					branch(level, blockSetter, random, pos.above(i), dir, config);
		}

		return ImmutableList.of(
			new FoliagePlacer.FoliageAttachment(pos.above(height), 0, false)
		);
	}

	void branch(
		LevelSimulatedReader level, 
		BiConsumer<BlockPos, BlockState> blockSetter,
		RandomSource random, 
		BlockPos pos, 
		Direction dir,
		TreeConfiguration config
	) {
		placeLog(
			level, 
			blockSetter, 
			random, 
			pos.relative(dir), 
			config, 
			block -> block.trySetValue(RotatedPillarBlock.AXIS, dir.getAxis())
		);
	}

	void root(
		LevelSimulatedReader level, 
		BiConsumer<BlockPos, BlockState> blockSetter,
		RandomSource random, 
		BlockPos pos,
		TreeConfiguration config
	) {
		var rootPosX = random.nextInt(-1, 1);
		var rootPosZ = random.nextInt(-1, 1);

		setDirtAt(level, blockSetter, random, pos.offset(rootPosX, -1, rootPosZ), config);

		for (var i = 0; i < 2; i++)
			placeLog(level, blockSetter, random, pos.offset(rootPosX, i, rootPosZ), config);
	}
}
