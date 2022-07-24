package teamHTBP.vida.client.model.armor.helmet;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.Entity;
import teamHTBP.vida.client.model.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class ArmorModelGoldHelmet<T extends Entity> extends AbstractModelElementHelmet {

    public ArmorModelGoldHelmet(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerHumanoid(partDefinition -> {
            var head = partDefinition.addOrReplaceChild("head",
                    CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F))
                    , PartPose.offset(0.0F, 0.0F, 0.0F));

            var bone = head.addOrReplaceChild("bone",
                    CubeListBuilder.create().texOffs(72, 11).addBox(-1.0F, 1.0F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(84, 11).addBox(-1.0F, -5.0F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(106, 12).addBox(2.0F, -4.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(106, 12).addBox(-3.0F, -4.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(78, 13).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(90, 13).addBox(-4.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(98, 13).addBox(1.0F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(64, 14).addBox(1.0F, 4.0F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(64, 14).addBox(-4.0F, 4.0F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    ,PartPose.offsetAndRotation(0.0F, -5.5F, -4.25F, 0.0436F, 0.0F, 0.0F));


        }, 128, 128);
    }

}
