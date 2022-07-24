package teamHTBP.vida.client.render.armormodel.leg;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.client.render.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class ArmorModelEarthLeggings extends AbstractModelElementLeggings {


    public ArmorModelEarthLeggings(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerHumanoid(partDefinition -> {
            var body_low = partDefinition.addOrReplaceChild("body_low",
                    CubeListBuilder.create().texOffs(0, 46).addBox(-4.0F, -14.0F, -2.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(14, 52).addBox(-5.0F, -14.0F, -1.5F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                            .texOffs(14, 52).addBox(4.0F, -14.0F, -1.5F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 52).addBox(-3.0F, -14.0F, -3.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 52).addBox(-3.0F, -14.0F, 2.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                    , PartPose.offset(0.0F, 24.0F, 0.0F));


        }, 128, 128);
    }

}
