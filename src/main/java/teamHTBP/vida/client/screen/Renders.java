package teamHTBP.vida.client.screen;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.client.Minecraft;

/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Renders {
    private static final RenderTarget TEMP_RT = new TextureTarget(400, 400, true, Minecraft.ON_OSX);

    public static RenderTarget temp() {
        return TEMP_RT;
    }
}
