package teamHTBP.vida.client.renderer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.client.renderer.entity.*;
import teamHTBP.vida.common.entity.VidaEntityLoader;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EntityRendererLoader {
    @SubscribeEvent
    public static void onClientSetUpEvent(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(VidaEntityLoader.faintLight.get(), EntityRenderFaintLight::new);
        event.registerEntityRenderer(VidaEntityLoader.ANCIENT_BELIEVER.get(), AncientBelieverRenderer::new);
        event.registerEntityRenderer(VidaEntityLoader.SILENT_BELIEVER.get(), SilentBelieverRenderer::new);
        event.registerEntityRenderer(VidaEntityLoader.ELEMENT_BALL.get(), ElementBallRenderer::new);
        event.registerEntityRenderer(VidaEntityLoader.BARRAGE_SHOOTER.get(), BarrageShooterRenderer::new);
    }
}
