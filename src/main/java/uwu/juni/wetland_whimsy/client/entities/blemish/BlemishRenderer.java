package uwu.juni.wetland_whimsy.client.entities.blemish;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.BlemishEntity;

public class BlemishRenderer extends MobRenderer<BlemishEntity, BlemishModel<BlemishEntity>> {

	public BlemishRenderer(EntityRendererProvider.Context context) {
		super(
			context, 
			new BlemishModel<>(context.bakeLayer(BlemishModel.LAYER_LOCATION)), 
			.45F
		);
	}
	
	@Override
	public ResourceLocation getTextureLocation(@Nonnull BlemishEntity entity) {
		return WetlandWhimsy.rLoc("textures/entity/blemish.png");
	}

	@Override
	public void render(
		@Nonnull BlemishEntity entity, 
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
