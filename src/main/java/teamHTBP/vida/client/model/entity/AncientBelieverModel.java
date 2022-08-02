package teamHTBP.vida.client.model.entity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.entity.AncientBeliever;

/**
 * @author DustW
 */
public class AncientBelieverModel extends AnimatedTickingGeoModel<AncientBeliever> {

    @Override
    public ResourceLocation getModelLocation(AncientBeliever object) {
        return new ResourceLocation(Vida.MOD_ID, "geo/entity/ancient_believer.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AncientBeliever object) {
        return new ResourceLocation(Vida.MOD_ID, "textures/entity/ancient_believer.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AncientBeliever animatable) {
        return new ResourceLocation(Vida.MOD_ID, "animations/entity/ancient_believer.animation.json");
    }
}
