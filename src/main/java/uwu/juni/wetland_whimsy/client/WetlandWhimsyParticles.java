package uwu.juni.wetland_whimsy.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import uwu.juni.wetland_whimsy.client.particles.AncientSoulsProvider;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;

@OnlyIn(Dist.CLIENT)
public class WetlandWhimsyParticles {
	public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(
			WetlandWhimsyParticleTypes.ANCIENT_SOULS.get(), 
			AncientSoulsProvider::new
		);
	}
}
