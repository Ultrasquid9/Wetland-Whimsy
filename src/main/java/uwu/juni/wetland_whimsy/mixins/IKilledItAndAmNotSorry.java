package uwu.juni.wetland_whimsy.mixins;

import org.spongepowered.asm.mixin.Mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.ResourceOrTagKeyArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.LocateCommand;
import net.minecraft.world.level.levelgen.structure.Structure;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.misc.WetlandWhimsyConfig;

@Mixin(LocateCommand.class)
public class IKilledItAndAmNotSorry {
	private static final CommandSyntaxException ERR = new SimpleCommandExceptionType(
		Component.translatable(WetlandWhimsy.MODID + ".swamp_hut_disabled")
	).create();

	@WrapMethod(method = "locateStructure")
	private static int WetlandWhimsy_IKilledItAndAmNotSorry(
		CommandSourceStack source, 
		ResourceOrTagKeyArgument.Result<Structure> structure,
		Operation<Integer> og
	) throws CommandSyntaxException {
		var opt = structure.unwrap().left();

		if (
			WetlandWhimsyConfig.disableVanillaSwampHutsOrDefault()
			&& opt.isPresent()
			&& opt
				.get()
				.location()
				.toString()
				.contains("minecraft:swamp_hut")
		) {
			throw ERR;
		}

		return og.call(source, structure);
	}
}
