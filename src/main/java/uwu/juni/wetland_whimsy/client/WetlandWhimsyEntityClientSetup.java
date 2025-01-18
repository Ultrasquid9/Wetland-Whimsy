package uwu.juni.wetland_whimsy.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import uwu.juni.wetland_whimsy.client.entities.SillyModel;

@OnlyIn(Dist.CLIENT)
public class WetlandWhimsyEntityClientSetup {
	public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(
			SillyModel.LAYER_LOCATION, 
			SillyModel::createBodyLayer
		);
	}
}
