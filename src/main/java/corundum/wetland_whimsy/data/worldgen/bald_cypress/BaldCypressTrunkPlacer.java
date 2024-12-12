package corundum.wetland_whimsy.data.worldgen.bald_cypress;

import java.util.List;
import java.util.function.BiConsumer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import corundum.wetland_whimsy.data.worldgen.WetlandWhimsyTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class BaldCypressTrunkPlacer extends TrunkPlacer {
	private static final int[] X = {
		0,
		-1,
		0,
		1
	};
	private static final int[] Y = {
		-1,
		0,
		1,
		0
	};

	public static final MapCodec<BaldCypressTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
		(instance) -> trunkPlacerParts(instance).apply(instance, BaldCypressTrunkPlacer::new)
	);
	
	public BaldCypressTrunkPlacer(int height, int heightRandA, int heightRandB) {
		super(height, heightRandA, heightRandB);
	}

    protected TrunkPlacerType<?> type() {
        return WetlandWhimsyTrunkPlacerTypes.BALD_CYPRESS_TRUNK_PLACER.get();
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

			if (i > 2 && i < height - 2 && i % 2 != 0) {
				for (int j = 0; j < 4; j++) {
					if (Math.random() < ((float)i / height) - 0.25) {
						this.branch(level, blockSetter, random, pos.above(i), X[j], Y[j], config);
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
		int x,
		int y,
		TreeConfiguration config
	) {
		for (int i = 0; i < 4; i++) {
			var new_x = x * i;
			var new_y = y * i;

			this.placeLog(level, blockSetter, random, pos.offset(new_x, 0, new_y), config);
		}
	}
}
