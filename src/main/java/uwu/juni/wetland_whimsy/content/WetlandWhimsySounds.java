package uwu.juni.wetland_whimsy.content;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class WetlandWhimsySounds {
	public static final DeferredRegister<SoundEvent> SOUNDS = WetlandWhimsy.registry(
		Registries.SOUND_EVENT
	);

	public static final DeferredHolder<SoundEvent, SoundEvent> BLEMISH_AMBIENT = createSound("blemish_ambient");
	public static final DeferredHolder<SoundEvent, SoundEvent> BLEMISH_HIT = createSound("blemish_hit");
	public static final DeferredHolder<SoundEvent, SoundEvent> BLEMISH_DEATH = createSound("blemish_death");

	public static final DeferredHolder<SoundEvent, SoundEvent> ANCIENT_POT_BREAK = createSound("pot_break");
	public static final DeferredHolder<SoundEvent, SoundEvent> ANCIENT_POT_INSERT = createSound("pot_insert");

	public static final DeferredHolder<SoundEvent, SoundEvent> SLUDGE_CHARGE_HIT = createSound("sludge_charge_hit");

	public static final DeferredHolder<SoundEvent, SoundEvent> NUKE_THE_SWAMPS = createSound("nuke_the_swamps");

	public static final DeferredSoundType ANCIENT_POT_SOUNDS = new DeferredSoundType(
		1.5F, 
		1F, 
		ANCIENT_POT_BREAK, 
		ANCIENT_POT_BREAK, 
		ANCIENT_POT_BREAK, 
		ANCIENT_POT_BREAK, 
		ANCIENT_POT_BREAK
	);

	public static DeferredHolder<SoundEvent, SoundEvent> createSound(String name) {
		return SOUNDS.register(
			name, 
			() -> SoundEvent.createVariableRangeEvent(WetlandWhimsy.rLoc(name))
		);
	}
}
