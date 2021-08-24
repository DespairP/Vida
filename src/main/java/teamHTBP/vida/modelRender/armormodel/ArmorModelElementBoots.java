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

public class ArmorModelElementBoots<T extends Entity> extends BipedModel {

    public ModelRenderer leg_right;
    public ModelRenderer leg_left;

    private float remainingItemUseTime;

    public ArmorModelElementBoots() {
        super(1.0f, 0, 64, 64);
    }

    public ArmorModelElementBoots(int textureWidth, int textureHeight) {
        super(1.0f, 0, textureWidth, textureHeight);
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
        return ImmutableList.of(this.bipedBody, this.bipedRightArm, this.bipedLeftArm, this.leg_right, this.leg_left, this.bipedHeadwear);
    }


    /**
     * Sets this entity's model rotation angles
     */
    public void setRotationAngles(LivingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = entityIn.getTicksElytraFlying() > 4;
        boolean flag1 = entityIn.isActualySwimming();
        this.bipedHead.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        if (flag) {
            this.bipedHead.rotateAngleX = (-(float) Math.PI / 4F);
        } else if (this.swimAnimation > 0.0F) {
            if (flag1) {
                this.bipedHead.rotateAngleX = this.rotLerpRad(this.bipedHead.rotateAngleX, (-(float) Math.PI / 4F), this.swimAnimation);
            } else {
                this.bipedHead.rotateAngleX = this.rotLerpRad(this.bipedHead.rotateAngleX, headPitch * ((float) Math.PI / 180F), this.swimAnimation);
            }
        } else {
            this.bipedHead.rotateAngleX = headPitch * ((float) Math.PI / 180F);
        }

        this.bipedBody.rotateAngleY = 0.0F;
        this.bipedRightArm.rotationPointZ = 0.0F;
        this.bipedRightArm.rotationPointX = -5.0F;
        this.bipedLeftArm.rotationPointZ = 0.0F;
        this.bipedLeftArm.rotationPointX = 5.0F;
        float f = 1.0F;
        if (flag) {
            f = (float) entityIn.getMotion().lengthSquared();
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.leg_right.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.leg_left.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
        this.leg_right.rotateAngleY = 0.0F;
        this.leg_left.rotateAngleY = 0.0F;
        this.leg_right.rotateAngleZ = 0.0F;
        this.leg_left.rotateAngleZ = 0.0F;
        if (this.isSitting) {
            this.bipedRightArm.rotateAngleX += (-(float) Math.PI / 5F);
            this.bipedLeftArm.rotateAngleX += (-(float) Math.PI / 5F);
            this.leg_right.rotateAngleX = -1.4137167F;
            this.leg_right.rotateAngleY = ((float) Math.PI / 10F);
            this.leg_right.rotateAngleZ = 0.07853982F;
            this.leg_left.rotateAngleX = -1.4137167F;
            this.leg_left.rotateAngleY = (-(float) Math.PI / 10F);
            this.leg_left.rotateAngleZ = -0.07853982F;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        switch (this.leftArmPose) {
            case EMPTY:
                this.bipedLeftArm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - 0.9424779F;
                this.bipedLeftArm.rotateAngleY = ((float) Math.PI / 6F);
                break;
            case ITEM:
                this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
                this.bipedLeftArm.rotateAngleY = 0.0F;
        }

        switch (this.rightArmPose) {
            case EMPTY:
                this.bipedRightArm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - 0.9424779F;
                this.bipedRightArm.rotateAngleY = (-(float) Math.PI / 6F);
                break;
            case ITEM:
                this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
                this.bipedRightArm.rotateAngleY = 0.0F;
                break;
            case THROW_SPEAR:
                this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - (float) Math.PI;
                this.bipedRightArm.rotateAngleY = 0.0F;
        }

        if (this.leftArmPose == BipedModel.ArmPose.THROW_SPEAR && this.rightArmPose != BipedModel.ArmPose.BLOCK && this.rightArmPose != BipedModel.ArmPose.THROW_SPEAR && this.rightArmPose != BipedModel.ArmPose.BOW_AND_ARROW) {
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - (float) Math.PI;
            this.bipedLeftArm.rotateAngleY = 0.0F;
        }

        if (this.swingProgress > 0.0F) {
            HandSide handside = this.getMainHand(entityIn);
            ModelRenderer modelrenderer = this.getArmForSide(handside);
            float f1 = this.swingProgress;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;
            if (handside == HandSide.LEFT) {
                this.bipedBody.rotateAngleY *= -1.0F;
            }

            this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
            f1 = 1.0F - this.swingProgress;
            f1 = f1 * f1;
            f1 = f1 * f1;
            f1 = 1.0F - f1;
            float f2 = MathHelper.sin(f1 * (float) Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
            modelrenderer.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
        }

        if (this.isSneak) {
            this.bipedBody.rotateAngleX = 0.5F;
            this.bipedRightArm.rotateAngleX += 0.4F;
            this.bipedLeftArm.rotateAngleX += 0.4F;
            this.leg_right.rotationPointZ = 4.0F;
            this.leg_left.rotationPointZ = 4.0F;
            this.leg_right.rotationPointY = 12.2F;
            this.leg_left.rotationPointY = 12.2F;
            this.bipedHead.rotationPointY = 4.2F;
            this.bipedBody.rotationPointY = 3.2F;
            this.bipedLeftArm.rotationPointY = 5.2F;
            this.bipedRightArm.rotationPointY = 5.2F;
        } else {
            this.bipedBody.rotateAngleX = 0.0F;
            this.leg_right.rotationPointZ = 0.1F;
            this.leg_left.rotationPointZ = 0.1F;
            this.leg_right.rotationPointY = 12.0F;
            this.leg_left.rotationPointY = 12.0F;
            this.bipedHead.rotationPointY = 0.0F;
            this.bipedBody.rotationPointY = 0.0F;
            this.bipedLeftArm.rotationPointY = 2.0F;
            this.bipedRightArm.rotationPointY = 2.0F;
        }

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        if (this.rightArmPose == BipedModel.ArmPose.BOW_AND_ARROW) {
            this.bipedRightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
            this.bipedLeftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY + 0.4F;
            this.bipedRightArm.rotateAngleX = (-(float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = (-(float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
        } else if (this.leftArmPose == BipedModel.ArmPose.BOW_AND_ARROW && this.rightArmPose != BipedModel.ArmPose.THROW_SPEAR && this.rightArmPose != BipedModel.ArmPose.BLOCK) {
            this.bipedRightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
            this.bipedLeftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
            this.bipedRightArm.rotateAngleX = (-(float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = (-(float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
        }

        float f4 = (float) CrossbowItem.getChargeTime(entityIn.getActiveItemStack());
        if (this.rightArmPose == BipedModel.ArmPose.CROSSBOW_CHARGE) {
            this.bipedRightArm.rotateAngleY = -0.8F;
            this.bipedRightArm.rotateAngleX = -0.97079635F;
            this.bipedLeftArm.rotateAngleX = -0.97079635F;
            float f5 = MathHelper.clamp(this.remainingItemUseTime, 0.0F, f4);
            this.bipedLeftArm.rotateAngleY = MathHelper.lerp(f5 / f4, 0.4F, 0.85F);
            this.bipedLeftArm.rotateAngleX = MathHelper.lerp(f5 / f4, this.bipedLeftArm.rotateAngleX, (-(float) Math.PI / 2F));
        } else if (this.leftArmPose == BipedModel.ArmPose.CROSSBOW_CHARGE) {
            this.bipedLeftArm.rotateAngleY = 0.8F;
            this.bipedRightArm.rotateAngleX = -0.97079635F;
            this.bipedLeftArm.rotateAngleX = -0.97079635F;
            float f6 = MathHelper.clamp(this.remainingItemUseTime, 0.0F, f4);
            this.bipedRightArm.rotateAngleY = MathHelper.lerp(f6 / f4, -0.4F, -0.85F);
            this.bipedRightArm.rotateAngleX = MathHelper.lerp(f6 / f4, this.bipedRightArm.rotateAngleX, (-(float) Math.PI / 2F));
        }

        if (this.rightArmPose == BipedModel.ArmPose.CROSSBOW_HOLD && this.swingProgress <= 0.0F) {
            this.bipedRightArm.rotateAngleY = -0.3F + this.bipedHead.rotateAngleY;
            this.bipedLeftArm.rotateAngleY = 0.6F + this.bipedHead.rotateAngleY;
            this.bipedRightArm.rotateAngleX = (-(float) Math.PI / 2F) + this.bipedHead.rotateAngleX + 0.1F;
            this.bipedLeftArm.rotateAngleX = -1.5F + this.bipedHead.rotateAngleX;
        } else if (this.leftArmPose == BipedModel.ArmPose.CROSSBOW_HOLD) {
            this.bipedRightArm.rotateAngleY = -0.6F + this.bipedHead.rotateAngleY;
            this.bipedLeftArm.rotateAngleY = 0.3F + this.bipedHead.rotateAngleY;
            this.bipedRightArm.rotateAngleX = -1.5F + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = (-(float) Math.PI / 2F) + this.bipedHead.rotateAngleX + 0.1F;
        }

        if (this.swimAnimation > 0.0F) {
            float f7 = limbSwing % 26.0F;
            float f8 = this.swingProgress > 0.0F ? 0.0F : this.swimAnimation;
            if (f7 < 14.0F) {
                this.bipedLeftArm.rotateAngleX = this.rotLerpRad(this.bipedLeftArm.rotateAngleX, 0.0F, this.swimAnimation);
                this.bipedRightArm.rotateAngleX = MathHelper.lerp(f8, this.bipedRightArm.rotateAngleX, 0.0F);
                this.bipedLeftArm.rotateAngleY = this.rotLerpRad(this.bipedLeftArm.rotateAngleY, (float) Math.PI, this.swimAnimation);
                this.bipedRightArm.rotateAngleY = MathHelper.lerp(f8, this.bipedRightArm.rotateAngleY, (float) Math.PI);
                this.bipedLeftArm.rotateAngleZ = this.rotLerpRad(this.bipedLeftArm.rotateAngleZ, (float) Math.PI + 1.8707964F * this.getArmAngleSq(f7) / this.getArmAngleSq(14.0F), this.swimAnimation);
                this.bipedRightArm.rotateAngleZ = MathHelper.lerp(f8, this.bipedRightArm.rotateAngleZ, (float) Math.PI - 1.8707964F * this.getArmAngleSq(f7) / this.getArmAngleSq(14.0F));
            } else if (f7 >= 14.0F && f7 < 22.0F) {
                float f10 = (f7 - 14.0F) / 8.0F;
                this.bipedLeftArm.rotateAngleX = this.rotLerpRad(this.bipedLeftArm.rotateAngleX, ((float) Math.PI / 2F) * f10, this.swimAnimation);
                this.bipedRightArm.rotateAngleX = MathHelper.lerp(f8, this.bipedRightArm.rotateAngleX, ((float) Math.PI / 2F) * f10);
                this.bipedLeftArm.rotateAngleY = this.rotLerpRad(this.bipedLeftArm.rotateAngleY, (float) Math.PI, this.swimAnimation);
                this.bipedRightArm.rotateAngleY = MathHelper.lerp(f8, this.bipedRightArm.rotateAngleY, (float) Math.PI);
                this.bipedLeftArm.rotateAngleZ = this.rotLerpRad(this.bipedLeftArm.rotateAngleZ, 5.012389F - 1.8707964F * f10, this.swimAnimation);
                this.bipedRightArm.rotateAngleZ = MathHelper.lerp(f8, this.bipedRightArm.rotateAngleZ, 1.2707963F + 1.8707964F * f10);
            } else if (f7 >= 22.0F && f7 < 26.0F) {
                float f9 = (f7 - 22.0F) / 4.0F;
                this.bipedLeftArm.rotateAngleX = this.rotLerpRad(this.bipedLeftArm.rotateAngleX, ((float) Math.PI / 2F) - ((float) Math.PI / 2F) * f9, this.swimAnimation);
                this.bipedRightArm.rotateAngleX = MathHelper.lerp(f8, this.bipedRightArm.rotateAngleX, ((float) Math.PI / 2F) - ((float) Math.PI / 2F) * f9);
                this.bipedLeftArm.rotateAngleY = this.rotLerpRad(this.bipedLeftArm.rotateAngleY, (float) Math.PI, this.swimAnimation);
                this.bipedRightArm.rotateAngleY = MathHelper.lerp(f8, this.bipedRightArm.rotateAngleY, (float) Math.PI);
                this.bipedLeftArm.rotateAngleZ = this.rotLerpRad(this.bipedLeftArm.rotateAngleZ, (float) Math.PI, this.swimAnimation);
                this.bipedRightArm.rotateAngleZ = MathHelper.lerp(f8, this.bipedRightArm.rotateAngleZ, (float) Math.PI);
            }

            float f11 = 0.3F;
            float f12 = 0.33333334F;
            this.leg_left.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.leg_left.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F + (float) Math.PI));
            this.leg_right.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.leg_right.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F));
        }

        this.bipedHeadwear.copyModelAngles(this.bipedHead);
    }

    protected float rotLerpRad(float angleIn, float maxAngleIn, float mulIn) {
        float f = (maxAngleIn - angleIn) % ((float) Math.PI * 2F);
        if (f < -(float) Math.PI) {
            f += ((float) Math.PI * 2F);
        }

        if (f >= (float) Math.PI) {
            f -= ((float) Math.PI * 2F);
        }

        return angleIn + mulIn * f;
    }

    private float getArmAngleSq(float limbSwing) {
        return -65.0F * limbSwing + limbSwing * limbSwing;
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
