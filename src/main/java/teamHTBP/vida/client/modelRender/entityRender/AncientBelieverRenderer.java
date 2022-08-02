package teamHTBP.vida.client.modelRender.entityRender;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamHTBP.vida.Vida;
import teamHTBP.vida.client.model.entity.AncientBelieverModel;
import teamHTBP.vida.common.entity.AncientBeliever;

/**
 * @author DustW
 */
@OnlyIn(Dist.CLIENT)
public class AncientBelieverRenderer extends GeoEntityRenderer<AncientBeliever> {

    public AncientBelieverRenderer(EntityRendererManager renderManager) {
        super(renderManager, new AncientBelieverModel());
    }

    @Override
    public ResourceLocation getEntityTexture(AncientBeliever entity) {
        return new ResourceLocation(Vida.MOD_ID, "textures/entity/ancient_believer.png");
    }
}
