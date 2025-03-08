package uwu.juni.wetland_whimsy.datapacks;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

@EventBusSubscriber(bus = Bus.MOD, modid = WetlandWhimsy.MODID)
public class Datapacks {
	public static final ResourceKey<Registry<ScalableReward>> SCALABLE_REWARD = ResourceKey.createRegistryKey(
		WetlandWhimsy.rLoc("scalable_reward")
	);
	public static final ResourceKey<Registry<Incense>> INCENSE = ResourceKey.createRegistryKey(
		WetlandWhimsy.rLoc("incense")
	);

	@SubscribeEvent
	public static void datapackRegistry(DataPackRegistryEvent.NewRegistry event) {
		event.dataPackRegistry(SCALABLE_REWARD, ScalableReward.CODEC);
		event.dataPackRegistry(INCENSE, Incense.CODEC);
	}
}
