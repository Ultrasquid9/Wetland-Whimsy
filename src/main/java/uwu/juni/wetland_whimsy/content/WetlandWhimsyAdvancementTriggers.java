package uwu.juni.wetland_whimsy.content;

import java.util.function.Supplier;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.advancements.AncientPotTrigger;

public class WetlandWhimsyAdvancementTriggers {
	public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = WetlandWhimsy.registry(
		Registries.TRIGGER_TYPE
	);

	public static final Supplier<AncientPotTrigger> ANCIENT_POT_TRIGGER = TRIGGERS.register(
		"ancient_pot", 
		AncientPotTrigger::new
	);
}
