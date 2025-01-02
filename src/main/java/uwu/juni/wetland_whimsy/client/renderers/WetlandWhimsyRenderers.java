package uwu.juni.wetland_whimsy.client.renderers;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import uwu.juni.wetland_whimsy.client.renderers.blockentities.AncientBrazierRenderer;
import uwu.juni.wetland_whimsy.content.WetlandWhimsyBlockEntities;

public class WetlandWhimsyRenderers {
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(
			WetlandWhimsyBlockEntities.ANCIENT_BRAZIER.get(),
			AncientBrazierRenderer::new
		);
	}
}
