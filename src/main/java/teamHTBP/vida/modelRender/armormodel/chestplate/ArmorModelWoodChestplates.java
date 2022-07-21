package teamHTBP.vida.modelRender.armormodel.chestplate;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.modelRender.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class ArmorModelWoodChestplates extends AbstractModelElementChestplates {
    private final ModelPart bone6;
    private final ModelPart bone3;
    private final ModelPart bone10;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    private final ModelPart bone7;
    private final ModelPart cube_r3;
    private final ModelPart bone8;
    private final ModelPart cube_r4;

    public ArmorModelWoodChestplates(ModelPart root) {
        super(root);

        bone6 = root.getChild("bone6");
        bone3 = root.getChild("bone3");
        bone10 = root.getChild("bone10");
        cube_r1 = root.getChild("cube_r1");
        cube_r2 = root.getChild("cube_r2");
        bone7 = root.getChild("bone7");
        cube_r3 = root.getChild("cube_r3");
        bone8 = root.getChild("bone8");
        cube_r4 = root.getChild("cube_r4");
    }

    // TODO
    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayer(partDefinition -> {
            var arm_left = partDefinition.addOrReplaceChild("arm_left",
                    CubeListBuilder.create().texOffs(0, 28).addBox(0.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(0, 38).addBox(-1.0F, 5.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(0, 48).addBox(2.5F, 4.5F, -1.5F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(4.0F, 1.0F, 0.0F));

            var bone6 = arm_left.addOrReplaceChild("bone6",
                    CubeListBuilder.create().texOffs(16, 28).addBox(2.75F, -2.5F, -2.5F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                            .texOffs(16, 28).addBox(4.0F, -2.75F, -2.25F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                            .texOffs(16, 28).addBox(1.25F, -2.75F, -2.25F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(0.0F, 0.0F, 0.0F));

            var arm_right = partDefinition.addOrReplaceChild("arm_right",
                    CubeListBuilder.create().texOffs(0, 28).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(0, 38).addBox(-3.0F, 5.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(0, 48).addBox(-3.5F, 4.5F, -1.5F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(-4.0F, 1.0F, 0.0F));

            var bone3 = arm_right.addOrReplaceChild("bone3",
                    CubeListBuilder.create().texOffs(16, 28).addBox(-3.75F, -2.5F, -2.5F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                            .texOffs(16, 28).addBox(-5.0F, -2.75F, -2.25F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                            .texOffs(16, 28).addBox(-2.25F, -2.75F, -2.25F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(0.0F, 0.0F, 0.0F));

            var body = partDefinition.addOrReplaceChild("body",
                    CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.2F))
                    , PartPose.offset(0.0F, 0.0F, 0.0F));

            var bone10 = body.addOrReplaceChild("bone10",
                    CubeListBuilder.create(),PartPose.offset(-3.0F, 12.0F, 0.0F));

            var cube_r1 = bone10.addOrReplaceChild("cube_r1",
                    CubeListBuilder.create().texOffs(32, 19).addBox(-0.5F, -0.75F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(32, 19).addBox(-4.5F, -0.75F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(5.0F, -10.75F, -2.75F, 0.0873F, 0.0F, 0.0F));

            var cube_r2 = bone10.addOrReplaceChild("cube_r2",
                    CubeListBuilder.create().texOffs(24, 19).addBox(-1.5F, -0.75F, 0.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(3.0F, -10.75F, -2.75F, 0.1745F, 0.0F, 0.0F));

            var bone7 = body.addOrReplaceChild("bone7",
                    CubeListBuilder.create().texOffs(56, 20).addBox(-5.0F, -0.5F, -0.5F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(36, 19).addBox(-4.8F, 0.4F, -5.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(36, 19).addBox(1.8F, 0.4F, -5.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.0F, 1.75F, 2.5F, 0.0873F, 0.0F, 0.0F));

            var cube_r3 = bone7.addOrReplaceChild("cube_r3",
                    CubeListBuilder.create().texOffs(44, 20).addBox(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                            .texOffs(44, 20).addBox(8.35F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(-4.4F, 0.1F, -0.5F, 0.1745F, 0.0F, 0.0F));

            var bone8 = body.addOrReplaceChild("bone8",
                    CubeListBuilder.create().texOffs(56, 20).addBox(-5.0F, -0.5F, -0.5F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(36, 19).addBox(-4.8F, 0.4F, -5.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(36, 19).addBox(1.8F, 0.4F, -5.4F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.0F, 0.25F, 2.5F, 0.0436F, 0.0F, 0.0F));

            var cube_r4 = bone8.addOrReplaceChild("cube_r4",
                    CubeListBuilder.create().texOffs(44, 20).addBox(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                            .texOffs(44, 20).addBox(8.35F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(-4.4F, 0.1F, -0.5F, 0.1745F, 0.0F, 0.0F));


        }, 128, 128);
    }

}
