package corundum.wetland_whimsy.data.worldgen.bald_cypress;

import java.util.List;
import java.util.function.BiConsumer;

import com.google.common.collect.ImmutableList;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

public class BaldCypressTrunkPlacer extends StraightTrunkPlacer {
	public BaldCypressTrunkPlacer(int height, int heightRandA, int heightRandB) {
		super(height, heightRandA, heightRandB);
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

		for (int i = 0; i < height; i++) {
			this.placeLog(level, blockSetter, random, pos.above(i), config);

			if (i > 2 && i % 2 == 0) {
				branch(level, blockSetter, random, pos, config);
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
		TreeConfiguration config
	) {
		for (int i = 0; i < 3; i++) {
			this.placeLog(level, blockSetter, random, pos.south(i), config);
		}
	}
}
