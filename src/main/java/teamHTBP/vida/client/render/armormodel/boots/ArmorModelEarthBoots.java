package teamHTBP.vida.client.render.armormodel.boots;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.client.render.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class ArmorModelEarthBoots extends AbstractModelElementBoots {
    public ArmorModelEarthBoots(ModelPart pRoot) {
        super(pRoot);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerHumanoid(partDefinition -> {
            var leg_left = partDefinition.addOrReplaceChild("leg_left",
                    CubeListBuilder.create().texOffs(22, 56).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(38, 56).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(54, 61).addBox(-1.5F, 5.0F, -2.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    , PartPose.offset(2.0F, 12.0F, 0.0F));

            var bone = leg_left.addOrReplaceChild("bone",
                    CubeListBuilder.create().texOffs(62, 62).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(54, 63).addBox(-1.5F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(54, 62).addBox(0.5F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.0F, 12.25F, -1.75F, -0.1309F, 0.0F, 0.0F));

            var leg_right = partDefinition.addOrReplaceChild("leg_right",
                    CubeListBuilder.create().texOffs(22, 56).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(38, 56).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(54, 61).addBox(-1.5F, 5.0F, -2.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(-2.0F, 12.0F, 0.0F));

            var bone2 = leg_right.addOrReplaceChild("bone2",
                    CubeListBuilder.create().texOffs(62, 62).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(54, 63).addBox(0.5F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(54, 62).addBox(-1.5F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.0F, 12.25F, -1.75F, -0.1309F, 0.0F, 0.0F));


        }, 128, 128);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
