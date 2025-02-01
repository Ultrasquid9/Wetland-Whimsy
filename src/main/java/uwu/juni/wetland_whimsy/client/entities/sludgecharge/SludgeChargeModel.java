package uwu.juni.wetland_whimsy.client.entities.sludgecharge;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.SludgeChargeEntity;

@SuppressWarnings("unused")
public class SludgeChargeModel extends EntityModel<SludgeChargeEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(WetlandWhimsy.rLoc("sludge_charge"), "main");
	private final ModelPart bb_main;

	public SludgeChargeModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -27.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(
		@Nonnull SludgeChargeEntity entity, 
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

	public ModelPart root() {
		return bb_main;
	}
}
