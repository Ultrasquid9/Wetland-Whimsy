package uwu.juni.wetland_whimsy.client.entities.swampspider;

// Made with Blockbench 4.11.2
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
import uwu.juni.wetland_whimsy.content.entities.SwampSpiderEntity;

@SuppressWarnings("unused")
public class SwampSpiderModel<T extends SwampSpiderEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(WetlandWhimsy.rLoc("swamp_spider"), "main");
	private final ModelPart model_base;
	private final ModelPart head;
	private final ModelPart tail;
	private final ModelPart mushrooms;
	private final ModelPart left_legs;
	private final ModelPart front;
	private final ModelPart center;
	private final ModelPart back;
	private final ModelPart right_legs;
	private final ModelPart front2;
	private final ModelPart center2;
	private final ModelPart back2;

	public SwampSpiderModel(ModelPart root) {
		this.model_base = root.getChild("model_base");
		this.head = this.model_base.getChild("head");
		this.tail = this.model_base.getChild("tail");
		this.mushrooms = this.tail.getChild("mushrooms");
		this.left_legs = this.model_base.getChild("left_legs");
		this.front = this.left_legs.getChild("front");
		this.center = this.left_legs.getChild("center");
		this.back = this.left_legs.getChild("back");
		this.right_legs = this.model_base.getChild("right_legs");
		this.front2 = this.right_legs.getChild("front2");
		this.center2 = this.right_legs.getChild("center2");
		this.back2 = this.right_legs.getChild("back2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition model_base = partdefinition.addOrReplaceChild("model_base", CubeListBuilder.create().texOffs(56, 32).addBox(-3.0F, -5.0F, -9.0F, 6.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 5.0F));

		PartDefinition head = model_base.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 64).addBox(-4.0F, -7.0F, -9.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.3F))
		.texOffs(56, 50).addBox(-4.0F, -7.0F, -9.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -8.0F));

		PartDefinition tail = model_base.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -14.0F, 0.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition mushrooms = tail.addOrReplaceChild(
			"mushrooms", 
			CubeListBuilder.create()
				.texOffs(0, 32).addBox(-14.0F, -14.0F, 3.0F, 28.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 48).addBox(-14.0F, -14.0F, 13.0F, 28.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), 
			PartPose.offsetAndRotation(0, 0, 0, 0, 0, 0)
		);

		PartDefinition shroom_cross_2_r1 = mushrooms.addOrReplaceChild("shroom_cross_2_r1", CubeListBuilder.create().texOffs(32, 64).addBox(-5.0F, -10.0F, 0.0F, 10.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -14.0F, 11.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition shroom_cross_1_r1 = mushrooms.addOrReplaceChild("shroom_cross_1_r1", CubeListBuilder.create().texOffs(32, 64).addBox(-5.0F, -10.0F, 0.0F, 10.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -14.0F, 11.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition left_legs = model_base.addOrReplaceChild("left_legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition front = left_legs.addOrReplaceChild("front", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition front_r1 = front.addOrReplaceChild("front_r1", CubeListBuilder.create().texOffs(64, 0).addBox(0.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -1.0F, -7.0F, 0.0F, 0.2618F, 0.48F));

		PartDefinition center = left_legs.addOrReplaceChild("center", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition center_r1 = center.addOrReplaceChild("center_r1", CubeListBuilder.create().texOffs(64, 0).addBox(0.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -1.0F, -5.0F, 0.0F, 0.0F, 0.48F));

		PartDefinition back = left_legs.addOrReplaceChild("back", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition back_r1 = back.addOrReplaceChild("back_r1", CubeListBuilder.create().texOffs(64, 0).addBox(0.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -1.0F, -3.0F, 0.0F, -0.2618F, 0.48F));

		PartDefinition right_legs = model_base.addOrReplaceChild("right_legs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -5.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition front2 = right_legs.addOrReplaceChild("front2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 5.0F));

		PartDefinition front_r2 = front2.addOrReplaceChild("front_r2", CubeListBuilder.create().texOffs(64, 0).addBox(0.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -1.0F, -7.0F, 0.0F, 0.2618F, 0.48F));

		PartDefinition center2 = right_legs.addOrReplaceChild("center2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 5.0F));

		PartDefinition center_r2 = center2.addOrReplaceChild("center_r2", CubeListBuilder.create().texOffs(64, 0).addBox(0.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -1.0F, -5.0F, 0.0F, 0.0F, 0.48F));

		PartDefinition back2 = right_legs.addOrReplaceChild("back2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 5.0F));

		PartDefinition back_r2 = back2.addOrReplaceChild("back_r2", CubeListBuilder.create().texOffs(64, 0).addBox(0.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -1.0F, -3.0F, 0.0F, -0.2618F, 0.48F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(
		@Nonnull SwampSpiderEntity entity, 
		float limbSwing, 
		float limbSwingAmount, 
		float ageInTicks, 
		float netHeadYaw, 
		float headPitch
	) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch);

		this.animateWalk(SwampSpiderAnimations.SWAMP_SPIDER_WALK, limbSwing, limbSwingAmount, 1.5f, 2.5f);
		this.animate(entity.idleAnimationState, SwampSpiderAnimations.SWAMP_SPIDER_IDLE, ageInTicks, 1f);
	}

	private void applyHeadRotation(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -30f, 30f);
		headPitch = Mth.clamp(headPitch, -25f, 45);

		this.head.yRot = headYaw * ((float)Math.PI / 180f);
		this.head.xRot = headPitch * ((float)Math.PI / 180f);
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

	@Override
	public void prepareMobModel(
		@Nonnull SwampSpiderEntity entity, 
		float limbSwing, 
		float limbSwingAmount, 
		float partialTick
	) {
		this.mushrooms.visible = !entity.isSheared();
	}
}