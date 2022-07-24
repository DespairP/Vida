package teamHTBP.vida.client.model.armor.leg;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.client.model.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class ArmorModelAquaLeggings extends AbstractModelElementLeggings {


    public ArmorModelAquaLeggings(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerHumanoid(partDefinition -> {
            var body_low = partDefinition.addOrReplaceChild("body_low",
                    CubeListBuilder.create().texOffs(0, 57).addBox(-4.5F, -14.0F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 50).addBox(-4.0F, -14.5F, -2.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(0, 57).addBox(1.5F, -14.0F, -0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 63).addBox(1.5F, -11.0F, -0.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 63).addBox(-4.5F, -11.0F, -0.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 77).addBox(-1.5F, -12.0F, -0.75F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F))
                    , PartPose.offset(0.0F, 24.0F, 0.0F));


        }, 128, 128);
    }

}
