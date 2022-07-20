package teamHTBP.vida.modelRender.tilemodel;// Made with Blockbench 3.9.3
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;

public class InjectTableModel extends TileEntityModel {
	private final ModelPart all;
	private final ModelPart group;
	public final ModelPart rotateCube;
	private final ModelPart group2;
	private final ModelPart cube_r1;

	public InjectTableModel() {
		super();
		texWidth = 64;
		texHeight = 64;

		all = new ModelPart(this);
		all.setPos(0.0F, 24.0F, 0.0F);
		

		group = new ModelPart(this);
		group.setPos(0.0F, -8.0F, 0.0F);
		all.addChild(group);
		group.texOffs(8, 18).addBox(-7.0F, 6.0F, -7.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);
		group.texOffs(12, 34).addBox(-6.5F, 5.5F, -6.5F, 13.0F, 1.0F, 13.0F, 0.0F, false);
		group.texOffs(12, 34).addBox(-6.5F, -0.25F, -6.5F, 13.0F, 3.0F, 13.0F, 0.0F, false);
		group.texOffs(16, 5).addBox(-6.0F, -0.75F, -6.0F, 12.0F, 1.0F, 12.0F, 0.0F, false);
		group.texOffs(0, 58).addBox(4.0F, 1.75F, 4.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		group.texOffs(0, 58).addBox(-6.0F, 1.75F, 4.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		group.texOffs(0, 58).addBox(-6.0F, 1.75F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		group.texOffs(0, 58).addBox(4.0F, 1.75F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		group.texOffs(28, 50).addBox(-4.5F, -2.0F, -4.5F, 9.0F, 2.0F, 9.0F, 0.0F, false);

		rotateCube = new ModelPart(this);
		rotateCube.setPos(0.0F, 0.0F, 0.0F);
		group.addChild(rotateCube);
		rotateCube.texOffs(8, 55).addBox(-3.0F, -4.5F, -3.0F, 6.0F, 3.0F, 6.0F, 0.0F, false);

		group2 = new ModelPart(this);
		group2.setPos(0.0F, -8.0F, 0.0F);
		all.addChild(group2);
		

		cube_r1 = new ModelPart(this);
		cube_r1.setPos(0.0F, -2.25F, 0.0F);
		group2.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.7854F, 0.0F);
		cube_r1.texOffs(0, 42).addBox(-1.5F, -3.0F, -7.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
		cube_r1.texOffs(0, 42).addBox(-7.0F, -3.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);
		cube_r1.texOffs(0, 42).addBox(-1.5F, -3.0F, 4.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
		cube_r1.texOffs(0, 42).addBox(4.0F, -3.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);
	}


	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		group.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		group2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		cube_r1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}