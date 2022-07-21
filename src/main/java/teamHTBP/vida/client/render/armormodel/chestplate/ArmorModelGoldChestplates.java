package teamHTBP.vida.client.render.armormodel.chestplate;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.client.render.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class ArmorModelGoldChestplates extends AbstractModelElementChestplates {

    public ArmorModelGoldChestplates(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerH(partDefinition -> {
            var arm_right = partDefinition.addOrReplaceChild("arm_right",
                    CubeListBuilder.create().texOffs(0, 28).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(0, 37).addBox(-3.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(48, 34).addBox(-4.0F, 4.0F, -2.5F, 1.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
                            .texOffs(38, 37).addBox(-1.0F, -4.15F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                    , PartPose.offset(-4.0F, 1.0F, 0.0F));

            var cube_r1 = arm_right.addOrReplaceChild("cube_r1",
                    CubeListBuilder.create().texOffs(16, 38).addBox(-1.5F, -6.0F, -0.5F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(-3.0F, 2.75F, -0.5F, 0.0F, 0.0F, -0.48F));

            var cube_r2 = arm_right.addOrReplaceChild("cube_r2",
                    CubeListBuilder.create().texOffs(26, 37).addBox(-1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(-3.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

            var arm_left = partDefinition.addOrReplaceChild("arm_left",
                    CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(0, 37).addBox(-1.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(48, 34).addBox(3.0F, 4.0F, -2.5F, 1.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
                            .texOffs(38, 37).addBox(-1.0F, -4.15F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(4.0F, 1.0F, 0.0F));

            var cube_r3 = arm_left.addOrReplaceChild("cube_r3",
                    CubeListBuilder.create().texOffs(16, 38).addBox(-1.5F, -6.0F, -0.5F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(3.0F, 2.75F, -0.5F, 0.0F, 0.0F, 0.48F));

            var cube_r4 = arm_left.addOrReplaceChild("cube_r4",
                    CubeListBuilder.create().texOffs(26, 37).addBox(-1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(3.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

            var body = partDefinition.addOrReplaceChild("body",
                    CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(42, 19).addBox(-3.0F, 3.5F, -2.5F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(42, 19).addBox(-3.0F, 3.5F, 1.5F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(24, 19).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(24, 19).addBox(-4.0F, 0.0F, 2.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(0.0F, 0.0F, 0.0F));
        }, 128, 128);
    }
}
