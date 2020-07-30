package teamHTBP.vida.modelRender.armormodel;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ArmorStandEntity;

public class ArmorModelElementHelmet<T extends Entity> extends BipedModel<ArmorStandEntity> {
    public ModelRenderer field_78116_c;
    public ModelRenderer part19;
    public ModelRenderer part15;
    public ModelRenderer part16;
    public ModelRenderer part17;
    public ModelRenderer part21;

    protected ArmorModelElementHelmet(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
        super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
    }


    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    protected Iterable<ModelRenderer> getHeadParts() {
        this.field_78116_c.copyModelAngles(this.bipedHead);
        return ImmutableList.of(this.field_78116_c);
    }


    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
