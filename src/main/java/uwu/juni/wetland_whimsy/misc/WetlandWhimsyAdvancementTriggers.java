package uwu.juni.wetland_whimsy.misc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.advancements.AncientPotTrigger;

@EventBusSubscriber(modid = WetlandWhimsy.MODID, bus = EventBusSubscriber.Bus.MOD)
public class WetlandWhimsyAdvancementTriggers {
	private static List<CriterionTrigger<? extends CriterionTriggerInstance>> registryQueue = new ArrayList<>();

	public static final AncientPotTrigger ANCIENT_POT_TRIGGER = (AncientPotTrigger)createTrigger(new AncientPotTrigger());

	private static CriterionTrigger<?> createTrigger(CriterionTrigger<?> trigger) {
		registryQueue.add(trigger);
		return trigger;
	}

	@SubscribeEvent
	static void advancementTriggers(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			for (var trigger : registryQueue) {
				CriteriaTriggers.register(trigger);
			}

			// The list isn't needed anymore, so we can free the memory inside 
			registryQueue = null;
		});
	}
}
