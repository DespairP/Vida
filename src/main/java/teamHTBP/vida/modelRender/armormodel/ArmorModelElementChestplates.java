package teamHTBP.vida.modelRender.armormodel;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArmorModelElementChestplates<T extends Entity> extends BipedModel<ArmorStandEntity> {

    public ModelRenderer field_178723_h;
    public ModelRenderer field_178724_i;
    public ModelRenderer field_78115_e;
    public ModelRenderer part16;
    public ModelRenderer part18;
    public ModelRenderer part15;
    public ModelRenderer part17;
    public ModelRenderer part24;
    public ModelRenderer part19;
    public ModelRenderer part21;
    public ModelRenderer part22;
    public ModelRenderer part23;
    public ModelRenderer part27;
    public ModelRenderer part20;
    public ModelRenderer part17_1;

    public ArmorModelElementChestplates() {
        super(1.0f, 0, 64, 64);

    }


    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

    }

    protected ModelRenderer getArmForSide(HandSide side) {
       return super.getArmForSide(side);
    }


    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        this.field_178723_h.copyModelAngles(this.bipedRightArm);
        this.field_78115_e.copyModelAngles(this.bipedBody);
        this.field_178724_i.copyModelAngles(this.bipedLeftArm);
        return ImmutableList.of(this.field_78115_e,this.field_178724_i,this.field_178723_h, this.field_178724_i,this.field_178724_i);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }


    public void setVisible(boolean visible) {
        this.bipedHead.showModel = false;
        this.bipedHeadwear.showModel =  true;
        this.bipedBody.showModel = true;
        this.bipedRightArm.showModel = true;
        this.bipedLeftArm.showModel = true;
        this.bipedRightLeg.showModel = false;
        this.bipedLeftLeg.showModel = false;
    }
}
