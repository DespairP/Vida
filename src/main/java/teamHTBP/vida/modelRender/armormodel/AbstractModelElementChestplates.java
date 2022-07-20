package teamHTBP.vida.modelRender.armormodel;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractModelElementChestplates<T extends LivingEntity> extends HumanoidModel<T> {

    public ModelRenderer arm_right;
    public ModelRenderer arm_left;
    public ModelRenderer body;


    private float remainingItemUseTime;

    public AbstractModelElementChestplates() {
        super(1.0f, 0, 64, 64);
    }

    public AbstractModelElementChestplates(int textureWidth, int textureHeight) {
        super(1.0f, 0, textureWidth, textureHeight);
    }

    protected ModelRenderer getArm(HandSide side) {
        return side == HandSide.LEFT ? this.arm_left : this.arm_right;
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }


    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        arm_right.copyFrom(this.rightArm);
        body.copyFrom(this.body);
        arm_left.copyFrom(this.leftArm);
        return ImmutableList.of(this.body, this.arm_left, this.arm_right, this.arm_left, this.arm_left);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }


    public void setAllVisible(boolean visible) {
        this.head.visible = false;
        this.hat.visible = true;
        this.body.visible = true;
        this.rightArm.visible = true;
        this.leftArm.visible = true;
        this.rightLeg.visible = false;
        this.leftLeg.visible = false;
    }
}
