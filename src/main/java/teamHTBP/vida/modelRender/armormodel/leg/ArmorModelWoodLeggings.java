package teamHTBP.vida.modelRender.armormodel.leg;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.modelRender.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class ArmorModelWoodLeggings extends AbstractModelElementLeggings {

    private final ModelPart bone11;
    private final ModelPart cube_r1;
    private final ModelPart bone9;
    private final ModelPart cube_r2;
    private final ModelPart cube_r3;
    private final ModelPart cube_r4;

    public ArmorModelWoodLeggings(ModelPart root) {
        super(root);

        bone11 = root.getChild("bone11");
        cube_r1 = root.getChild("cube_r1");
        bone9 = root.getChild("bone9");
        cube_r2 = root.getChild("cube_r2");
        cube_r3 = root.getChild("cube_r3");
        cube_r4 = root.getChild("cube_r4");
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayer(partDefinition -> {
            var body_low = partDefinition.addOrReplaceChild("body_low",
                    CubeListBuilder.create().texOffs(0, 58).addBox(-4.0F, -14.0F, -2.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.2F))
                    , PartPose.offset(0.0F, 24.0F, 0.0F));

            var bone11 = body_low.addOrReplaceChild("bone11",
                    CubeListBuilder.create().texOffs(0, 67).addBox(-4.0F, -0.5F, -0.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.0F, -11.75F, 1.5F, 0.1745F, 0.0F, 0.0F));

            var cube_r1 = bone11.addOrReplaceChild("cube_r1",
                    CubeListBuilder.create().texOffs(0, 70).addBox(1.0F, -0.5F, -0.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 70).addBox(6.0F, -0.5F, -0.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(-5.0F, 1.75F, 0.0F, 0.0873F, 0.0F, 0.0F));

            var bone9 = body_low.addOrReplaceChild("bone9",
                    CubeListBuilder.create().texOffs(46, 64).addBox(-4.5F, -13.5F, 1.95F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(0.0F, 0.0F, 0.0F));

            var cube_r2 = bone9.addOrReplaceChild("cube_r2",
                    CubeListBuilder.create().texOffs(24, 58).addBox(-0.5F, -0.5F, -5.5F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                            .texOffs(24, 58).addBox(-9.0F, -0.5F, -5.5F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(4.25F, -12.75F, 2.5F, 0.1745F, 0.0F, 0.0F));

            var cube_r3 = bone9.addOrReplaceChild("cube_r3",
                    CubeListBuilder.create().texOffs(38, 63).addBox(-2.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(4.0F, -11.75F, -2.4F, 0.0F, 0.0F, 0.1745F));

            var cube_r4 = bone9.addOrReplaceChild("cube_r4",
                    CubeListBuilder.create().texOffs(38, 63).addBox(-0.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(-4.0F, -11.75F, -2.4F, 0.0F, 0.0F, -0.1745F));


        });
    }

}
