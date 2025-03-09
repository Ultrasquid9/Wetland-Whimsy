package uwu.juni.wetland_whimsy.client.entities.blemish;

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
import uwu.juni.wetland_whimsy.content.entities.BlemishEntity;

@SuppressWarnings("unused")
public class BlemishModel<T extends BlemishEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(WetlandWhimsy.rLoc("blemish"), "main");
	private final ModelPart model_base;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart tail;

	public BlemishModel(ModelPart root) {
		this.model_base = root.getChild("model_base");
		this.left_arm = this.model_base.getChild("left_arm");
		this.right_arm = this.model_base.getChild("right_arm");
		this.body = this.model_base.getChild("body");
		this.head = this.model_base.getChild("head");
		this.tail = this.model_base.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition model_base = partdefinition.addOrReplaceChild("model_base", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition left_arm = model_base.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition left_arm_r1 = left_arm.addOrReplaceChild("left_arm_r1", CubeListBuilder.create().texOffs(32, 23).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 8.0F, -1.0F, -0.0873F, 0.0F, -0.1745F));

		PartDefinition right_arm = model_base.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition right_arm_r1 = right_arm.addOrReplaceChild("right_arm_r1", CubeListBuilder.create().texOffs(32, 23).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 8.0F, -1.0F, -0.0873F, 0.0F, 0.1745F));

		PartDefinition body = model_base.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition rib_large_left_r1 = body.addOrReplaceChild("rib_large_left_r1", CubeListBuilder.create().texOffs(34, 12).addBox(-3.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(34, 12).mirror().addBox(-11.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, -5.0F, 2.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition rib_small_left_r1 = body.addOrReplaceChild("rib_small_left_r1", CubeListBuilder.create().texOffs(35, 8).addBox(-2.0F, -1.75F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(35, 8).mirror().addBox(-9.0F, -1.75F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, -3.0F, 5.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(2, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 1.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition head = model_base.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 33).addBox(-4.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.1F))
		.texOffs(11, 33).addBox(-4.0F, -5.0F, -13.0F, 8.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition tail = model_base.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(12, 2).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 13.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(
		@Nonnull BlemishEntity entity, 
		float limbSwing, 
		float limbSwingAmount, 
		float ageInTicks, 
		float netHeadYaw, 
		float headPitch
	) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch);

		this.animateWalk(BlemishAnimations.BLEMISH_WALK, limbSwing, limbSwingAmount, 3.5f, 2.5f);
		this.animate(entity.idleAnimationState, BlemishAnimations.BLEMISH_IDLE, ageInTicks, 1f);
	}

	private void applyHeadRotation(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -30f, 30f);
		headPitch = Mth.clamp(headPitch, -25f, 45);

		this.head.yRot = headYaw * ((float)Math.PI / 180f);
		this.head.xRot = headPitch * ((float)Math.PI / 180f);
		this.head.zRot = headPitch * ((float)Math.PI / 180f);
	}

	@Override
	public void renderToBuffer(
		@Nonnull PoseStack poseStack, 
		@Nonnull VertexConsumer vertexConsumer, 
		int packedLight, 
		int packedOverlay,
		int color
	) {
		model_base.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	public ModelPart root() {
		return model_base;
	}
}
