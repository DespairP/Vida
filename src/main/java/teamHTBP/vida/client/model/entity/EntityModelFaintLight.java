package teamHTBP.vida.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.client.model.AutoRegModel;
import teamHTBP.vida.entity.FaintLight;
import teamHTBP.vida.utils.ModelHelper;

@OnlyIn(Dist.CLIENT)
@AutoRegModel
public class EntityModelFaintLight extends EntityModel<FaintLight> {
    private final ModelPart body;

    public EntityModelFaintLight(ModelPart root) {
        body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayer(partDefinition -> {
            var body = partDefinition.addOrReplaceChild("body",
                    CubeListBuilder.create(), PartPose.offset(-4.0F, -8, -4));
        }, 32, 32);
    }

    @Override
    public void setupAnim(FaintLight entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

}
