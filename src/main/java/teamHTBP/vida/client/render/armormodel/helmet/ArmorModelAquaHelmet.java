package teamHTBP.vida.client.render.armormodel.helmet;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.client.render.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class ArmorModelAquaHelmet extends AbstractModelElementHelmet {

    public ArmorModelAquaHelmet(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerHumanoid(partDefinition -> {
            var head = partDefinition.addOrReplaceChild("head",
                    CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F))
                    ,PartPose.offset(0.0F, 0.0F, 0.0F));

            var bone = head.addOrReplaceChild("bone",
                    CubeListBuilder.create().texOffs(16, 35).addBox(0.0F, -4.25F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    , PartPose.offsetAndRotation(-4.5F, -6.0F, -5.0F, -1.3526F, -0.1309F, 0.0F));

            var cube_r1 = bone.addOrReplaceChild("cube_r1",
                    CubeListBuilder.create().texOffs(16, 35).addBox(-1.85F, -7.35F, -0.7F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(1.0F, -0.25F, 0.0F, 0.0873F, 0.0F, 0.2182F));

            var cube_r2 = bone.addOrReplaceChild("cube_r2",
                    CubeListBuilder.create().texOffs(17, 36).addBox(-1.0F, -1.8F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(1.0F, -0.25F, 0.0F, 0.0F, 0.0F, 1.7017F));

            var bone2 = head.addOrReplaceChild("bone2",
                    CubeListBuilder.create().texOffs(16, 35).addBox(0.0F, -4.25F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(-4.5F, -4.0F, -5.0F, -1.6144F, -0.1309F, 0.0F));

            var cube_r3 = bone2.addOrReplaceChild("cube_r3",
                    CubeListBuilder.create().texOffs(16, 35).addBox(-1.85F, -7.35F, -0.7F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(1.0F, -0.25F, 0.0F, 0.0873F, 0.0F, 0.2182F));

            var cube_r4 = bone2.addOrReplaceChild("cube_r4",
                    CubeListBuilder.create().texOffs(17, 36).addBox(-1.0F, -1.65F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(1.0F, -0.25F, 0.0F, 0.0F, 0.0F, 1.7453F));

            var bone3 = head.addOrReplaceChild("bone3",
                    CubeListBuilder.create().texOffs(16, 35).addBox(0.0F, -4.25F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(-4.5F, -2.0F, -5.0F, -1.8326F, -0.1309F, 0.0F));

            var cube_r5 = bone3.addOrReplaceChild("cube_r5",
                    CubeListBuilder.create().texOffs(16, 35).addBox(-1.85F, -7.35F, -0.7F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(1.0F, -0.25F, 0.0F, 0.0873F, 0.0F, 0.2182F));

            var cube_r6 = bone3.addOrReplaceChild("cube_r6",
                    CubeListBuilder.create().texOffs(17, 36).addBox(-1.0F, -1.65F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(1.0F, -0.25F, 0.0F, 0.0F, 0.0F, 1.7453F));


        }, 128, 128);
    }

}
