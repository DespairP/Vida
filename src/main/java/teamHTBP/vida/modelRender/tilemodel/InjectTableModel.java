package teamHTBP.vida.modelRender.tilemodel;// Made with Blockbench 3.9.3
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class InjectTableModel extends TileEntityModel {
	private final ModelRenderer all;
	private final ModelRenderer group;
	public final ModelRenderer rotateCube;
	private final ModelRenderer group2;
	private final ModelRenderer cube_r1;

	public InjectTableModel() {
		super();
		textureWidth = 64;
		textureHeight = 64;

		all = new ModelRenderer(this);
		all.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		group = new ModelRenderer(this);
		group.setRotationPoint(0.0F, -8.0F, 0.0F);
		all.addChild(group);
		group.setTextureOffset(8, 18).addBox(-7.0F, 6.0F, -7.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);
		group.setTextureOffset(12, 34).addBox(-6.5F, 5.5F, -6.5F, 13.0F, 1.0F, 13.0F, 0.0F, false);
		group.setTextureOffset(12, 34).addBox(-6.5F, -0.25F, -6.5F, 13.0F, 3.0F, 13.0F, 0.0F, false);
		group.setTextureOffset(16, 5).addBox(-6.0F, -0.75F, -6.0F, 12.0F, 1.0F, 12.0F, 0.0F, false);
		group.setTextureOffset(0, 58).addBox(4.0F, 1.75F, 4.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		group.setTextureOffset(0, 58).addBox(-6.0F, 1.75F, 4.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		group.setTextureOffset(0, 58).addBox(-6.0F, 1.75F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		group.setTextureOffset(0, 58).addBox(4.0F, 1.75F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		group.setTextureOffset(28, 50).addBox(-4.5F, -2.0F, -4.5F, 9.0F, 2.0F, 9.0F, 0.0F, false);

		rotateCube = new ModelRenderer(this);
		rotateCube.setRotationPoint(0.0F, 0.0F, 0.0F);
		group.addChild(rotateCube);
		rotateCube.setTextureOffset(8, 55).addBox(-3.0F, -4.5F, -3.0F, 6.0F, 3.0F, 6.0F, 0.0F, false);

		group2 = new ModelRenderer(this);
		group2.setRotationPoint(0.0F, -8.0F, 0.0F);
		all.addChild(group2);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, -2.25F, 0.0F);
		group2.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.7854F, 0.0F);
		cube_r1.setTextureOffset(0, 42).addBox(-1.5F, -3.0F, -7.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
		cube_r1.setTextureOffset(0, 42).addBox(-7.0F, -3.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);
		cube_r1.setTextureOffset(0, 42).addBox(-1.5F, -3.0F, 4.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
		cube_r1.setTextureOffset(0, 42).addBox(4.0F, -3.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		group.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		group2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		cube_r1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}