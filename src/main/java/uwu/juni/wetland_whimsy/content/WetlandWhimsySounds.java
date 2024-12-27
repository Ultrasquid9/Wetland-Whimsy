package uwu.juni.wetland_whimsy.content;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class WetlandWhimsySounds {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(
		ForgeRegistries.SOUND_EVENTS, 
		WetlandWhimsy.MODID
	);

	public static final RegistryObject<SoundEvent> NUKE_THE_SWAMPS = SOUNDS.register(
		"nuke_the_swamps", 
		() -> SoundEvent.createVariableRangeEvent(
			new ResourceLocation(WetlandWhimsy.MODID, "nuke_the_swamps")
		)
	);
}
