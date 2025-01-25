package uwu.juni.wetland_whimsy.datapacks;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import uwu.juni.wetland_whimsy.WetlandWhimsy;

public class Datapacks {
	public static final ResourceKey<Registry<ScalableReward>> SCALABLE_REWARD = ResourceKey.createRegistryKey(
		WetlandWhimsy.rLoc("scalable_reward")
	);

	public static void datapackRegistry(DataPackRegistryEvent.NewRegistry event) {
		event.dataPackRegistry(SCALABLE_REWARD, ScalableReward.CODEC);
	}
}
