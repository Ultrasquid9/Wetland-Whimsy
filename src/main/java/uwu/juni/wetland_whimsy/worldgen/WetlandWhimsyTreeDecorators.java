package uwu.juni.wetland_whimsy.worldgen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.worldgen.aria_mushroom.AriaMushroomTreeDecorator;

public class WetlandWhimsyTreeDecorators {
	public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(
		BuiltInRegistries.TREE_DECORATOR_TYPE, 
		WetlandWhimsy.MODID
	);

	public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<AriaMushroomTreeDecorator>> ARIA_MUSHROOMS = TREE_DECORATORS.register(
		"aria_mushrooms", 
		() -> new TreeDecoratorType<>(AriaMushroomTreeDecorator.CODEC)
	);
}
