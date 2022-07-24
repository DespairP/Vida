package teamHTBP.vida.client.render.armormodel.helmet;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.client.render.AutoRegModel;
import teamHTBP.vida.utils.ModelHelper;

@OnlyIn(Dist.CLIENT)
@AutoRegModel
public class ArmorModelFireHelmet<T extends Entity> extends AbstractModelElementHelmet {

    public ArmorModelFireHelmet(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        return ModelHelper.createBodyLayerHumanoid(partDefinition -> {
            var head = partDefinition.addOrReplaceChild("head",
                    CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                    , PartPose.offset(0.0F, 0.0F, 0.0F));


        }, 128, 128);
    }

}
