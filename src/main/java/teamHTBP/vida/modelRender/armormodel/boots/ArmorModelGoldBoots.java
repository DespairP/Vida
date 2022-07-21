package teamHTBP.vida.modelRender.armormodel.boots;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.modelRender.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class ArmorModelGoldBoots extends AbstractModelElementBoots {
    public ArmorModelGoldBoots(ModelPart pRoot) {
        super(pRoot);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayer(partDefinition -> {
            var leg_right = partDefinition.addOrReplaceChild("leg_right",
                    CubeListBuilder.create().texOffs(0, 67).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(0, 76).addBox(-2.0F, 6.9F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(16, 79).addBox(-2.0F, 5.5F, -3.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                    , PartPose.offset(-2.0F, 12.0F, 0.0F));

            var leg_left = partDefinition.addOrReplaceChild("leg_left",
                    CubeListBuilder.create().texOffs(0, 67).addBox(-2.0F, -0.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(0, 76).addBox(-2.0F, 6.9F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(16, 79).addBox(-2.0F, 5.5F, -3.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(2.0F, 12.0F, 0.0F));
        }, 128, 128);
    }
}

