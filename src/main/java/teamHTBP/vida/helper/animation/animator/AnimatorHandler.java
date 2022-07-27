package teamHTBP.vida.helper.animation.animator;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(Dist.CLIENT)
public class AnimatorHandler {
    @SubscribeEvent
    public static void onEvent(TickEvent.ClientTickEvent event) {
        for (Animator animator : Animator.ANIMATORS) {
            animator.addExistingTick();
        }
    }

    @SubscribeEvent
    public static void onEvent(TickEvent.RenderTickEvent event) {
        for (Animator animator : Animator.ANIMATORS) {
            animator.run(event.renderTickTime);
        }
    }
}
