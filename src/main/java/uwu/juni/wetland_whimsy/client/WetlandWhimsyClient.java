package uwu.juni.wetland_whimsy.client;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.client.entities.blemish.BlemishModel;
import uwu.juni.wetland_whimsy.client.entities.blemish.BlemishRenderer;
import uwu.juni.wetland_whimsy.client.entities.boat.BaldCypressBoatRenderer;
import uwu.juni.wetland_whimsy.client.entities.crane.CraneModel;
import uwu.juni.wetland_whimsy.client.entities.crane.CraneRenderer;
import uwu.juni.wetland_whimsy.client.entities.silly.SillyModel;
import uwu.juni.wetland_whimsy.client.entities.silly.SillyRenderer;
import uwu.juni.wetland_whimsy.client.entities.sludgecharge.SludgeChargeModel;
import uwu.juni.wetland_whimsy.client.entities.sludgecharge.SludgeChargeRenderer;
import uwu.juni.wetland_whimsy.client.entities.swampspider.SwampSpiderModel;
import uwu.juni.wetland_whimsy.client.entities.swampspider.SwampSpiderRenderer;
import uwu.juni.wetland_whimsy.client.particles.ancientsouls.AncientSoulsProvider;
import uwu.juni.wetland_whimsy.client.particles.bloodcapspores.BloodcapSporesProvider;
import uwu.juni.wetland_whimsy.client.particles.coloredfire.ColoredFireProvider;
import uwu.juni.wetland_whimsy.client.particles.muddrip.MudDripProvider;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyParticleTypes;

@EventBusSubscriber(value = Dist.CLIENT, modid = WetlandWhimsy.MODID)
public class WetlandWhimsyClient {
	@SubscribeEvent
	public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(
			SillyModel.LAYER_LOCATION, 
			SillyModel::createBodyLayer
		);
		event.registerLayerDefinition(
			CraneModel.LAYER_LOCATION, 
			CraneModel::createBodyLayer
		);
		event.registerLayerDefinition(
			BlemishModel.LAYER_LOCATION, 
			BlemishModel::createBodyLayer
		);
		event.registerLayerDefinition(
			SwampSpiderModel.LAYER_LOCATION, 
			SwampSpiderModel::createBodyLayer
		);

		event.registerLayerDefinition(
			SludgeChargeModel.LAYER_LOCATION, 
			SludgeChargeModel::createBodyLayer
		);

		event.registerLayerDefinition(
			new ModelLayerLocation(WetlandWhimsy.rLoc("boat/bald_cypress"), "main"), 
			BoatModel::createBodyModel
		);
		event.registerLayerDefinition(
			new ModelLayerLocation(WetlandWhimsy.rLoc("chest_boat/bald_cypress"), "main"), 
			ChestBoatModel::createBodyModel
		);
	}

	@SubscribeEvent
	public static void entityRenderer(FMLClientSetupEvent event) {
		EntityRenderers.register(
			WetlandWhimsyEntityTypes.SILLY_ENTITY.get(), 
			SillyRenderer::new
		);
		EntityRenderers.register(
			WetlandWhimsyEntityTypes.CRANE.get(), 
			CraneRenderer::new
		);
		EntityRenderers.register(
			WetlandWhimsyEntityTypes.BLEMISH.get(), 
			BlemishRenderer::new
		);
		EntityRenderers.register(
			WetlandWhimsyEntityTypes.SWAMP_SPIDER.get(), 
			SwampSpiderRenderer::new
		);

		EntityRenderers.register(
			WetlandWhimsyEntityTypes.SLUDGE_CHARGE.get(), 
			SludgeChargeRenderer::new
		);

		EntityRenderers.register(
			WetlandWhimsyEntityTypes.BALD_CYPRESS_BOAT.get(), 
			context -> new BaldCypressBoatRenderer(context, false)
		);
		EntityRenderers.register(
			WetlandWhimsyEntityTypes.BALD_CYPRESS_CHEST_BOAT.get(), 
			context -> new BaldCypressBoatRenderer(context, true)
		);
	}

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
