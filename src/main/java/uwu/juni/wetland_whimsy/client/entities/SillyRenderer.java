package uwu.juni.wetland_whimsy.client.entities;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.SillyEntity;

public class SillyRenderer extends MobRenderer<SillyEntity, SillyModel<SillyEntity>> {

	public SillyRenderer(EntityRendererProvider.Context context) {
		super(
			context, 
			new SillyModel<>(context.bakeLayer(SillyModel.LAYER_LOCATION)), 
			.7F
		);
	}
	
	@Override
	public ResourceLocation getTextureLocation(@Nonnull SillyEntity entity) {
		return ResourceLocation.fromNamespaceAndPath(
			WetlandWhimsy.MODID, 
			"textures/entity/silly.png"
		);
	}

	@Override
	public void render(
		@Nonnull SillyEntity entity, 
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
