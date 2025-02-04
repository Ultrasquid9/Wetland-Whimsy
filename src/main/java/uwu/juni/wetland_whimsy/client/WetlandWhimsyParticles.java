package uwu.juni.wetland_whimsy.client;

import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import uwu.juni.wetland_whimsy.client.particles.AncientSoulsProvider;
import uwu.juni.wetland_whimsy.client.particles.BloodcapSporesProvider;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;

public class WetlandWhimsyParticles {
	public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(
			WetlandWhimsyParticleTypes.ANCIENT_SOULS.get(), 
			AncientSoulsProvider::new
		);
		event.registerSpriteSet(
			WetlandWhimsyParticleTypes.BLOODCAP_SPORES.get(), 
			BloodcapSporesProvider::new
		);
	}
}
