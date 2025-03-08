package uwu.juni.wetland_whimsy.worldgen.aria_mushroom;

import javax.annotation.Nonnull;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlocks;
import uwu.juni.wetland_whimsy.content.blocks.AriaMushroomBlock;
import uwu.juni.wetland_whimsy.worldgen.WetlandWhimsyTreeDecorators;

public class AriaMushroomTreeDecorator extends TreeDecorator {
	public static final MapCodec<AriaMushroomTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
		.fieldOf("probability")
		.xmap(AriaMushroomTreeDecorator::new, instance -> instance.probability);

	private final float probability;

	public AriaMushroomTreeDecorator(float probability) {
		this.probability = probability;
	}

	@Override
	public void place(@Nonnull TreeDecorator.Context context) {
		var random = context.random();

		if (random.nextFloat() >= probability)
			return;
		
		var list = context.logs();
		var i = list.get(0).getY();

		list.stream()
			.filter(pos -> pos.getY() - i <= 2)
			.forEach(pos -> {
				for (var dir : Direction.Plane.HORIZONTAL) {
					if (random.nextFloat() > 0.5F)
						continue;

					var newdir = dir.getOpposite();
					var newpos = pos.offset(newdir.getStepX(), 0, newdir.getStepZ());

					if (!context.isAir(newpos))
						continue;

					context.setBlock(
						newpos,
						WetlandWhimsyBlocks.ARIA_MUSHROOM.get()
							.defaultBlockState()
							.setValue(AriaMushroomBlock.FACING, dir)
					);
				}
			});
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return WetlandWhimsyTreeDecorators.ARIA_MUSHROOMS.get();
	}
}
