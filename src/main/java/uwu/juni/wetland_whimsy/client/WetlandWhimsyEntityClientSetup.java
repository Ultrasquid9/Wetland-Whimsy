package uwu.juni.wetland_whimsy.client;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.client.entities.blemish.BlemishModel;
import uwu.juni.wetland_whimsy.client.entities.blemish.BlemishRenderer;
import uwu.juni.wetland_whimsy.client.entities.boat.BaldCypressBoatRenderer;
import uwu.juni.wetland_whimsy.client.entities.bullet.BulletModel;
import uwu.juni.wetland_whimsy.client.entities.bullet.BulletRenderer;
import uwu.juni.wetland_whimsy.client.entities.crane.CraneModel;
import uwu.juni.wetland_whimsy.client.entities.crane.CraneRenderer;
import uwu.juni.wetland_whimsy.client.entities.silly.SillyModel;
import uwu.juni.wetland_whimsy.client.entities.silly.SillyRenderer;
import uwu.juni.wetland_whimsy.client.entities.sludgecharge.SludgeChargeModel;
import uwu.juni.wetland_whimsy.client.entities.sludgecharge.SludgeChargeRenderer;
import uwu.juni.wetland_whimsy.client.entities.swampspider.SwampSpiderModel;
import uwu.juni.wetland_whimsy.client.entities.swampspider.SwampSpiderRenderer;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;

@EventBusSubscriber(bus = Bus.MOD, value = Dist.CLIENT, modid = WetlandWhimsy.MODID)
public class WetlandWhimsyEntityClientSetup {
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
			BulletModel.LAYER_LOCATION, 
			BulletModel::createBodyLayer
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
			WetlandWhimsyEntityTypes.BULLET.get(), 
			BulletRenderer::new
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
}
