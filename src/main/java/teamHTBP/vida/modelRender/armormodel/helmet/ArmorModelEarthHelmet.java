package teamHTBP.vida.modelRender.armormodel.helmet;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import teamHTBP.vida.modelRender.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@AutoRegModel
public class ArmorModelEarthHelmet extends AbstractModelElementHelmet {
    public ArmorModelEarthHelmet(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayer(partDefinition -> {
            var head = partDefinition.addOrReplaceChild("head",
                    CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F))
                            .texOffs(64, 12).addBox(-1.5F, -9.5F, -4.35F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(72, 12).addBox(1.5F, -9.0F, -4.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                            .texOffs(72, 12).addBox(-2.5F, -9.0F, -4.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    , PartPose.offset(0.0F, 0.0F, 0.0F));


        }, 128, 128);
    }
}
