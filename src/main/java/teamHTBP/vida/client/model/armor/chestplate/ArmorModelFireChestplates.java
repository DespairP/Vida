package teamHTBP.vida.client.model.armor.chestplate;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.client.model.AutoRegModel;
import teamHTBP.vida.helper.client.ModelHelper;

@AutoRegModel
public class ArmorModelFireChestplates extends AbstractModelElementChestplates {
    public ArmorModelFireChestplates(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerHumanoid(partDefinition -> {
            var body = partDefinition.addOrReplaceChild("body",
                    CubeListBuilder.create().texOffs(48, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                    , PartPose.offset(0.0F, 0.0F, 0.0F));

            var arm_left = partDefinition.addOrReplaceChild("arm_left",
                    CubeListBuilder.create().texOffs(48, 32).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(5.0F, 2.0F, 0.0F));

            var arm_right = partDefinition.addOrReplaceChild("arm_right",
                    CubeListBuilder.create().texOffs(48, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(-5.0F, 2.0F, 0.0F));


        }, 128, 128);
    }
}
