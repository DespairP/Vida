package teamHTBP.vida.helper.renderHelper;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;

public class RenderHelper {
    public static FontRenderer fontRenderer = Minecraft.getInstance().font;
    /**
     * 字体位置
     **/
    public final static ResourceLocation DUNGEON_FONT = new ResourceLocation("vida", "dungeonfont");
    //public final static ResourceLocation fusionpixel = new ResourceLocation("vida", "fusionpixel");


    /**
     * 普通材质渲染
     */
    public static void renderWithTexture(ResourceLocation texture, Runnable runnable) {
        RenderSystem.pushMatrix();
        Minecraft.getInstance().textureManager.bind(texture);
        runnable.run();
        RenderSystem.pushMatrix();
    }

    /**
     * dungeonFont字体渲染
     */
    public static void renderTextWithDungeonFont(MatrixStack matrixStack, String text, int x, int y) {
        RenderSystem.pushMatrix();
        StringTextComponent component = new StringTextComponent(text);
        component.setStyle(Style.EMPTY.withFont(DUNGEON_FONT));
        fontRenderer.draw(matrixStack, component, x, y, 0x000000);
        RenderSystem.popMatrix();
    }

    /**渲染字体*/
    public static void renderTextWithTranslationKeyCenter(MatrixStack matrixStack, String key, int maxLength, int x, int y, int color) {
        TranslationTextComponent name = new TranslationTextComponent(key);
        int textLength = fontRenderer.width(fontRenderer.plainSubstrByWidth(name.getString(), maxLength));
        int textHeight = fontRenderer.wordWrapHeight(name.getString(),maxLength);
        RenderSystem.pushMatrix();
        fontRenderer.drawWordWrap(name, (2 * x + maxLength) / 2 - textLength / 2 , y - textHeight, maxLength, color);
        RenderSystem.popMatrix();
    }

    /**由于GL20的scissor的xy与MC的xy不同,所以请使用这个方法裁剪*/
    public static void renderScissor(int x,int y,int width,int height){
        MainWindow window = Minecraft.getInstance().getWindow();
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


}
