package teamHTBP.vida.client.render.armormodel.boots;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.player.Player;
import teamHTBP.vida.client.render.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class ArmorModelAquaBoots extends AbstractModelElementBoots<Player> {
    public ArmorModelAquaBoots(ModelPart pRoot) {
        super(pRoot);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerHumanoid(partDefinition -> {
            var leg_left = partDefinition.addOrReplaceChild("leg_left",
                    CubeListBuilder.create().texOffs(12, 73).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(12, 64).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                    , PartPose.offset(2.0F, 12.0F, 0.0F));

            var cube_r1 = leg_left.addOrReplaceChild("cube_r1",
                    CubeListBuilder.create().texOffs(12, 82).addBox(-1.0F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(12, 82).addBox(0.75F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(12, 82).addBox(-2.75F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.5F, 10.0F, -2.0F, 0.0873F, 0.0F, 0.0F));

            var leg_right = partDefinition.addOrReplaceChild("leg_right",
                    CubeListBuilder.create().texOffs(12, 73).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(12, 64).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                    ,PartPose.offset(-2.0F, 12.0F, 0.0F));

            var cube_r2 = leg_right.addOrReplaceChild("cube_r2",
                    CubeListBuilder.create().texOffs(12, 82).addBox(-1.0F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(12, 82).addBox(0.75F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(12, 82).addBox(-2.75F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.5F, 10.0F, -2.0F, 0.0873F, 0.0F, 0.0F));
        }, 128, 128);
    }
}
