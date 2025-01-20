package uwu.juni.wetland_whimsy.client.entities.swampspider;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.SwampSpiderEntity;

public class SwampSpiderGlowLayer<T extends SwampSpiderEntity> extends EyesLayer<T, SwampSpiderModel<T>> {
	private static final RenderType SWAMP_SPIDER_GLOW = RenderType.eyes(
		WetlandWhimsy.rLoc("textures/entity/swamp_spider_glow.png")
	);

	public SwampSpiderGlowLayer(RenderLayerParent<T, SwampSpiderModel<T>> thing) {
		super(thing);
	}

	@Override
	public RenderType renderType() {
		return SWAMP_SPIDER_GLOW;
	}
}
