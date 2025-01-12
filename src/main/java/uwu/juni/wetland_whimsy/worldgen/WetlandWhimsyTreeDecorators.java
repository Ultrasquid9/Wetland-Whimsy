package uwu.juni.wetland_whimsy.worldgen;

import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.worldgen.aria_mushroom.AriaMushroomTreeDecorator;

public class WetlandWhimsyTreeDecorators {
	public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(
		ForgeRegistries.TREE_DECORATOR_TYPES, 
		WetlandWhimsy.MODID
	);

	public static final RegistryObject<TreeDecoratorType<AriaMushroomTreeDecorator>> ARIA_MUSHROOMS = TREE_DECORATORS.register(
		"aria_mushrooms", 
		() -> new TreeDecoratorType<>(AriaMushroomTreeDecorator.CODEC)
	);
}
