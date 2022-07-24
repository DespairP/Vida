package teamHTBP.vida.client.render.armormodel.helmet;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.client.render.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class ArmorModelWoodHelmet extends AbstractModelElementHelmet {

    public ArmorModelWoodHelmet(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerHumanoid(partDefinition -> {
            var head = partDefinition.addOrReplaceChild("head",
                    CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F))
                    ,PartPose.offset(0.0F, 0.0F, 0.0F));

            var bone = head.addOrReplaceChild("bone",
                    CubeListBuilder.create(),PartPose.offsetAndRotation(3.75F, -1.5F, -4.0F, -0.1745F, 0.0F, 0.0F));

            var cube_r1 = bone.addOrReplaceChild("cube_r1",
                    CubeListBuilder.create().texOffs(64, 6).addBox(-3.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(1.75F, 0.5F, -0.25F, 0.0F, -0.1745F, 0.0F));

            var cube_r2 = bone.addOrReplaceChild("cube_r2",
                    CubeListBuilder.create().texOffs(80, 3).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.8F, 0.0F, 2.75F, 0.4363F, 0.0F, 0.0F));

            var cube_r3 = bone.addOrReplaceChild("cube_r3",
                    CubeListBuilder.create().texOffs(70, 3).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.75F, 0.5F, -0.25F, 0.1745F, 0.0F, 0.0F));

            var bone4 = head.addOrReplaceChild("bone4",
                    CubeListBuilder.create(),PartPose.offsetAndRotation(-3.75F, -1.5F, -4.0F, -0.1745F, 0.0F, 0.0F));

            var cube_r4 = bone4.addOrReplaceChild("cube_r4",
                    CubeListBuilder.create().texOffs(64, 6).addBox(1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(-1.75F, 0.5F, -0.25F, 0.0F, 0.1745F, 0.0F));

            var cube_r5 = bone4.addOrReplaceChild("cube_r5",
                    CubeListBuilder.create().texOffs(80, 3).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                    , PartPose.offsetAndRotation(-0.8F, 0.0F, 2.75F, 0.4363F, 0.0F, 0.0F));

            var cube_r6 = bone4.addOrReplaceChild("cube_r6",
                    CubeListBuilder.create().texOffs(70, 3).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(-0.75F, 0.5F, -0.25F, 0.1745F, 0.0F, 0.0F));

            var bone2 = head.addOrReplaceChild("bone2",
                    CubeListBuilder.create(),PartPose.offsetAndRotation(3.75F, -7.0F, -4.0F, 0.1309F, 0.1745F, 0.0F));

            var cube_r7 = bone2.addOrReplaceChild("cube_r7",
                    CubeListBuilder.create().texOffs(64, 6).addBox(-3.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(1.75F, 0.5F, -0.25F, 0.0F, -0.1745F, 0.0F));

            var cube_r8 = bone2.addOrReplaceChild("cube_r8",
                    CubeListBuilder.create().texOffs(80, 3).addBox(-0.8086F, -0.8413F, 0.7076F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.8F, 0.0F, 2.75F, 0.0873F, -0.4363F, 0.0F));

            var cube_r9 = bone2.addOrReplaceChild("cube_r9",
                    CubeListBuilder.create().texOffs(70, 3).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.75F, 0.5F, -0.25F, 0.1745F, 0.0F, 0.0F));

            var bone5 = head.addOrReplaceChild("bone5",
                    CubeListBuilder.create(),PartPose.offsetAndRotation(-3.75F, -7.0F, -4.0F, 0.1309F, -0.1745F, 0.0F));

            var cube_r10 = bone5.addOrReplaceChild("cube_r10",
                    CubeListBuilder.create().texOffs(64, 6).addBox(1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(-1.75F, 0.5F, -0.25F, 0.0F, 0.1745F, 0.0F));

            var cube_r11 = bone5.addOrReplaceChild("cube_r11",
                    CubeListBuilder.create().texOffs(80, 3).addBox(-0.1914F, -0.8413F, 0.7076F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(-0.8F, 0.0F, 2.75F, 0.0873F, 0.4363F, 0.0F));

            var cube_r12 = bone5.addOrReplaceChild("cube_r12",
                    CubeListBuilder.create().texOffs(70, 3).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(-0.75F, 0.5F, -0.25F, 0.1745F, 0.0F, 0.0F));


        }, 128, 128);
    }

}
