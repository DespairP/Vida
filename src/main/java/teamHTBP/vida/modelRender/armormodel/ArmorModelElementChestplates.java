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
    private float remainingItemUseTime;

    public ArmorModelElementChestplates() {
        super(1.0f, 0, 64, 64);
    }

    protected ModelRenderer getArmForSide(HandSide side) {
        return side == HandSide.LEFT ? this.field_178724_i : this.field_178723_h;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
       // this.field_178723_h.copyModelAngles(this.bipedRightArm);
       // this.field_78115_e.copyModelAngles(this.bipedBody);
        //this.field_178724_i.copyModelAngles(this.bipedLeftArm);
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }


    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        //this.field_178723_h.copyModelAngles(this.bipedRightArm);
       // this.field_78115_e.copyModelAngles(this.bipedBody);
      //  this.field_178724_i.copyModelAngles(this.bipedLeftArm);
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

        this.field_78115_e.rotateAngleY = 0.0F;
        this.field_178723_h.rotationPointZ = 0.0F;
        this.field_178723_h.rotationPointX = -5.0F;
        this.field_178724_i.rotationPointZ = 0.0F;
        this.field_178724_i.rotationPointX = 5.0F;
        float f = 1.0F;
        if (flag) {
            f = (float)entityIn.getMotion().lengthSquared();
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        this.field_178723_h.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.field_178724_i.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.field_178723_h.rotateAngleZ = 0.0F;
        this.field_178724_i.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;
        this.bipedRightLeg.rotateAngleZ = 0.0F;
        this.bipedLeftLeg.rotateAngleZ = 0.0F;
        if (this.isSitting) {
            this.field_178723_h.rotateAngleX += (-(float)Math.PI / 5F);
            this.field_178724_i.rotateAngleX += (-(float)Math.PI / 5F);
            this.bipedRightLeg.rotateAngleX = -1.4137167F;
            this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.bipedRightLeg.rotateAngleZ = 0.07853982F;
            this.bipedLeftLeg.rotateAngleX = -1.4137167F;
            this.bipedLeftLeg.rotateAngleY = (-(float)Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleZ = -0.07853982F;
        }

        this.field_178723_h.rotateAngleY = 0.0F;
        this.field_178723_h.rotateAngleZ = 0.0F;
        switch(this.leftArmPose) {
            case EMPTY:
                this.field_178724_i.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.field_178724_i.rotateAngleX = this.field_178724_i.rotateAngleX * 0.5F - 0.9424779F;
                this.field_178724_i.rotateAngleY = ((float)Math.PI / 6F);
                break;
            case ITEM:
                this.field_178724_i.rotateAngleX = this.field_178724_i.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
                this.field_178724_i.rotateAngleY = 0.0F;
        }

        switch(this.rightArmPose) {
            case EMPTY:
                this.field_178723_h.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.field_178723_h.rotateAngleX = this.field_178723_h.rotateAngleX * 0.5F - 0.9424779F;
                this.field_178723_h.rotateAngleY = (-(float)Math.PI / 6F);
                break;
            case ITEM:
                this.field_178723_h.rotateAngleX = this.field_178723_h.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
                this.field_178723_h.rotateAngleY = 0.0F;
                break;
            case THROW_SPEAR:
                this.field_178723_h.rotateAngleX = this.field_178723_h.rotateAngleX * 0.5F - (float)Math.PI;
                this.field_178723_h.rotateAngleY = 0.0F;
        }

        if (this.leftArmPose == BipedModel.ArmPose.THROW_SPEAR && this.rightArmPose != BipedModel.ArmPose.BLOCK && this.rightArmPose != BipedModel.ArmPose.THROW_SPEAR && this.rightArmPose != BipedModel.ArmPose.BOW_AND_ARROW) {
            this.field_178724_i.rotateAngleX = this.field_178724_i.rotateAngleX * 0.5F - (float)Math.PI;
            this.field_178724_i.rotateAngleY = 0.0F;
        }

        if (this.swingProgress > 0.0F) {
            HandSide handside = this.getMainHand(entityIn);
            ModelRenderer modelrenderer = this.getArmForSide(handside);
            float f1 = this.swingProgress;
            this.field_78115_e.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;
            if (handside == HandSide.LEFT) {
                this.field_78115_e.rotateAngleY *= -1.0F;
            }

            this.field_178723_h.rotationPointZ = MathHelper.sin(this.field_78115_e.rotateAngleY) * 5.0F;
            this.field_178723_h.rotationPointX = -MathHelper.cos(this.field_78115_e.rotateAngleY) * 5.0F;
            this.field_178724_i.rotationPointZ = -MathHelper.sin(this.field_78115_e.rotateAngleY) * 5.0F;
            this.field_178724_i.rotationPointX = MathHelper.cos(this.field_78115_e.rotateAngleY) * 5.0F;
            this.field_178723_h.rotateAngleY += this.field_78115_e.rotateAngleY;
            this.field_178724_i.rotateAngleY += this.field_78115_e.rotateAngleY;
            this.field_178724_i.rotateAngleX += this.field_78115_e.rotateAngleY;
            f1 = 1.0F - this.swingProgress;
            f1 = f1 * f1;
            f1 = f1 * f1;
            f1 = 1.0F - f1;
            float f2 = MathHelper.sin(f1 * (float)Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float)((double)modelrenderer.rotateAngleX - ((double)f2 * 1.2D + (double)f3));
            modelrenderer.rotateAngleY += this.field_78115_e.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
        }

        if (this.isSneak) {
            this.field_78115_e.rotateAngleX = 0.5F;
            this.field_178723_h.rotateAngleX += 0.4F;
            this.field_178724_i.rotateAngleX += 0.4F;
            this.bipedRightLeg.rotationPointZ = 4.0F;
            this.bipedLeftLeg.rotationPointZ = 4.0F;
            this.bipedRightLeg.rotationPointY = 12.2F;
            this.bipedLeftLeg.rotationPointY = 12.2F;
            this.bipedHead.rotationPointY = 4.2F;
            this.field_78115_e.rotationPointY = 3.2F;
            this.field_178724_i.rotationPointY = 5.2F;
            this.field_178723_h.rotationPointY = 5.2F;
        } else {
            this.field_78115_e.rotateAngleX = 0.0F;
            this.bipedRightLeg.rotationPointZ = 0.1F;
            this.bipedLeftLeg.rotationPointZ = 0.1F;
            this.bipedRightLeg.rotationPointY = 12.0F;
            this.bipedLeftLeg.rotationPointY = 12.0F;
            this.bipedHead.rotationPointY = 0.0F;
            this.field_78115_e.rotationPointY = 0.0F;
            this.field_178724_i.rotationPointY = 2.0F;
            this.field_178723_h.rotationPointY = 2.0F;
        }

        this.field_178723_h.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.field_178724_i.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.field_178723_h.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.field_178724_i.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        if (this.rightArmPose == BipedModel.ArmPose.BOW_AND_ARROW) {
            this.field_178723_h.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
            this.field_178724_i.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY + 0.4F;
            this.field_178723_h.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.field_178724_i.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
        } else if (this.leftArmPose == BipedModel.ArmPose.BOW_AND_ARROW && this.rightArmPose != BipedModel.ArmPose.THROW_SPEAR && this.rightArmPose != BipedModel.ArmPose.BLOCK) {
            this.field_178723_h.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
            this.field_178724_i.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
            this.field_178723_h.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.field_178724_i.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
        }

        float f4 = (float) CrossbowItem.getChargeTime(entityIn.getActiveItemStack());
        if (this.rightArmPose == BipedModel.ArmPose.CROSSBOW_CHARGE) {
            this.field_178723_h.rotateAngleY = -0.8F;
            this.field_178723_h.rotateAngleX = -0.97079635F;
            this.field_178724_i.rotateAngleX = -0.97079635F;
            float f5 = MathHelper.clamp(this.remainingItemUseTime, 0.0F, f4);
            this.field_178724_i.rotateAngleY = MathHelper.lerp(f5 / f4, 0.4F, 0.85F);
            this.field_178724_i.rotateAngleX = MathHelper.lerp(f5 / f4, this.field_178724_i.rotateAngleX, (-(float)Math.PI / 2F));
        } else if (this.leftArmPose == BipedModel.ArmPose.CROSSBOW_CHARGE) {
            this.field_178724_i.rotateAngleY = 0.8F;
            this.field_178723_h.rotateAngleX = -0.97079635F;
            this.field_178724_i.rotateAngleX = -0.97079635F;
            float f6 = MathHelper.clamp(this.remainingItemUseTime, 0.0F, f4);
            this.field_178723_h.rotateAngleY = MathHelper.lerp(f6 / f4, -0.4F, -0.85F);
            this.field_178723_h.rotateAngleX = MathHelper.lerp(f6 / f4, this.field_178723_h.rotateAngleX, (-(float)Math.PI / 2F));
        }

        if (this.rightArmPose == BipedModel.ArmPose.CROSSBOW_HOLD && this.swingProgress <= 0.0F) {
            this.field_178723_h.rotateAngleY = -0.3F + this.bipedHead.rotateAngleY;
            this.field_178724_i.rotateAngleY = 0.6F + this.bipedHead.rotateAngleY;
            this.field_178723_h.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX + 0.1F;
            this.field_178724_i.rotateAngleX = -1.5F + this.bipedHead.rotateAngleX;
        } else if (this.leftArmPose == BipedModel.ArmPose.CROSSBOW_HOLD) {
            this.field_178723_h.rotateAngleY = -0.6F + this.bipedHead.rotateAngleY;
            this.field_178724_i.rotateAngleY = 0.3F + this.bipedHead.rotateAngleY;
            this.field_178723_h.rotateAngleX = -1.5F + this.bipedHead.rotateAngleX;
            this.field_178724_i.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX + 0.1F;
        }

        if (this.swimAnimation > 0.0F) {
            float f7 = limbSwing % 26.0F;
            float f8 = this.swingProgress > 0.0F ? 0.0F : this.swimAnimation;
            if (f7 < 14.0F) {
                this.field_178724_i.rotateAngleX = this.rotLerpRad(this.field_178724_i.rotateAngleX, 0.0F, this.swimAnimation);
                this.field_178723_h.rotateAngleX = MathHelper.lerp(f8, this.field_178723_h.rotateAngleX, 0.0F);
                this.field_178724_i.rotateAngleY = this.rotLerpRad(this.field_178724_i.rotateAngleY, (float)Math.PI, this.swimAnimation);
                this.field_178723_h.rotateAngleY = MathHelper.lerp(f8, this.field_178723_h.rotateAngleY, (float)Math.PI);
                this.field_178724_i.rotateAngleZ = this.rotLerpRad(this.field_178724_i.rotateAngleZ, (float)Math.PI + 1.8707964F * this.getArmAngleSq(f7) / this.getArmAngleSq(14.0F), this.swimAnimation);
                this.field_178723_h.rotateAngleZ = MathHelper.lerp(f8, this.field_178723_h.rotateAngleZ, (float)Math.PI - 1.8707964F * this.getArmAngleSq(f7) / this.getArmAngleSq(14.0F));
            } else if (f7 >= 14.0F && f7 < 22.0F) {
                float f10 = (f7 - 14.0F) / 8.0F;
                this.field_178724_i.rotateAngleX = this.rotLerpRad(this.field_178724_i.rotateAngleX, ((float)Math.PI / 2F) * f10, this.swimAnimation);
                this.field_178723_h.rotateAngleX = MathHelper.lerp(f8, this.field_178723_h.rotateAngleX, ((float)Math.PI / 2F) * f10);
                this.field_178724_i.rotateAngleY = this.rotLerpRad(this.field_178724_i.rotateAngleY, (float)Math.PI, this.swimAnimation);
                this.field_178723_h.rotateAngleY = MathHelper.lerp(f8, this.field_178723_h.rotateAngleY, (float)Math.PI);
                this.field_178724_i.rotateAngleZ = this.rotLerpRad(this.field_178724_i.rotateAngleZ, 5.012389F - 1.8707964F * f10, this.swimAnimation);
                this.field_178723_h.rotateAngleZ = MathHelper.lerp(f8, this.field_178723_h.rotateAngleZ, 1.2707963F + 1.8707964F * f10);
            } else if (f7 >= 22.0F && f7 < 26.0F) {
                float f9 = (f7 - 22.0F) / 4.0F;
                this.field_178724_i.rotateAngleX = this.rotLerpRad(this.field_178724_i.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f9, this.swimAnimation);
                this.field_178723_h.rotateAngleX = MathHelper.lerp(f8, this.field_178723_h.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f9);
                this.field_178724_i.rotateAngleY = this.rotLerpRad(this.field_178724_i.rotateAngleY, (float)Math.PI, this.swimAnimation);
                this.field_178723_h.rotateAngleY = MathHelper.lerp(f8, this.field_178723_h.rotateAngleY, (float)Math.PI);
                this.field_178724_i.rotateAngleZ = this.rotLerpRad(this.field_178724_i.rotateAngleZ, (float)Math.PI, this.swimAnimation);
                this.field_178723_h.rotateAngleZ = MathHelper.lerp(f8, this.field_178723_h.rotateAngleZ, (float)Math.PI);
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
