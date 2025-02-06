package uwu.juni.wetland_whimsy.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.ResourceOrTagKeyArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.LocateCommand;
import net.minecraft.world.level.levelgen.structure.Structure;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.misc.Config;

@Mixin(LocateCommand.class)
public class IKilledItAndAmNotSorry {
	@Inject(
		method = "locateStructure",
		at= @At(value = "HEAD")
	)
	private static void WetlandWhimsy_IKilledItAndAmNotSorry(
		CommandSourceStack source, 
		ResourceOrTagKeyArgument.Result<Structure> structure,
		CallbackInfoReturnable<Integer> irrelevant
	) throws CommandSyntaxException {
		if (!Config.disableVanillaSwampHuts) return;

		var opt = structure.unwrap().left();
		if (opt.isPresent() && opt.get().location().toString().contains("swamp_hut"))
			throw new SimpleCommandExceptionType(Component.translatable(WetlandWhimsy.MODID + ".swamp_hut_disabled")).create();
	}
}
