package teamHTBP.vida.helper.render;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class RenderHelper {
    public static Font fontRenderer = Minecraft.getInstance().font;
    /**
     * 字体位置
     **/
    public final static ResourceLocation DUNGEON_FONT = new ResourceLocation("vida", "dungeonfont");
    //public final static ResourceLocation fusionpixel = new ResourceLocation("vida", "fusionpixel");


    /**
     * 普通材质渲染
     */
    public static void renderWithTexture(PoseStack matrixStack, ResourceLocation texture, Runnable runnable) {
        matrixStack.pushPose();
        RenderSystem.setShaderTexture(0, texture);
        runnable.run();
        matrixStack.pushPose();
    }

    /**
     * dungeonFont字体渲染
     */
    public static void renderTextWithDungeonFont(PoseStack matrixStack, String text, int x, int y) {
        matrixStack.pushPose();
        TextComponent component = new TextComponent(text);
        component.setStyle(Style.EMPTY.withFont(DUNGEON_FONT));
        fontRenderer.draw(matrixStack, component, x, y, 0x000000);
        matrixStack.popPose();
    }

    /**渲染字体*/
    public static void renderTextWithTranslationKeyCenter(PoseStack matrixStack, String key, int maxLength, int x, int y, int color) {
        TranslatableComponent name = new TranslatableComponent(key);
        int textLength = fontRenderer.width(fontRenderer.plainSubstrByWidth(name.getString(), maxLength));
        int textHeight = fontRenderer.wordWrapHeight(name.getString(),maxLength);
        matrixStack.pushPose();
        fontRenderer.drawWordWrap(name, (2 * x + maxLength) / 2 - textLength / 2 , y - textHeight, maxLength, color);
        matrixStack.popPose();
    }

    /**由于GL20的scissor的xy与MC的xy不同,所以请使用这个方法裁剪*/
    public static void renderScissor(int x,int y,int width,int height){
        Window window = Minecraft.getInstance().getWindow();
        int scaledHeight = window.getGuiScaledHeight();
        int scaledWidth = window.getGuiScaledWidth();
        double scaledFactor = window.getGuiScale();
        RenderSystem.enableScissor(
                (int)(x * scaledFactor),
                (int)((scaledHeight - y - height) * scaledFactor),
                (int)(width * scaledFactor),
                (int)(height * scaledFactor)
        );
    }

    public static void blitVertical(PoseStack poseStack, int x1, int y1, int height, int weight, int z, TextureAtlasSprite sprite, double percent) {
        innerBlit(poseStack,
                x1, x1 + weight,
                (int) (y1 + height * (1 - percent)), y1 + height,
                z,
                sprite.getU0(), sprite.getU1(),
                (float) (sprite.getV0() + (sprite.getV1() - sprite.getV0()) * (1 - percent)), sprite.getV1());
    }

    private static void innerBlit(PoseStack poseStack, float x1, float x2, float y1, float y2, float z,
                                  float minU, float maxU, float minV, float maxV) {
        Matrix4f m4 = poseStack.last().pose();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(m4, x1, y2, z).uv(minU, maxV).endVertex();
        bufferbuilder.vertex(m4, x2, y2, z).uv(maxU, maxV).endVertex();
        bufferbuilder.vertex(m4, x2, y1, z).uv(maxU, minV).endVertex();
        bufferbuilder.vertex(m4, x1, y1, z).uv(minU, minV).endVertex();
        bufferbuilder.end();
        BufferUploader.end(bufferbuilder);
    }
}
