package uwu.juni.wetland_whimsy.client.entities.swampspider;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.SwampSpiderEntity;

public class SwampSpiderRenderer extends MobRenderer<SwampSpiderEntity, SwampSpiderModel<SwampSpiderEntity>> {

	public SwampSpiderRenderer(EntityRendererProvider.Context context) {
		super(
			context, 
			new SwampSpiderModel<>(context.bakeLayer(SwampSpiderModel.LAYER_LOCATION)), 
			.85F
		);

		this.addLayer(new SwampSpiderGlowLayer<>(this));
	}
	
	@Override
	public ResourceLocation getTextureLocation(@Nonnull SwampSpiderEntity entity) {
		return WetlandWhimsy.rLoc("textures/entity/swamp_spider.png");
	}

	@Override
	public void render(
		@Nonnull SwampSpiderEntity entity, 
		float entityYaw, 
		float partialTicks, 
		@Nonnull PoseStack poseStack,
		@Nonnull MultiBufferSource buffer, 
		int packedLight
	) {
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}
}
