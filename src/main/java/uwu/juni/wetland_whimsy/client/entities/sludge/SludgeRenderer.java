package uwu.juni.wetland_whimsy.client.entities.sludge;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.SludgeEntity;

public class SludgeRenderer extends MobRenderer<SludgeEntity, SludgeModel<SludgeEntity>> {

	public SludgeRenderer(EntityRendererProvider.Context context) {
		super(
			context, 
			new SludgeModel<>(context.bakeLayer(SludgeModel.LAYER_LOCATION)), 
			.45F
		);
	}
	
	@Override
	public ResourceLocation getTextureLocation(@Nonnull SludgeEntity entity) {
		return WetlandWhimsy.rLoc("textures/entity/sludge.png");
	}

	@Override
	public void render(
		@Nonnull SludgeEntity entity, 
		float entityYaw, 
		float partialTicks, 
		@Nonnull PoseStack poseStack,
		@Nonnull MultiBufferSource buffer, 
		int packedLight
	) {
		if (entity.isBaby()) 
			poseStack.scale(1.2F, 0.5F, 1.2F);

		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}
}
