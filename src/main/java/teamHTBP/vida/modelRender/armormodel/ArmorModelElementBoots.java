package teamHTBP.vida.modelRender.armormodel;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ArmorStandEntity;

public class ArmorModelElementBoots<T extends Entity> extends BipedModel<ArmorStandEntity> {

    public ModelRenderer field_178721_j;
    public ModelRenderer field_178722_k;
    public ModelRenderer part28;
    public ModelRenderer part29;
    public ModelRenderer part25;
    public ModelRenderer part24;

    public ArmorModelElementBoots() {
        super(1.0f,0,64,64);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }



    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    protected Iterable<ModelRenderer> getBodyParts() {
        this.field_178721_j.copyModelAngles(this.bipedLeftLeg);
        this.field_178722_k.copyModelAngles(this.bipedRightLeg);
        return ImmutableList.of(this.bipedBody, this.field_178722_k, this.field_178722_k, field_178721_j,this.field_178722_k, this.field_178721_j);
    }


    public void setVisible(boolean visible) {
        this.bipedHead.showModel = false;
        this.bipedHeadwear.showModel = false;
        this.bipedBody.showModel = false;
        this.bipedRightArm.showModel = false;
        this.bipedLeftArm.showModel = false;
        this.bipedRightLeg.showModel = true;
        this.bipedLeftLeg.showModel = true;
    }

}
