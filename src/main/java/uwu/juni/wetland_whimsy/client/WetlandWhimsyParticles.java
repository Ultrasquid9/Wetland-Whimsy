package uwu.juni.wetland_whimsy.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.client.particles.ancientsouls.AncientSoulsProvider;
import uwu.juni.wetland_whimsy.client.particles.bloodcapspores.BloodcapSporesProvider;
import uwu.juni.wetland_whimsy.client.particles.coloredfire.ColoredFireProvider;
import uwu.juni.wetland_whimsy.client.particles.muddrip.MudDripProvider;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;

@EventBusSubscriber(bus = Bus.MOD, value = Dist.CLIENT, modid = WetlandWhimsy.MODID)
public class WetlandWhimsyParticles {
	@SubscribeEvent
	public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(
			WetlandWhimsyParticleTypes.ANCIENT_SOULS.get(), 
			AncientSoulsProvider::new
		);
		event.registerSpriteSet(
			WetlandWhimsyParticleTypes.BLOODCAP_SPORES.get(), 
			BloodcapSporesProvider::new
		);
		event.registerSpriteSet(
			WetlandWhimsyParticleTypes.MUD_DRIP.get(), 
			MudDripProvider::new
		);
		event.registerSpriteSet(
			WetlandWhimsyParticleTypes.COLORED_FIRE.get(), 
			ColoredFireProvider::new
		);
	}
}
