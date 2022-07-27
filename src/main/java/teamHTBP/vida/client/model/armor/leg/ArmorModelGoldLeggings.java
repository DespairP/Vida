package teamHTBP.vida.client.model.armor.leg;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.client.model.AutoRegModel;
import teamHTBP.vida.helper.client.ModelHelper;

@AutoRegModel
public class ArmorModelGoldLeggings extends AbstractModelElementLeggings {
    public ArmorModelGoldLeggings(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerHumanoid(partDefinition -> {
            var body_low = partDefinition.addOrReplaceChild("body_low",
                    CubeListBuilder.create().texOffs(0, 51).addBox(-4.0F, -2.0F, -2.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(0, 57).addBox(-4.75F, -2.5F, -1.5F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 57).addBox(3.75F, -2.5F, -1.5F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                    , PartPose.offset(0.0F, 12.0F, 0.0F));

            var cube_1 = body_low.addOrReplaceChild("cube_1",
                    CubeListBuilder.create().texOffs(18, 59).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(0.0F, 0.0F, 3.75F));

            var cube_2 = body_low.addOrReplaceChild("cube_2",
                    CubeListBuilder.create().texOffs(8, 62).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(0.0F, -0.5F, -0.75F));


        }, 128, 128);
    }

}
