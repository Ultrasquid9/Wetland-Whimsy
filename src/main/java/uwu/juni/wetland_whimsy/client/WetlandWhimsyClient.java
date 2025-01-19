package uwu.juni.wetland_whimsy.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;

@OnlyIn(Dist.CLIENT)
public class WetlandWhimsyClient {
	public static void clientBussin(IEventBus bussin) {
		bussin.addListener(WetlandWhimsyParticles::registerParticleProviders);
		bussin.addListener(WetlandWhimsyEntityClientSetup::registerLayers);

		bussin.addListener(WetlandWhimsyEntityClientSetup::entityRenderer);
	}
}
