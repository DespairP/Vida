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
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        leg_left.copyFrom(leftLeg);
        leg_right.copyFrom(rightLeg);
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.body, this.rightArm, this.leftArm, this.leg_right, this.leg_left, this.hat);
    }


    public void setAllVisible(boolean visible) {
        this.head.visible = false;
        this.hat.visible = false;
        this.body.visible = false;
        this.rightArm.visible = false;
        this.leftArm.visible = false;
        this.leg_right.visible = true;
        this.leg_left.visible = true;
    }
}
