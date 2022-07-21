package teamHTBP.vida.modelRender.armormodel.chestplate;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.modelRender.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class ArmorModelEarthChestplates extends AbstractModelElementChestplates {
    public ArmorModelEarthChestplates(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayer(partDefinition -> {
            var arm_right = partDefinition.addOrReplaceChild("arm_right",
                    CubeListBuilder.create().texOffs(26, 36).addBox(-4.25F, -3.0F, -2.5F, 3.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 28).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(0, 37).addBox(-3.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(16, 38).addBox(-4.0F, 5.5F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                    , PartPose.offset(-4.0F, 1.0F, 0.0F));

            var arm_left = partDefinition.addOrReplaceChild("arm_left",
                    CubeListBuilder.create().texOffs(26, 36).addBox(1.25F, -3.0F, -2.5F, 3.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                            .texOffs(0, 28).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(0, 37).addBox(-1.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                            .texOffs(16, 38).addBox(3.0F, 5.5F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(4.0F, 1.0F, 0.0F));

            var body = partDefinition.addOrReplaceChild("body",
                    CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.1F))
                            .texOffs(24, 18).addBox(-4.0F, 0.0F, -3.5F, 8.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                            .texOffs(62, 19).addBox(-3.0F, 0.0F, 1.25F, 6.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                            .texOffs(44, 18).addBox(-3.5F, 5.0F, -3.0F, 7.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                    ,PartPose.offset(0.0F, 0.0F, 0.0F));
        }, 128, 128);
    }
}
