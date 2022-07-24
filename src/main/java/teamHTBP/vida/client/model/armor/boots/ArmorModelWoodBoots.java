package teamHTBP.vida.client.model.armor.boots;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.client.model.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;


@AutoRegModel
public class ArmorModelWoodBoots extends AbstractModelElementBoots {
    public ArmorModelWoodBoots(ModelPart pRoot) {
        super(pRoot);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerHumanoid(partDefinition -> {
            var leg_right = partDefinition.addOrReplaceChild("leg_right",
                    CubeListBuilder.create().texOffs(0, 76).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(16, 77).addBox(-2.5F, 0.25F, -1.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                            .texOffs(24, 76).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                    , PartPose.offset(-2.0F, 12.0F, 0.0F));

            var cube_r1 = leg_right.addOrReplaceChild("cube_r1",
                    CubeListBuilder.create().texOffs(40, 77).addBox(-1.5F, -3.75F, -1.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.0F, 12.0F, -2.0F, -0.1309F, 0.0F, 0.0F));

            var leg_left = partDefinition.addOrReplaceChild("leg_left",
                    CubeListBuilder.create().texOffs(0, 76).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(16, 77).addBox(1.5F, 0.25F, -1.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                            .texOffs(24, 76).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                    ,PartPose.offset(2.0F, 12.0F, 0.0F));

            var cube_r2 = leg_left.addOrReplaceChild("cube_r2",
                    CubeListBuilder.create().texOffs(40, 77).addBox(-1.5F, -3.75F, -1.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.0F, 12.0F, -2.0F, -0.1309F, 0.0F, 0.0F));
        }, 128, 128);
    }
}
