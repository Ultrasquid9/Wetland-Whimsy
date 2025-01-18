package uwu.juni.wetland_whimsy.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import uwu.juni.wetland_whimsy.client.entities.SillyRenderer;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;

@OnlyIn(Dist.CLIENT)
public class WetlandWhimsyClient {
	public static void clientBussin(IEventBus bussin) {
		bussin.addListener(WetlandWhimsyParticles::registerParticleProviders);
		bussin.addListener(WetlandWhimsyEntityClientSetup::registerLayers);

		bussin.addListener(WetlandWhimsyClient::clientSetup);
	}

	private static void clientSetup(FMLClientSetupEvent event) {
		EntityRenderers.register(
			WetlandWhimsyEntityTypes.SILLY_ENTITY.get(), 
			SillyRenderer::new
		);
	}
}
