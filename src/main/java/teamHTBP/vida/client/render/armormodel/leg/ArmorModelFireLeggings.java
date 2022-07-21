package teamHTBP.vida.client.render.armormodel.leg;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.client.render.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class ArmorModelFireLeggings extends AbstractModelElementLeggings {
    public ArmorModelFireLeggings(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerH(partDefinition -> {
            var body_low = partDefinition.addOrReplaceChild("body_low",
                    CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));


        }, 128, 128);
    }

}
