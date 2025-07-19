package uwu.juni.wetland_whimsy.mixins;

import org.spongepowered.asm.mixin.Mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyPotPatterns;

@Mixin(DecoratedPotPatterns.class)
public class NewPotPatterns {
	@WrapMethod(method = "getResourceKey")
	private static ResourceKey<String> WetlandWhimsy_NewPotPatterns(
		Item item,
		Operation<ResourceKey<String>> og
	) {
		var x = WetlandWhimsyPotPatterns.WW_POT_PATTERNS.get(item);

		if (x == null) {
			return og.call(item);
		}

		return x;
	}
}
