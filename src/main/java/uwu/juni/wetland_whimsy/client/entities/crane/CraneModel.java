package uwu.juni.wetland_whimsy.client.entities.crane;

// Made with Blockbench 4.12.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

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
import net.minecraft.util.Mth;
import uwu.juni.wetland_whimsy.WetlandWhimsy;
import uwu.juni.wetland_whimsy.content.entities.CraneEntity;

@SuppressWarnings("unused")
public class CraneModel extends HierarchicalModel<CraneEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(WetlandWhimsy.rLoc("crane"), "main");
	private final ModelPart crane;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	private final ModelPart body;
	private final ModelPart left_wing;
	private final ModelPart evil;

	public CraneModel(ModelPart root) {
		this.crane = root.getChild("crane");
		this.neck = this.crane.getChild("neck");
		this.head = this.neck.getChild("head");
		this.left_leg = this.crane.getChild("left_leg");
		this.right_leg = this.crane.getChild("right_leg");
		this.body = this.crane.getChild("body");
		this.left_wing = this.body.getChild("left_wing");
		this.evil = this.body.getChild("evil");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition crane = partdefinition.addOrReplaceChild("crane", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 0.0F));

		PartDefinition neck = crane.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, -4.0F));

		PartDefinition cube_r1 = neck.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(44, 10).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(18, 46).addBox(0.0F, -2.0F, -6.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 40).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 1.0F));

		PartDefinition left_leg = crane.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition lower_r1 = left_leg.addOrReplaceChild("lower_r1", CubeListBuilder.create().texOffs(26, 46).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 8.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition upper_r1 = left_leg.addOrReplaceChild("upper_r1", CubeListBuilder.create().texOffs(38, 40).addBox(-2.0F, -6.0F, -1.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 3.0F, 1.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition right_leg = crane.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition upper_r2 = right_leg.addOrReplaceChild("upper_r2", CubeListBuilder.create().texOffs(44, 0).addBox(-2.0F, -6.0F, -1.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 3.0F, 1.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition lower_r2 = right_leg.addOrReplaceChild("lower_r2", CubeListBuilder.create().texOffs(30, 46).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 8.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition body = crane.addOrReplaceChild("body", CubeListBuilder.create().texOffs(18, 40).addBox(-2.0F, -9.0F, 8.0F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -8.0F, -1.0F, 8.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -12.0F, -5.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition left_wing = body.addOrReplaceChild("left_wing", CubeListBuilder.create(), PartPose.offset(4.0F, -18.0F, -2.0F));

		PartDefinition cube_r2 = left_wing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 22).addBox(0.0F, -0.6232F, -0.6995F, 1.0F, 6.0F, 12.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition evil = body.addOrReplaceChild("evil", CubeListBuilder.create(), PartPose.offset(-4.0F, -18.0F, -2.0F));

		PartDefinition cube_r3 = evil.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(26, 22).addBox(0.0F, -0.6232F, -0.6995F, 1.0F, 6.0F, 12.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}


	@Override
	public void setupAnim(
		@Nonnull CraneEntity entity, 
		float limbSwing, 
		float limbSwingAmount, 
		float ageInTicks, 
		float netHeadYaw, 
		float headPitch
	) {
		crane.getAllParts().forEach(ModelPart::resetPose);
		applyHeadRotation(netHeadYaw, headPitch);

		animateWalk(CraneAnimations.CRANE_WALK, limbSwing, limbSwingAmount, 1f, 1f);
		animate(entity.idleAnimationState, CraneAnimations.CRANE_IDLE, ageInTicks, 1f);
	}

	private void applyHeadRotation(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -30f, 30f);
		headPitch = Mth.clamp(headPitch, -25f, 45);

		this.head.yRot = headYaw * ((float)Math.PI / 180f);
		this.head.xRot = headPitch * ((float)Math.PI / 180f);
	}

	@Override
	public ModelPart root() {
		return crane;
	}

	@Override
	public void renderToBuffer(
		@Nonnull PoseStack poseStack, 
		@Nonnull VertexConsumer vertexConsumer, 
		int packedLight, 
		int packedOverlay,
		int color
	) {
		crane.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}