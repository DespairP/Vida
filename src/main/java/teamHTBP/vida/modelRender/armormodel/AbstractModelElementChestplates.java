package teamHTBP.vida.modelRender.armormodel;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractModelElementChestplates<T extends LivingEntity> extends BipedModel<T> {

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

    protected ModelRenderer getArmForSide(HandSide side) {
        return side == HandSide.LEFT ? this.arm_left : this.arm_right;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }


    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        arm_right.copyModelAngles(this.bipedRightArm);
        body.copyModelAngles(this.bipedBody);
        arm_left.copyModelAngles(this.bipedLeftArm);
        return ImmutableList.of(this.body, this.arm_left, this.arm_right, this.arm_left, this.arm_left);
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
        this.bipedHeadwear.showModel = true;
        this.bipedBody.showModel = true;
        this.bipedRightArm.showModel = true;
        this.bipedLeftArm.showModel = true;
        this.bipedRightLeg.showModel = false;
        this.bipedLeftLeg.showModel = false;
    }
}
