package uwu.juni.wetland_whimsy.client.renderers.blockentities;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SpawnerRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.phys.AABB;
import uwu.juni.wetland_whimsy.content.blocks.entities.AncientBrazierBlockEntity;

public class AncientBrazierRenderer implements BlockEntityRenderer<AncientBrazierBlockEntity> {
	private final EntityRenderDispatcher entityRenderer;

	public AncientBrazierRenderer(BlockEntityRendererProvider.Context context) {
		this.entityRenderer = context.getEntityRenderer();
	}

	@SuppressWarnings("null")
	public void render(
		AncientBrazierBlockEntity blockEntity, 
		float partialTick, 
		PoseStack poseStack, 
		MultiBufferSource bufferSource, 
		int packedLight, 
		int packedOverlay
	) {
		var level = blockEntity.getLevel();
		if (level == null) 
			return;

		var basespawner = blockEntity.getSpawner();
		var entity = basespawner.getOrCreateDisplayEntity(level, blockEntity.getBlockPos());
		if (entity == null) 
			return;

		SpawnerRenderer.renderEntityInSpawner(
			partialTick, 
			poseStack, 
			bufferSource, 
			packedLight, 
			entity, 
			this.entityRenderer, 
			basespawner.getoSpin(), 
			basespawner.getSpin()
		);		
	}

	@Override
	public AABB getRenderBoundingBox(@Nonnull AncientBrazierBlockEntity blockEntity) {
		var pos = blockEntity.getBlockPos();
		return new AABB(pos.getX() - 1.0, pos.getY() - 1.0, pos.getZ() - 1.0, pos.getX() + 2.0, pos.getY() + 2.0, pos.getZ() + 2.0);
	}
}
