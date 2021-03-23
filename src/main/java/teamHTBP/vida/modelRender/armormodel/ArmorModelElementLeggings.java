package teamHTBP.vida.modelRender.armormodel;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ArmorStandEntity;

public class ArmorModelElementLeggings<T extends Entity> extends BipedModel<ArmorStandEntity> {

    public ModelRenderer field_78115_e;
    public ModelRenderer part21;
    public ModelRenderer part20;
    public ModelRenderer part22;
    public ModelRenderer part23;
    public ModelRenderer part25;
    public ModelRenderer part19;
    public ModelRenderer part16;
    public ModelRenderer part17;
    public ModelRenderer part24;
    public ModelRenderer part26;

    public ArmorModelElementLeggings() {
        super(1.0f,0,64,64);
    }

    public ArmorModelElementLeggings(int textureWidth,int textureHeight) {
        super(1.0f,0, textureWidth, textureHeight);
    }
    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.field_78115_e.showModel = true;
        this.field_78115_e.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

    }



    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setVisible(boolean visible) {
        this.bipedHead.showModel = false;
        this.bipedHeadwear.showModel = false;
        this.bipedBody.showModel = true;
        this.bipedRightArm.showModel = false;
        this.bipedLeftArm.showModel = false;
        this.bipedRightLeg.showModel = true;
        this.bipedLeftLeg.showModel = true;
    }


}
