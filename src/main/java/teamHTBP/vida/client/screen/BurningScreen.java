package teamHTBP.vida.client.screen;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.Vida;
import teamHTBP.vida.client.input.VidaKeyRegistry;
import teamHTBP.vida.client.shader.Shaders;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(Dist.CLIENT)
public class BurningScreen extends Screen {
    protected BurningScreen() {
        super(new TextComponent(""));
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onClose() {
        super.onClose();
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @SubscribeEvent
    public static void onEvent(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getInstance();

        if (VidaKeyRegistry.TEST.isDown() && !(mc.screen instanceof BurningScreen)) {
            mc.setScreen(new BurningScreen());
        }
    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (burning)
                tick++;
            else tick = 0;
        }
    }

    public static final ResourceLocation TEX = new ResourceLocation(Vida.MOD_ID, "textures/gui/book.png");

    boolean burning;
    int tick;

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBg(pPoseStack, pPartialTick, pMouseX, pMouseY);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    void posTex(Matrix4f m4, BufferBuilder builder) {
        RenderSystem.setShaderTexture(0, TEX);
        builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

        float sX = (width - w()) / 2F;
        float sY = (height - h()) / 2F;
        float eX = sX + w();
        float eY = sY + h();

        float minU = 0;
        float minV = 0;
        float maxU = w() / 512F;
        float maxV = h() / 512F;

        builder.vertex(m4, sX, eY, 0).uv(minU, maxV).endVertex();
        builder.vertex(m4, eX, eY, 0).uv(maxU, maxV).endVertex();
        builder.vertex(m4, eX, sY, 0).uv(maxU, minV).endVertex();
        builder.vertex(m4, sX, sY, 0).uv(minU, minV).endVertex();
    }

    void pos(Matrix4f m4, BufferBuilder builder) {
        builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

        float sX = (width - w()) / 2F;
        float sY = (height - h()) / 2F;
        float eX = sX + w();
        float eY = sY + h();

        builder.vertex(m4, sX, eY, 0).endVertex();
        builder.vertex(m4, eX, eY, 0).endVertex();
        builder.vertex(m4, eX, sY, 0).endVertex();
        builder.vertex(m4, sX, sY, 0).endVertex();
    }

    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        Minecraft mc = Minecraft.getInstance();
        RenderTarget tar = mc.getMainRenderTarget();
        float aWidth = tar.width;
        float aHeight = tar.height;
        MouseHandler mouse = mc.mouseHandler;
        float amx = (float) mouse.xpos();
        float amy = (float) mouse.ypos();

        if (burning) {
            ShaderInstance shader = Shaders.glowEdge;

            RenderSystem.setShader(() -> shader);

            Shaders.setUniforms(shader, new Shaders.Point2f(aWidth, aHeight), new Shaders.Point2f(amx, amy), tick, pPartialTick);

            BufferBuilder builder = Tesselator.getInstance().getBuilder();

            Matrix4f m4 = pPoseStack.last().pose();

            pos(m4, builder);

            builder.end();
            BufferUploader.end(builder);
        }
        else {
            RenderSystem.setShaderTexture(0, TEX);
            int centerX = (width - w()) / 2;
            int centerY = (height - h()) / 2;
            GuiComponent.blit(pPoseStack, centerX, centerY, 0, 0, 0, w() , h(), 512, 512);
        }

        int color = 0xFFFFFFFF;

        drawString(pPoseStack, mc.font, "w:" + aWidth, 0, 0, color);
        drawString(pPoseStack, mc.font, "h:" + aHeight, 0, 10, color);
        drawString(pPoseStack, mc.font, "mx:" + amx, 0, 20, color);
        drawString(pPoseStack, mc.font, "my:" + amy, 0, 30, color);
    }

    int w() {
        return 371;
    }

    int h() {
        return 230;
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        burning = !burning;
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
