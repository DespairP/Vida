package teamHTBP.vida.modelRender.tilemodel;// Made with Blockbench 3.9.3
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.modelRender.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class InjectTableModel extends TileEntityModel {
	private final ModelPart all;
	private final ModelPart group;
	public final ModelPart  rotateCube;
	private final ModelPart group2;
	private final ModelPart cube_r1;

	public InjectTableModel(ModelPart part) {
		super(part);

		all = part.getChild("all");
		group = part.getChild("group");
		rotateCube = part.getChild("rotateCube");
		group2 = part.getChild("group2");
		cube_r1 = part.getChild("cube_r1");
	}


	public static LayerDefinition createBodyLayer() {
		return ModelHelper.createBodyLayer(partDefinition -> {
			var all = partDefinition.addOrReplaceChild("all",
					CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

			var group = all.addOrReplaceChild("group",
					CubeListBuilder.create().texOffs(8, 18).addBox(-7.0F, 6.0F, -7.0F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
							.texOffs(12, 34).addBox(-6.5F, 5.5F, -6.5F, 13.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
							.texOffs(12, 34).addBox(-6.5F, -0.25F, -6.5F, 13.0F, 3.0F, 13.0F, new CubeDeformation(0.0F))
							.texOffs(16, 5).addBox(-6.0F, -0.75F, -6.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
							.texOffs(0, 58).addBox(4.0F, 1.75F, 4.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
							.texOffs(0, 58).addBox(-6.0F, 1.75F, 4.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
							.texOffs(0, 58).addBox(-6.0F, 1.75F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
							.texOffs(0, 58).addBox(4.0F, 1.75F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
							.texOffs(28, 50).addBox(-4.5F, -2.0F, -4.5F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
					,PartPose.offset(0.0F, -8.0F, 0.0F));

			var rotateCube = group.addOrReplaceChild("rotateCube",
					CubeListBuilder.create().texOffs(8, 55).addBox(-3.0F, -4.5F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
					,PartPose.offset(0.0F, 0.0F, 0.0F));

			var group2 = all.addOrReplaceChild("group2",
					CubeListBuilder.create(),PartPose.offset(0.0F, -8.0F, 0.0F));

			var cube_r1 = group2.addOrReplaceChild("cube_r1",
					CubeListBuilder.create().texOffs(0, 42).addBox(-1.5F, -3.0F, -7.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
							.texOffs(0, 42).addBox(-7.0F, -3.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
							.texOffs(0, 42).addBox(-1.5F, -3.0F, 4.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
							.texOffs(0, 42).addBox(4.0F, -3.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
					,PartPose.offsetAndRotation(0.0F, -2.25F, 0.0F, 0.0F, -0.7854F, 0.0F));


		});
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		group.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		group2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		cube_r1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}