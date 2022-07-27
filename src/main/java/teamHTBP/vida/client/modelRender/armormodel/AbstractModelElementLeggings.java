package teamHTBP.vida.client.modelRender.armormodel;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public abstract class AbstractModelElementLeggings<T extends LivingEntity> extends BipedModel<T> {

    public ModelRenderer body_low;

    public AbstractModelElementLeggings() {
        super(1.0f, 0, 64, 64);
    }

    public AbstractModelElementLeggings(int textureWidth, int textureHeight) {
        super(1.0f, 0, textureWidth, textureHeight);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.body_low.showModel = true;
        this.body_low.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }


    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setVisible(boolean visible) {
        this.bipedHead.showModel = false;
        this.bipedHeadwear.showModel = false;
        this.bipedRightArm.showModel = false;
        this.bipedLeftArm.showModel = false;
        this.bipedRightLeg.showModel = true;
        this.bipedLeftLeg.showModel = true;
        this.body_low.showModel = true;
    }


}
