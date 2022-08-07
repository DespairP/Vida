package teamHTBP.vida.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamHTBP.vida.client.model.entity.AbstractGeoModel;
import teamHTBP.vida.common.entity.SilentBeliever;

/**
 * @author DustW
 */
public class SilentBelieverRenderer extends GeoEntityRenderer<SilentBeliever> {
    public SilentBelieverRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AbstractGeoModel<>("silent_believer"));
    }
}
