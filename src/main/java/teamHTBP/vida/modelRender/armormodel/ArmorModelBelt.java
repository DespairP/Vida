package teamHTBP.vida.modelRender.armormodel;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArmorModelBelt extends BipedModel<PlayerEntity> {
    private final ModelRenderer body; //模型主要部分
    private final ModelRenderer bone; //模型次要部分


    public ArmorModelBelt() {
        super(1.0f, 0, 32, 32);
        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-4.5F, 9.9F, -2.5F, 9.0F, 2.0F, 5.0F, 0.0F, false);
        body.setTextureOffset(0, 7).addBox(-0.5F, 9.4F, -2.75F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        body.setTextureOffset(4, 7).addBox(0.5F, 9.4F, -2.75F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        body.setTextureOffset(4, 9).addBox(0.5F, 11.4F, -2.75F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        bone = new ModelRenderer(this);
        bone.setRotationPoint(5.25F, 11.4F, 0.0F);
        body.addChild(bone);
        setRotationAngle(bone, 0.0F, 0.0F, -0.1309F);
        bone.setTextureOffset(10, 7).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
        bone.setTextureOffset(0, 11).addBox(0.05F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        this.body.showModel = true;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setVisible(boolean visible) {
        this.bipedHead.showModel = false;
        this.bipedHeadwear.showModel = false;
        this.bipedBody.showModel = true;
        this.bipedRightArm.showModel = false;
        this.bipedLeftArm.showModel = false;
        this.bipedRightLeg.showModel = false;
        this.bipedLeftLeg.showModel = false;
    }


    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
