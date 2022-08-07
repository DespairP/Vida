package teamHTBP.vida.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamHTBP.vida.client.model.entity.AbstractGeoModel;
import teamHTBP.vida.common.entity.AncientBeliever;

/**
 * @author DustW
 */
public class AncientBelieverRenderer extends GeoEntityRenderer<AncientBeliever> {
    public AncientBelieverRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AbstractGeoModel<>("ancient_believer"));
    }
}
