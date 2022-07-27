package teamHTBP.vida.client.modelRender.tilemodel;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PrismTableModel extends TileEntityModel {
    private final ModelRenderer bone;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;


    public PrismTableModel() {
        super();
        textureWidth = 128;
        textureHeight = 128;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.setTextureOffset(0, 0).addBox(-7.0F, -3.0F, -7.0F, 14.0F, 3.0F, 14.0F, 0.0F, false);
        bone.setTextureOffset(0, 17).addBox(-5.0F, -16.0F, -5.0F, 10.0F, 13.0F, 10.0F, 0.0F, false);

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, -15.75F, 0.0F);
        bone.addChild(bone2);
        setRotationAngle(bone2, 0.2182F, 0.0F, 0.0F);
        bone2.setTextureOffset(29, 75).addBox(-6.0F, 1.0F, -7.0F, 12.0F, 0.0F, 14.0F, 0.0F, false);
        bone2.setTextureOffset(12, 40).addBox(-6.0F, -3.0F, -7.0F, 12.0F, 4.0F, 14.0F, 0.0F, false);

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone3.setTextureOffset(0, 40).addBox(7.0F, -16.0F, -9.0F, 3.0F, 16.0F, 3.0F, 0.0F, false);
        bone3.setTextureOffset(0, 40).addBox(-10.0F, -16.0F, -9.0F, 3.0F, 16.0F, 3.0F, 0.0F, true);
        bone3.setTextureOffset(0, 40).addBox(-10.0F, -20.0F, 6.0F, 3.0F, 20.0F, 3.0F, 0.0F, false);
        bone3.setTextureOffset(0, 40).addBox(7.0F, -20.0F, 6.0F, 3.0F, 20.0F, 3.0F, 0.0F, true);

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, -16.0F, 0.0F);
        bone3.addChild(bone4);
        setRotationAngle(bone4, 0.2182F, 0.0F, 0.0F);
        bone4.setTextureOffset(0, 58).addBox(-11.0F, -4.0F, -10.0F, 5.0F, 4.0F, 20.0F, 0.0F, false);
        bone4.setTextureOffset(0, 58).addBox(6.0F, -4.0F, -10.0F, 5.0F, 4.0F, 20.0F, 0.0F, true);
        bone4.setTextureOffset(0, 82).addBox(-8.0F, -8.0F, -9.0F, 2.0F, 4.0F, 18.0F, 0.0F, true);
        bone4.setTextureOffset(0, 82).addBox(6.0F, -8.0F, -9.0F, 2.0F, 4.0F, 18.0F, 0.0F, false);
        bone4.setTextureOffset(40, 86).addBox(-6.0F, -5.5F, -8.0F, 12.0F, 2.0F, 16.0F, 0.0F, true);
        bone4.setTextureOffset(0, 104).addBox(-6.0F, -6.0F, -9.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
        bone4.setTextureOffset(0, 104).addBox(-6.0F, -7.0F, 8.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {
        bone.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
    }
}
