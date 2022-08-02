package teamHTBP.vida.client.modelRender;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.client.modelRender.entityRender.AncientBelieverRenderer;
import teamHTBP.vida.common.entity.EntityLoader;
import teamHTBP.vida.client.modelRender.entityRender.EntityRenderFaintLight;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EntityRendererLoader {
    @SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityLoader.faintLight.get(), EntityRenderFaintLight::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLoader.ANCIENT_BELIEVER.get(), AncientBelieverRenderer::new);
    }
}
