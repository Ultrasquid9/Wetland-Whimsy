package uwu.juni.wetland_whimsy.client.entities;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import uwu.juni.wetland_whimsy.content.entities.SillyEntity;

public class SillyModel<T extends SillyEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
		ResourceLocation.fromNamespaceAndPath("modid", "test_entity"), 
		"main"
	);
	private final ModelPart bb_main;

	public SillyModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild(
			"bb_main", 
			CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-8.0F, -21.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				
				.texOffs(0, 32)
				.addBox(-6.0F, -27.0F, -6.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				
				.texOffs(8, 32)
				.addBox(4.0F, -27.0F, -6.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), 
			
			PartPose.offset(0.0F, 24.0F, 0.0F)
		);

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(
		@Nonnull SillyEntity entity, 
		float limbSwing, 
		float limbSwingAmount, 
		float ageInTicks, 
		float netHeadYaw, 
		float headPitch
	) {}

	@Override
	public void renderToBuffer(
		@Nonnull PoseStack poseStack, 
		@Nonnull VertexConsumer vertexConsumer, 
		int packedLight, 
		int packedOverlay,
		int color
	) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return bb_main;
	}
}
