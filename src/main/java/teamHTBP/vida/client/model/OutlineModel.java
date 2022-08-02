package teamHTBP.vida.client.model;// Made with Blockbench 4.3.0
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

@AutoRegModel
public class OutlineModel extends EntityModel<Entity> {
	private final ModelPart bone;

	public OutlineModel(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(18, 18).addBox(-8.0F, -16.0F, -8.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(7.0F, -1.0F, -8.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(7.0F, -16.0F, -8.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(18, 1).addBox(-8.0F, -1.0F, -8.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(36, 2).addBox(-7.0F, -1.0F, -8.0F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 35).addBox(-7.0F, -16.0F, 7.0F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 35).addBox(-7.0F, -16.0F, -8.0F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 0).addBox(-7.0F, -1.0F, 7.0F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 0).addBox(7.0F, -15.0F, -8.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 0).addBox(-8.0F, -15.0F, -8.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 0).addBox(7.0F, -15.0F, 7.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-8.0F, -15.0F, 7.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(Entity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		// nothing to do
	}
}