package teamHTBP.vida.modelRender.armormodel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.world.entity.LivingEntity;

public abstract class AbstractModelElementLeggings<T extends LivingEntity> extends HumanoidModel<T> {

    public ModelRenderer body_low;

    public AbstractModelElementLeggings() {
        super(1.0f, 0, 64, 64);
    }

    public AbstractModelElementLeggings(int textureWidth, int textureHeight) {
        super(1.0f, 0, textureWidth, textureHeight);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.body_low.visible = true;
        this.body_low.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }


    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    public void setAllVisible(boolean visible) {
        this.head.visible = false;
        this.hat.visible = false;
        this.rightArm.visible = false;
        this.leftArm.visible = false;
        this.rightLeg.visible = true;
        this.leftLeg.visible = true;
        this.body_low.visible = true;
    }


}
