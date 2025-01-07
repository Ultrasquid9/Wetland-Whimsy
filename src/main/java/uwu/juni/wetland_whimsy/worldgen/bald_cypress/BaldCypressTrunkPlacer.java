package uwu.juni.wetland_whimsy.worldgen.bald_cypress;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyTrunkPlacers;
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

@SuppressWarnings("null")
public class BaldCypressTrunkPlacer extends TrunkPlacer {
	private static final Direction[] DIRS = {
		Direction.NORTH,
		Direction.SOUTH,
		Direction.EAST,
		Direction.WEST
	};

	public static final Codec<BaldCypressTrunkPlacer> CODEC = RecordCodecBuilder.create(
		(instance) -> trunkPlacerParts(instance)
			.apply(instance, BaldCypressTrunkPlacer::new)
	);
	
	public BaldCypressTrunkPlacer(int height, int heightRandA, int heightRandB) {
		super(height, heightRandA, heightRandB);
	}

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
		this.root(level, blockSetter, random, pos, config);

		for (int i = 0; i < height; i++) {
			this.placeLog(level, blockSetter, random, pos.above(i), config);			

			if (i > 2 && i < height - 2 && i % 2 != 0) {
				for (int j = 0; j < 4; j++) {
					if (Math.random() < ((float)i / height) - 0.25) {
						this.branch(level, blockSetter, random, pos.above(i), DIRS[j], config);
					}
				}
			}
		}

		return ImmutableList.of(
			new FoliagePlacer.FoliageAttachment(pos.above(height), 0, false)
		);
	}

	public void branch(
		LevelSimulatedReader level, 
		BiConsumer<BlockPos, BlockState> blockSetter,
		RandomSource random, 
		BlockPos pos, 
		Direction dir,
		TreeConfiguration config
	) {
        Function<BlockState, BlockState> fn = block -> block.trySetValue(RotatedPillarBlock.AXIS, dir.getAxis());

		this.placeLog(level, blockSetter, random, pos.relative(dir), config, fn);
	}

	public void root(
		LevelSimulatedReader level, 
		BiConsumer<BlockPos, BlockState> blockSetter,
		RandomSource random, 
		BlockPos pos,
		TreeConfiguration config
	) {
		var rootPosX = random.nextInt(-1, 1);
		var rootPosZ = random.nextInt(-1, 1);

		TrunkPlacer.setDirtAt(level, blockSetter, random, pos.offset(rootPosX, -1, rootPosZ), config);

		for (int i = 0; i < 2; i++) {
			this.placeLog(level, blockSetter, random, pos.offset(rootPosX, i, rootPosZ), config);
		}
	}
}
