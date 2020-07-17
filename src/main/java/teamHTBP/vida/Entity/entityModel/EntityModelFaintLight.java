package teamHTBP.vida.Entity.entityModel;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.Entity.EntityFaintLight;

public class EntityModelFaintLight extends EntityModel<EntityFaintLight> {
    private final ModelRenderer body;

    public EntityModelFaintLight(){
        textureWidth = 32;
        textureHeight = 32;
        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 0);
        body.addBox( -8.0F, -16.0F, 0.0F, 32, 32 , 0, 0.0F);
    }
    @Override
    public void setRotationAngles(EntityFaintLight entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
          body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

    }

}
