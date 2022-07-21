package teamHTBP.vida.modelRender.tilemodel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.modelRender.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class PrismTableModel extends TileEntityModel {
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bone3;
    private final ModelPart bone4;

    public PrismTableModel(ModelPart part) {
        super(part);

        bone = part.getChild("bone");
        bone2 = part.getChild("bone2");
        bone3 = part.getChild("bone3");
        bone4 = part.getChild("bone4");
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayer(partDefinition -> {
            var bone = partDefinition.addOrReplaceChild("bone",
                    CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -3.0F, -7.0F, 14.0F, 3.0F, 14.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 17).addBox(-5.0F, -16.0F, -5.0F, 10.0F, 13.0F, 10.0F, new CubeDeformation(0.0F))
                    , PartPose.offset(0.0F, 24.0F, 0.0F));

            var bone2 = bone.addOrReplaceChild("bone2",
                    CubeListBuilder.create().texOffs(29, 75).addBox(-6.0F, 1.0F, -7.0F, 12.0F, 0.0F, 14.0F, new CubeDeformation(0.0F))
                            .texOffs(12, 40).addBox(-6.0F, -3.0F, -7.0F, 12.0F, 4.0F, 14.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.0F, -15.75F, 0.0F, 0.2182F, 0.0F, 0.0F));

            var bone3 = partDefinition.addOrReplaceChild("bone3",
                    CubeListBuilder.create().texOffs(0, 40).addBox(7.0F, -16.0F, -9.0F, 3.0F, 16.0F, 3.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 40).addBox(-10.0F, -16.0F, -9.0F, 3.0F, 16.0F, 3.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 40).addBox(-10.0F, -20.0F, 6.0F, 3.0F, 20.0F, 3.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 40).addBox(7.0F, -20.0F, 6.0F, 3.0F, 20.0F, 3.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(0.0F, 24.0F, 0.0F));

            var bone4 = bone3.addOrReplaceChild("bone4",
                    CubeListBuilder.create().texOffs(0, 58).addBox(-11.0F, -4.0F, -10.0F, 5.0F, 4.0F, 20.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 58).addBox(6.0F, -4.0F, -10.0F, 5.0F, 4.0F, 20.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 82).addBox(-8.0F, -8.0F, -9.0F, 2.0F, 4.0F, 18.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 82).addBox(6.0F, -8.0F, -9.0F, 2.0F, 4.0F, 18.0F, new CubeDeformation(0.0F))
                            .texOffs(40, 86).addBox(-6.0F, -5.5F, -8.0F, 12.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 104).addBox(-6.0F, -6.0F, -9.0F, 12.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 104).addBox(-6.0F, -7.0F, 8.0F, 12.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.0F, -16.0F, 0.0F, 0.2182F, 0.0F, 0.0F));


        }, 128, 128);
    }


    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {
        bone.render(matrixStack, iVertexBuilder, i, i1, v, v1, v2, v3);
    }
}
