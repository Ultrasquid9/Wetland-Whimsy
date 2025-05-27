package uwu.juni.wetland_whimsy.content;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class WetlandWhimsySounds {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(
		ForgeRegistries.SOUND_EVENTS, 
		WetlandWhimsy.MODID
	);

	public static final RegistryObject<SoundEvent> ANCIENT_POT_BREAK = createSound("pot_break");
	public static final RegistryObject<SoundEvent> ANCIENT_POT_INSERT = createSound("pot_insert");

	public static final ForgeSoundType ANCIENT_POT_SOUNDS = new ForgeSoundType(
		1.5F, 
		1F, 
		ANCIENT_POT_BREAK, 
		ANCIENT_POT_BREAK, 
		ANCIENT_POT_BREAK, 
		ANCIENT_POT_BREAK, 
		ANCIENT_POT_BREAK
	);

	public static final RegistryObject<SoundEvent> NUKE_THE_SWAMPS = createSound("nuke_the_swamps");

	public static RegistryObject<SoundEvent> createSound(String name) {
		return SOUNDS.register(
			name, 
			() -> SoundEvent.createVariableRangeEvent(
				ResourceLocation.fromNamespaceAndPath(
					WetlandWhimsy.MODID, 
					name
				)
			)
		);
	}
}
