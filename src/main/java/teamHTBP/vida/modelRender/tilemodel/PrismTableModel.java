package teamHTBP.vida.modelRender.tilemodel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PrismTableModel extends TileEntityModel {
    private final ModelRenderer bone;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;


    public PrismTableModel() {
        super();
        texWidth = 128;
        texHeight = 128;

        bone = new ModelRenderer(this);
        bone.setPos(0.0F, 24.0F, 0.0F);
        bone.texOffs(0, 0).addBox(-7.0F, -3.0F, -7.0F, 14.0F, 3.0F, 14.0F, 0.0F, false);
        bone.texOffs(0, 17).addBox(-5.0F, -16.0F, -5.0F, 10.0F, 13.0F, 10.0F, 0.0F, false);

        bone2 = new ModelRenderer(this);
        bone2.setPos(0.0F, -15.75F, 0.0F);
        bone.addChild(bone2);
        setRotationAngle(bone2, 0.2182F, 0.0F, 0.0F);
        bone2.texOffs(29, 75).addBox(-6.0F, 1.0F, -7.0F, 12.0F, 0.0F, 14.0F, 0.0F, false);
        bone2.texOffs(12, 40).addBox(-6.0F, -3.0F, -7.0F, 12.0F, 4.0F, 14.0F, 0.0F, false);

        bone3 = new ModelRenderer(this);
        bone3.setPos(0.0F, 24.0F, 0.0F);
        bone3.texOffs(0, 40).addBox(7.0F, -16.0F, -9.0F, 3.0F, 16.0F, 3.0F, 0.0F, false);
        bone3.texOffs(0, 40).addBox(-10.0F, -16.0F, -9.0F, 3.0F, 16.0F, 3.0F, 0.0F, true);
        bone3.texOffs(0, 40).addBox(-10.0F, -20.0F, 6.0F, 3.0F, 20.0F, 3.0F, 0.0F, false);
        bone3.texOffs(0, 40).addBox(7.0F, -20.0F, 6.0F, 3.0F, 20.0F, 3.0F, 0.0F, true);

        bone4 = new ModelRenderer(this);
        bone4.setPos(0.0F, -16.0F, 0.0F);
        bone3.addChild(bone4);
        setRotationAngle(bone4, 0.2182F, 0.0F, 0.0F);
        bone4.texOffs(0, 58).addBox(-11.0F, -4.0F, -10.0F, 5.0F, 4.0F, 20.0F, 0.0F, false);
        bone4.texOffs(0, 58).addBox(6.0F, -4.0F, -10.0F, 5.0F, 4.0F, 20.0F, 0.0F, true);
        bone4.texOffs(0, 82).addBox(-8.0F, -8.0F, -9.0F, 2.0F, 4.0F, 18.0F, 0.0F, true);
        bone4.texOffs(0, 82).addBox(6.0F, -8.0F, -9.0F, 2.0F, 4.0F, 18.0F, 0.0F, false);
        bone4.texOffs(40, 86).addBox(-6.0F, -5.5F, -8.0F, 12.0F, 2.0F, 16.0F, 0.0F, true);
        bone4.texOffs(0, 104).addBox(-6.0F, -6.0F, -9.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
        bone4.texOffs(0, 104).addBox(-6.0F, -7.0F, 8.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {
        bone.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
    }
}
