package teamHTBP.vida.modelRender.armormodel;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArmorModelElementChestplates<T extends Entity> extends BipedModel {

    public ModelRenderer arm_right;
    public ModelRenderer arm_left;
    public ModelRenderer body;


    private float remainingItemUseTime;

    public ArmorModelElementChestplates() {
        super(1.0f, 0, 64, 64);
    }

    public ArmorModelElementChestplates(int textureWidth,int textureHeight) {
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
        //this.arm_right.copyModelAngles(this.bipedRightArm);
       // this.body.copyModelAngles(this.bipedBody);
      //  this.arm_left.copyModelAngles(this.bipedLeftArm);
        return ImmutableList.of(this.body,this.arm_left,this.arm_right, this.arm_left,this.arm_left);
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

    public void setRotationAngles(LivingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = entityIn.getTicksElytraFlying() > 4;
        boolean flag1 = entityIn.isActualySwimming();
        this.bipedHead.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        if (flag) {
            this.bipedHead.rotateAngleX = (-(float)Math.PI / 4F);
        } else if (this.swimAnimation > 0.0F) {
            if (flag1) {
                this.bipedHead.rotateAngleX = this.rotLerpRad(this.bipedHead.rotateAngleX, (-(float)Math.PI / 4F), this.swimAnimation);
            } else {
                this.bipedHead.rotateAngleX = this.rotLerpRad(this.bipedHead.rotateAngleX, headPitch * ((float)Math.PI / 180F), this.swimAnimation);
            }
        } else {
            this.bipedHead.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        }

        this.body.rotateAngleY = 0.0F;
        this.arm_right.rotationPointZ = 0.0F;
        this.arm_right.rotationPointX = -5.0F;
        this.arm_left.rotationPointZ = 0.0F;
        this.arm_left.rotationPointX = 5.0F;
        float f = 1.0F;
        if (flag) {
            f = (float)entityIn.getMotion().lengthSquared();
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        this.arm_right.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.arm_left.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.arm_right.rotateAngleZ = 0.0F;
        this.arm_left.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;
        this.bipedRightLeg.rotateAngleZ = 0.0F;
        this.bipedLeftLeg.rotateAngleZ = 0.0F;
        if (this.isSitting) {
            this.arm_right.rotateAngleX += (-(float)Math.PI / 5F);
            this.arm_left.rotateAngleX += (-(float)Math.PI / 5F);
            this.bipedRightLeg.rotateAngleX = -1.4137167F;
            this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.bipedRightLeg.rotateAngleZ = 0.07853982F;
            this.bipedLeftLeg.rotateAngleX = -1.4137167F;
            this.bipedLeftLeg.rotateAngleY = (-(float)Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleZ = -0.07853982F;
        }

        this.arm_right.rotateAngleY = 0.0F;
        this.arm_right.rotateAngleZ = 0.0F;
        switch(this.leftArmPose) {
            case EMPTY:
                this.arm_left.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.arm_left.rotateAngleX = this.arm_left.rotateAngleX * 0.5F - 0.9424779F;
                this.arm_left.rotateAngleY = ((float)Math.PI / 6F);
                break;
            case ITEM:
                this.arm_left.rotateAngleX = this.arm_left.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
                this.arm_left.rotateAngleY = 0.0F;
        }

        switch(this.rightArmPose) {
            case EMPTY:
                this.arm_right.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.arm_right.rotateAngleX = this.arm_right.rotateAngleX * 0.5F - 0.9424779F;
                this.arm_right.rotateAngleY = (-(float)Math.PI / 6F);
                break;
            case ITEM:
                this.arm_right.rotateAngleX = this.arm_right.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
                this.arm_right.rotateAngleY = 0.0F;
                break;
            case THROW_SPEAR:
                this.arm_right.rotateAngleX = this.arm_right.rotateAngleX * 0.5F - (float)Math.PI;
                this.arm_right.rotateAngleY = 0.0F;
        }

        if (this.leftArmPose == BipedModel.ArmPose.THROW_SPEAR && this.rightArmPose != BipedModel.ArmPose.BLOCK && this.rightArmPose != BipedModel.ArmPose.THROW_SPEAR && this.rightArmPose != BipedModel.ArmPose.BOW_AND_ARROW) {
            this.arm_left.rotateAngleX = this.arm_left.rotateAngleX * 0.5F - (float)Math.PI;
            this.arm_left.rotateAngleY = 0.0F;
        }

        if (this.swingProgress > 0.0F) {
            HandSide handside = this.getMainHand(entityIn);
            ModelRenderer modelrenderer = this.getArmForSide(handside);
            float f1 = this.swingProgress;
            this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;
            if (handside == HandSide.LEFT) {
                this.body.rotateAngleY *= -1.0F;
            }

            this.arm_right.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 5.0F;
            this.arm_right.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 5.0F;
            this.arm_left.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
            this.arm_left.rotationPointX = MathHelper.cos(this.body.rotateAngleY) * 5.0F;
            this.arm_right.rotateAngleY += this.body.rotateAngleY;
            this.arm_left.rotateAngleY += this.body.rotateAngleY;
            this.arm_left.rotateAngleX += this.body.rotateAngleY;
            f1 = 1.0F - this.swingProgress;
            f1 = f1 * f1;
            f1 = f1 * f1;
            f1 = 1.0F - f1;
            float f2 = MathHelper.sin(f1 * (float)Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float)((double)modelrenderer.rotateAngleX - ((double)f2 * 1.2D + (double)f3));
            modelrenderer.rotateAngleY += this.body.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
        }

        if (this.isSneak) {
            this.body.rotateAngleX = 0.5F;
            this.arm_right.rotateAngleX += 0.4F;
            this.arm_left.rotateAngleX += 0.4F;
            this.bipedRightLeg.rotationPointZ = 4.0F;
            this.bipedLeftLeg.rotationPointZ = 4.0F;
            this.bipedRightLeg.rotationPointY = 12.2F;
            this.bipedLeftLeg.rotationPointY = 12.2F;
            this.bipedHead.rotationPointY = 4.2F;
            this.body.rotationPointY = 3.2F;
            this.arm_left.rotationPointY = 5.2F;
            this.arm_right.rotationPointY = 5.2F;
        } else {
            this.body.rotateAngleX = 0.0F;
            this.bipedRightLeg.rotationPointZ = 0.1F;
            this.bipedLeftLeg.rotationPointZ = 0.1F;
            this.bipedRightLeg.rotationPointY = 12.0F;
            this.bipedLeftLeg.rotationPointY = 12.0F;
            this.bipedHead.rotationPointY = 0.0F;
            this.body.rotationPointY = 0.0F;
            this.arm_left.rotationPointY = 2.0F;
            this.arm_right.rotationPointY = 2.0F;
        }

        this.arm_right.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.arm_left.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.arm_right.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.arm_left.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        if (this.rightArmPose == BipedModel.ArmPose.BOW_AND_ARROW) {
            this.arm_right.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
            this.arm_left.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY + 0.4F;
            this.arm_right.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.arm_left.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
        } else if (this.leftArmPose == BipedModel.ArmPose.BOW_AND_ARROW && this.rightArmPose != BipedModel.ArmPose.THROW_SPEAR && this.rightArmPose != BipedModel.ArmPose.BLOCK) {
            this.arm_right.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
            this.arm_left.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
            this.arm_right.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.arm_left.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
        }

        float f4 = (float) CrossbowItem.getChargeTime(entityIn.getActiveItemStack());
        if (this.rightArmPose == BipedModel.ArmPose.CROSSBOW_CHARGE) {
            this.arm_right.rotateAngleY = -0.8F;
            this.arm_right.rotateAngleX = -0.97079635F;
            this.arm_left.rotateAngleX = -0.97079635F;
            float f5 = MathHelper.clamp(this.remainingItemUseTime, 0.0F, f4);
            this.arm_left.rotateAngleY = MathHelper.lerp(f5 / f4, 0.4F, 0.85F);
            this.arm_left.rotateAngleX = MathHelper.lerp(f5 / f4, this.arm_left.rotateAngleX, (-(float)Math.PI / 2F));
        } else if (this.leftArmPose == BipedModel.ArmPose.CROSSBOW_CHARGE) {
            this.arm_left.rotateAngleY = 0.8F;
            this.arm_right.rotateAngleX = -0.97079635F;
            this.arm_left.rotateAngleX = -0.97079635F;
            float f6 = MathHelper.clamp(this.remainingItemUseTime, 0.0F, f4);
            this.arm_right.rotateAngleY = MathHelper.lerp(f6 / f4, -0.4F, -0.85F);
            this.arm_right.rotateAngleX = MathHelper.lerp(f6 / f4, this.arm_right.rotateAngleX, (-(float)Math.PI / 2F));
        }

        if (this.rightArmPose == BipedModel.ArmPose.CROSSBOW_HOLD && this.swingProgress <= 0.0F) {
            this.arm_right.rotateAngleY = -0.3F + this.bipedHead.rotateAngleY;
            this.arm_left.rotateAngleY = 0.6F + this.bipedHead.rotateAngleY;
            this.arm_right.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX + 0.1F;
            this.arm_left.rotateAngleX = -1.5F + this.bipedHead.rotateAngleX;
        } else if (this.leftArmPose == BipedModel.ArmPose.CROSSBOW_HOLD) {
            this.arm_right.rotateAngleY = -0.6F + this.bipedHead.rotateAngleY;
            this.arm_left.rotateAngleY = 0.3F + this.bipedHead.rotateAngleY;
            this.arm_right.rotateAngleX = -1.5F + this.bipedHead.rotateAngleX;
            this.arm_left.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX + 0.1F;
        }

        if (this.swimAnimation > 0.0F) {
            float f7 = limbSwing % 26.0F;
            float f8 = this.swingProgress > 0.0F ? 0.0F : this.swimAnimation;
            if (f7 < 14.0F) {
                this.arm_left.rotateAngleX = this.rotLerpRad(this.arm_left.rotateAngleX, 0.0F, this.swimAnimation);
                this.arm_right.rotateAngleX = MathHelper.lerp(f8, this.arm_right.rotateAngleX, 0.0F);
                this.arm_left.rotateAngleY = this.rotLerpRad(this.arm_left.rotateAngleY, (float)Math.PI, this.swimAnimation);
                this.arm_right.rotateAngleY = MathHelper.lerp(f8, this.arm_right.rotateAngleY, (float)Math.PI);
                this.arm_left.rotateAngleZ = this.rotLerpRad(this.arm_left.rotateAngleZ, (float)Math.PI + 1.8707964F * this.getArmAngleSq(f7) / this.getArmAngleSq(14.0F), this.swimAnimation);
                this.arm_right.rotateAngleZ = MathHelper.lerp(f8, this.arm_right.rotateAngleZ, (float)Math.PI - 1.8707964F * this.getArmAngleSq(f7) / this.getArmAngleSq(14.0F));
            } else if (f7 >= 14.0F && f7 < 22.0F) {
                float f10 = (f7 - 14.0F) / 8.0F;
                this.arm_left.rotateAngleX = this.rotLerpRad(this.arm_left.rotateAngleX, ((float)Math.PI / 2F) * f10, this.swimAnimation);
                this.arm_right.rotateAngleX = MathHelper.lerp(f8, this.arm_right.rotateAngleX, ((float)Math.PI / 2F) * f10);
                this.arm_left.rotateAngleY = this.rotLerpRad(this.arm_left.rotateAngleY, (float)Math.PI, this.swimAnimation);
                this.arm_right.rotateAngleY = MathHelper.lerp(f8, this.arm_right.rotateAngleY, (float)Math.PI);
                this.arm_left.rotateAngleZ = this.rotLerpRad(this.arm_left.rotateAngleZ, 5.012389F - 1.8707964F * f10, this.swimAnimation);
                this.arm_right.rotateAngleZ = MathHelper.lerp(f8, this.arm_right.rotateAngleZ, 1.2707963F + 1.8707964F * f10);
            } else if (f7 >= 22.0F && f7 < 26.0F) {
                float f9 = (f7 - 22.0F) / 4.0F;
                this.arm_left.rotateAngleX = this.rotLerpRad(this.arm_left.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f9, this.swimAnimation);
                this.arm_right.rotateAngleX = MathHelper.lerp(f8, this.arm_right.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f9);
                this.arm_left.rotateAngleY = this.rotLerpRad(this.arm_left.rotateAngleY, (float)Math.PI, this.swimAnimation);
                this.arm_right.rotateAngleY = MathHelper.lerp(f8, this.arm_right.rotateAngleY, (float)Math.PI);
                this.arm_left.rotateAngleZ = this.rotLerpRad(this.arm_left.rotateAngleZ, (float)Math.PI, this.swimAnimation);
                this.arm_right.rotateAngleZ = MathHelper.lerp(f8, this.arm_right.rotateAngleZ, (float)Math.PI);
            }

            float f11 = 0.3F;
            float f12 = 0.33333334F;
            this.bipedLeftLeg.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.bipedLeftLeg.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F + (float)Math.PI));
            this.bipedRightLeg.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.bipedRightLeg.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F));
        }

        this.bipedHeadwear.copyModelAngles(this.bipedHead);
    }

    protected float rotLerpRad(float angleIn, float maxAngleIn, float mulIn) {
        float f = (maxAngleIn - angleIn) % ((float)Math.PI * 2F);
        if (f < -(float)Math.PI) {
            f += ((float)Math.PI * 2F);
        }

        if (f >= (float)Math.PI) {
            f -= ((float)Math.PI * 2F);
        }

        return angleIn + mulIn * f;
    }

    private float getArmAngleSq(float limbSwing) {
        return -65.0F * limbSwing + limbSwing * limbSwing;
    }
}
