package uwu.juni.wetland_whimsy.datapacks;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DataPackRegistryEvent;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

@EventBusSubscriber(modid = WetlandWhimsy.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Datapacks {
	public static final ResourceKey<Registry<ScalableReward>> SCALABLE_REWARD = ResourceKey.createRegistryKey(
		WetlandWhimsy.rLoc("scalable_reward")
	);

	@SubscribeEvent
	public static void datapackRegistry(DataPackRegistryEvent.NewRegistry event) {
		event.dataPackRegistry(SCALABLE_REWARD, ScalableReward.CODEC);
	}
}
