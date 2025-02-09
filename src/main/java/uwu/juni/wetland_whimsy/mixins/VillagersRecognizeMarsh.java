package uwu.juni.wetland_whimsy.mixins;

import org.spongepowered.asm.mixin.Mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biome;
import uwu.juni.wetland_whimsy.datagen.registries.WetlandWhimsyBiomes;

@Mixin(VillagerType.class)
public class VillagersRecognizeMarsh {
	@WrapMethod(method = "byBiome")
	private static VillagerType WetlandWhimsy_NewPotPatterns(
		Holder<Biome> biome,
		Operation<VillagerType> og
	) {
		if (biome.is(WetlandWhimsyBiomes.MARSH))
			return VillagerType.SWAMP;

		return og.call(biome);
	}
}
