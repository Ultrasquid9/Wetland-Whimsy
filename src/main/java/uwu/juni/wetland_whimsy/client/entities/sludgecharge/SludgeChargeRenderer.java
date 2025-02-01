package uwu.juni.wetland_whimsy.client.entities.sludgecharge;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.SludgeChargeEntity;

public class SludgeChargeRenderer extends EntityRenderer<SludgeChargeEntity> {
	private static final double MIN_CAMERA_DISTANCE_SQUARED = Mth.square(3.5F);
	private static final ResourceLocation RLOC = WetlandWhimsy.rLoc("textures/entity/sludge_charge.png");

	private SludgeChargeModel model;

	public SludgeChargeRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new SludgeChargeModel(context.bakeLayer(SludgeChargeModel.LAYER_LOCATION));
	}
	
	@Override
	public ResourceLocation getTextureLocation(@Nonnull SludgeChargeEntity entity) {
		return RLOC;
	}

	public void render(
		@Nonnull SludgeChargeEntity entity, 
		float entityYaw, 
		float partialTick, 
		@Nonnull PoseStack poseStack, 
		@Nonnull MultiBufferSource bufferSource, 
		int packedLight
	) {
		if (entity.tickCount >= 2 || !(entityRenderDispatcher.camera.getEntity().distanceToSqr(entity) < MIN_CAMERA_DISTANCE_SQUARED)) {
			var vcon = bufferSource.getBuffer(RenderType.entityCutout(RLOC));

			model.renderToBuffer(poseStack, vcon, packedLight, OverlayTexture.NO_OVERLAY);
			super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		}
	}
}
