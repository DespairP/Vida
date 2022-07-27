package teamHTBP.vida.client.renderer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.client.renderer.entity.EntityRenderFaintLight;
import teamHTBP.vida.common.entity.VidaEntityLoader;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EntityRendererLoader {
    @SubscribeEvent
    public static void onClientSetUpEvent(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(VidaEntityLoader.faintLight.get(), EntityRenderFaintLight::new);
    }
}
