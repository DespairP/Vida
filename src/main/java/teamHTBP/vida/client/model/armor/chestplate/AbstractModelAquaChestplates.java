package teamHTBP.vida.client.model.armor.chestplate;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.client.model.AutoRegModel;
import teamHTBP.vida.helper.client.ModelHelper;

@AutoRegModel
public class AbstractModelAquaChestplates extends AbstractModelElementChestplates {

    public AbstractModelAquaChestplates(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerHumanoid(partDefinition -> {
            var arm_left = partDefinition.addOrReplaceChild("arm_left",
                    CubeListBuilder.create().texOffs(24, 41).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(40, 41).addBox(-1.0F, 7.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(24, 36).addBox(-1.25F, -4.0F, -2.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                            .texOffs(24, 36).addBox(0.25F, -4.5F, -2.75F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                            .texOffs(24, 36).addBox(1.5F, -3.25F, -2.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                            .texOffs(24, 36).addBox(1.75F, -3.75F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                            .texOffs(24, 36).addBox(1.5F, -3.5F, 1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                            .texOffs(24, 36).addBox(0.25F, -3.25F, 0.75F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                            .texOffs(24, 36).addBox(-1.75F, -3.5F, 0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                            .texOffs(24, 36).addBox(-1.5F, -3.75F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
                    PartPose.offset(4.0F, 1.0F, 0.0F));

            var arm_right = partDefinition.addOrReplaceChild("arm_right",
                    CubeListBuilder.create().texOffs(0, 41).addBox(-3.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(0, 32).addBox(-3.0F, -0.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F)),
                    PartPose.offset(-4.0F, 1.0F, 0.0F));

            var bone4 = arm_right.addOrReplaceChild("bone4",
            CubeListBuilder.create().texOffs(16, 35).addBox(-3.5F, -4.0F, 0.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    .texOffs(16, 35).addBox(-2.5F, -4.5F, 1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    .texOffs(16, 35).addBox(-3.75F, -5.0F, -0.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    .texOffs(16, 35).addBox(-3.75F, 5.0F, -0.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    .texOffs(16, 35).addBox(-3.25F, 3.5F, 0.75F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    .texOffs(16, 35).addBox(-4.0F, 4.0F, -1.75F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    .texOffs(16, 35).addBox(-3.75F, 4.5F, -2.75F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    .texOffs(16, 35).addBox(-2.75F, -4.25F, -2.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offset(0.0F, 0.0F, 0.0F));

            var body = partDefinition.addOrReplaceChild("body",
                    CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.2F)),
            PartPose.offset(0.0F, 0.0F, 0.0F));

            var bone5 = body.addOrReplaceChild("body",
                    CubeListBuilder.create().texOffs(0, 28).addBox(-0.5F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
                    PartPose.offsetAndRotation(-4.5F, -0.5F, -2.25F, 0.0F, 0.0F, 0.6109F));

            var cube_r1 = bone5.addOrReplaceChild("cube_r1",
                    CubeListBuilder.create().texOffs(0, 30).addBox(-4.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(0.0F, 0.0F, -0.25F, 0.0F, 1.3963F, 0.0F));

            var bone6 = body.addOrReplaceChild("bone6",
                    CubeListBuilder.create().texOffs(0, 28).addBox(-0.5F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 28).addBox(0.4403F, 0.9759F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(-5.0F, 1.0F, -2.25F, 0.0F, 0.0F, 0.5672F));

            var bone11 = body.addOrReplaceChild("bone11",
                    CubeListBuilder.create().texOffs(32, 33).addBox(-4.25F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                            .texOffs(32, 33).addBox(2.25F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                            .texOffs(32, 33).addBox(-1.25F, -6.0905F, -0.987F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(0.0F, 5.0F, 1.75F, -0.0873F, 0.0F, 0.0F));
        }, 128, 128);
    }
}
