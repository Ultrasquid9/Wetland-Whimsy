package uwu.juni.wetland_whimsy.client.entities.bullet;

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
import uwu.juni.wetland_whimsy.content.entities.BulletEntity;

public class BulletRenderer extends EntityRenderer<BulletEntity> {
	private static final double MIN_CAMERA_DISTANCE_SQUARED = Mth.square(3.5F);
	private static final ResourceLocation RLOC = WetlandWhimsy.rLoc("textures/entity/bullet.png");

	private BulletModel model;

	public BulletRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new BulletModel(context.bakeLayer(BulletModel.LAYER_LOCATION));
	}
	
	@Override
	public ResourceLocation getTextureLocation(@Nonnull BulletEntity entity) {
		return RLOC;
	}

	public void render(
		@Nonnull BulletEntity entity, 
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
