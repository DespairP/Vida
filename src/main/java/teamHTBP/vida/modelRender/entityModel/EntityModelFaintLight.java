package teamHTBP.vida.modelRender.entityModel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.entity.EntityFaintLight;

@OnlyIn(Dist.CLIENT)
public class EntityModelFaintLight extends EntityModel<EntityFaintLight> {
    private final ModelPart body;

    public EntityModelFaintLight() {
        texWidth = 32;
        texHeight = 32;
        body = new ModelPart(this);
        body.setPos(-4.0F, -8, -4);
        body.setTexSize(16, 16);
        body.addBox(-1, -0, -1, 16, 16, 1, 0.0F);

    }

    @Override
    public void setupAnim(EntityFaintLight entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

    }

}
