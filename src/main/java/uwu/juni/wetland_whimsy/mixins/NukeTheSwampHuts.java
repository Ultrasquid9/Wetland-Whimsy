package uwu.juni.wetland_whimsy.mixins;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.structures.SwampHutStructure;
import uwu.juni.wetland_whimsy.misc.Config;

@Mixin(SwampHutStructure.class)
public class NukeTheSwampHuts {
	@ModifyReturnValue(
		method = "findGenerationPoint",
		at = @At("RETURN")
	)
	private Optional<Structure.GenerationStub> WetlandWhimsy_YouWillNotFindAGenerationPoint(
		Optional<Structure.GenerationStub> dontCare
	) {
		if (!Config.disableVanillaSwampHuts) return dontCare;

		return Optional.empty();
	}
}
