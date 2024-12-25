package uwu.juni.wetland_whimsy.content;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class WetlandWhimsySounds {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(
		BuiltInRegistries.SOUND_EVENT, 
		WetlandWhimsy.MODID
	);

	public static final DeferredHolder<SoundEvent, SoundEvent> NUKE_THE_SWAMPS = SOUNDS.register(
		"nuke_the_swamps", 
		() -> SoundEvent.createVariableRangeEvent(
			ResourceLocation.fromNamespaceAndPath(
				WetlandWhimsy.MODID, 
				"nuke_the_swamps"
			)
		)
	);
}
