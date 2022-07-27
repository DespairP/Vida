package teamHTBP.vida.client.modelRender.entityModel;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.common.entity.EntityFaintLight;

@OnlyIn(Dist.CLIENT)
public class EntityModelFaintLight extends EntityModel<EntityFaintLight> {
    private final ModelRenderer body;

    public EntityModelFaintLight() {
        textureWidth = 32;
        textureHeight = 32;
        body = new ModelRenderer(this);
        body.setRotationPoint(-4.0F, -8, -4);
        body.setTextureSize(16, 16);
        body.addBox(-1, -0, -1, 16, 16, 1, 0.0F);

    }

    @Override
    public void setRotationAngles(EntityFaintLight entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

    }

}
