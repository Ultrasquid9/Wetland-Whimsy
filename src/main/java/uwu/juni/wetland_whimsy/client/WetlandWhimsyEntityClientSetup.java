package uwu.juni.wetland_whimsy.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import uwu.juni.wetland_whimsy.client.entities.silly.SillyModel;
import uwu.juni.wetland_whimsy.client.entities.silly.SillyRenderer;
import uwu.juni.wetland_whimsy.client.entities.blemish.BlemishModel;
import uwu.juni.wetland_whimsy.client.entities.blemish.BlemishRenderer;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyEntityTypes;

@OnlyIn(Dist.CLIENT)
public class WetlandWhimsyEntityClientSetup {
	public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(
			SillyModel.LAYER_LOCATION, 
			SillyModel::createBodyLayer
		);
		event.registerLayerDefinition(
			BlemishModel.LAYER_LOCATION, 
			BlemishModel::createBodyLayer
		);
	}

	public static void entityRenderer(FMLClientSetupEvent event) {
		EntityRenderers.register(
			WetlandWhimsyEntityTypes.SILLY_ENTITY.get(), 
			SillyRenderer::new
		);
		EntityRenderers.register(
			WetlandWhimsyEntityTypes.BLEMISH.get(), 
			BlemishRenderer::new
		);
	}
}
