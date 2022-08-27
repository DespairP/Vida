package teamHTBP.vida.client.shader;

import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.Vida;
import teamHTBP.vida.client.event.listener.ClientTickHandler;

import java.io.IOException;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Shaders {
    public static ShaderInstance burning;
    public static ShaderInstance colorful;
    public static ShaderInstance glowEdge;

    @SubscribeEvent
    public static void onEvent(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceManager(),
                new ResourceLocation(Vida.MOD_ID, "burning"), DefaultVertexFormat.POSITION_TEX), s -> burning = s);
        event.registerShader(new ShaderInstance(event.getResourceManager(),
                new ResourceLocation(Vida.MOD_ID, "colorful"), DefaultVertexFormat.POSITION_TEX), s -> colorful = s);
        event.registerShader(new ShaderInstance(event.getResourceManager(),
                new ResourceLocation(Vida.MOD_ID, "glow_edge"), DefaultVertexFormat.POSITION), s -> glowEdge = s);
    }

    public static final record Point2f(float x, float y) {}

    public static void setUniforms(ShaderInstance shader, Point2f res, Point2f mouse, float partialTick) {
        setUniforms(shader, res, mouse, (int) ClientTickHandler.tick(), partialTick);
    }

    public static void setUniforms(ShaderInstance shader, Point2f res, Point2f mouse, int tick, float partialTick) {
        Uniform iTime = shader.getUniform("iTime");
        if (iTime != null)
            iTime.set((tick + partialTick) / 20);
        Uniform iResolution = shader.getUniform("iResolution");
        if (iResolution != null) {
            iResolution.set(res.x, res.y);
        }
        Uniform iMouse = shader.getUniform("iMouse");
        if (iMouse != null)
            iMouse.set(mouse.x, mouse.y);
    }
}
