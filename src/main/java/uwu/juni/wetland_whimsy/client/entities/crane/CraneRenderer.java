package uwu.juni.wetland_whimsy.client.entities.crane;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.CraneEntity;

public class CraneRenderer extends MobRenderer<CraneEntity, CraneModel> {

	public CraneRenderer(EntityRendererProvider.Context context) {
		super(
			context, 
			new CraneModel(context.bakeLayer(CraneModel.LAYER_LOCATION)), 
			.4F
		);
	}
	
	@Override
	public ResourceLocation getTextureLocation(@Nonnull CraneEntity entity) {
		return WetlandWhimsy.rLoc("textures/entity/crane.png");
	}

	@Override
	public void render(
		@Nonnull CraneEntity entity, 
		float entityYaw, 
		float partialTicks, 
		@Nonnull PoseStack poseStack,
		@Nonnull MultiBufferSource buffer, 
		int packedLight
	) {
		if (entity.isBaby()) 
			poseStack.scale(0.5F, 0.5F, 0.5F);

		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}
}
