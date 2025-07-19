package uwu.juni.wetland_whimsy.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.client.entities.crane.CraneModel;
import uwu.juni.wetland_whimsy.client.entities.crane.CraneRenderer;
import uwu.juni.wetland_whimsy.client.particles.AncientSoulsProvider;
import uwu.juni.wetland_whimsy.client.particles.BloodcapSporesProvider;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;

@EventBusSubscriber(value = Dist.CLIENT, modid = WetlandWhimsy.MODID, bus = EventBusSubscriber.Bus.MOD)
public class WetlandWhimsyClient {
	@SubscribeEvent
	public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(
			CraneModel.LAYER_LOCATION, 
			CraneModel::createBodyLayer
		);
	}

	@SubscribeEvent
	public static void entityRenderer(FMLClientSetupEvent event) {
		EntityRenderers.register(
			WetlandWhimsyEntityTypes.CRANE.get(), 
			CraneRenderer::new
		);
	}

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
