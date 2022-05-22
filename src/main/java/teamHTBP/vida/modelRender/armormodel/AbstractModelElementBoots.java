package teamHTBP.vida.modelRender.armormodel;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public abstract class AbstractModelElementBoots<T extends LivingEntity> extends BipedModel<T> {

    public ModelRenderer leg_right;
    public ModelRenderer leg_left;

    public AbstractModelElementBoots() {
        this( 64, 64);
    }

    public AbstractModelElementBoots(int textureWidth, int textureHeight) {
        super(1.0f, 0, textureWidth, textureHeight);
    }


    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        leg_left.copyModelAngles(bipedLeftLeg);
        leg_right.copyModelAngles(bipedRightLeg);
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.bipedBody, this.bipedRightArm, this.bipedLeftArm, this.leg_right, this.leg_left, this.bipedHeadwear);
    }


    public void setVisible(boolean visible) {
        this.bipedHead.showModel = false;
        this.bipedHeadwear.showModel = false;
        this.bipedBody.showModel = false;
        this.bipedRightArm.showModel = false;
        this.bipedLeftArm.showModel = false;
        this.leg_right.showModel = true;
        this.leg_left.showModel = true;
    }
}
