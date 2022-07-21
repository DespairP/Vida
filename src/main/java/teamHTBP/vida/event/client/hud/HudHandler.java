package teamHTBP.vida.event.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.GameRenderer;

/**
 * @author DustW
 */
public class HudHandler {
    public static void setupShader() {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
    }
}
