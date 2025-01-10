package uwu.juni.wetland_whimsy.worldgen.aria_mushroom;

import java.util.List;

import javax.annotation.Nonnull;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.blocks.AriaMushroomBlock;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyTreeDecorators;

public class AriaMushroomTreeDecorator extends TreeDecorator {
	public static final MapCodec<AriaMushroomTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
		.fieldOf("probability")
		.xmap(AriaMushroomTreeDecorator::new, p_226037_ -> p_226037_.probability);

	private final float probability;

	public AriaMushroomTreeDecorator(float probability) {
		this.probability = probability;
	}

	@Override
	public void place(@Nonnull TreeDecorator.Context context) {
		var randomsource = context.random();

		if (!(randomsource.nextFloat() >= probability)) {
			List<BlockPos> list = context.logs();

			int i = list.get(0).getY();
			list.stream()
				.filter(p_69980_ -> p_69980_.getY() - i <= 2)
				.forEach(
					p_226026_ -> {
						for (var direction : Direction.Plane.HORIZONTAL) {
							if (randomsource.nextFloat() <= 0.5F) {
								Direction direction1 = direction.getOpposite();
								BlockPos blockpos = p_226026_.offset(direction1.getStepX(), 0, direction1.getStepZ());
								if (context.isAir(blockpos)) {
									context.setBlock(
										blockpos,
										WetlandWhimsyBlocks.ARIA_MUSHROOM.get()
											.defaultBlockState()
											.setValue(AriaMushroomBlock.FACING, direction)
									);
								}
							}
						}
					}
				);
		}
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return WetlandWhimsyTreeDecorators.ARIA_MUSHROOMS.get();
	}
}
