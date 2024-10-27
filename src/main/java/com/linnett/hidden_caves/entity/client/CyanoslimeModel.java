package com.linnett.hidden_caves.entity.client;

import com.linnett.hidden_caves.entity.custom.CyanoslimeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CyanoslimeModel extends HierarchicalModel<CyanoslimeEntity> {

	private final ModelPart root;
	private final ModelPart slime;
	private final ModelPart nione;
	private final ModelPart nione2;
	private final ModelPart nione3;

	public CyanoslimeModel(ModelPart root) {
		this.root = root.getChild("root");
		this.slime = this.root.getChild("slime");
		this.nione = this.root.getChild("nione");
		this.nione2 = this.root.getChild("nione2");
		this.nione3 = this.root.getChild("nione3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition slime = root.addOrReplaceChild("slime", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -9.0F, -9.0F, 18.0F, 18.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition nione = root.addOrReplaceChild("nione", CubeListBuilder.create().texOffs(0, 36).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, -6.5F, 4.5F));

		PartDefinition nione2 = root.addOrReplaceChild("nione2", CubeListBuilder.create().texOffs(20, 36).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, -6.5F, -2.5F));

		PartDefinition nione3 = root.addOrReplaceChild("nione3", CubeListBuilder.create().texOffs(40, 36).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -13.5F, -1.5F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(CyanoslimeEntity cyanoslimeEntity, float v, float v1, float v2, float v3, float v4) {

	}
}