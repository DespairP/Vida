package teamHTBP.vida.client.gui.HUD;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.math.MathHelper;

public class MessageHUD extends AbstractGui {
    private final int guiLeftMid;
    private final Minecraft minecraft;
    public float alpha = 0;
    public int stayTime = 300;
    public int currentTime = 0;
    private int guiTopMid;
    private String message = "";

    public MessageHUD(String message) {
        int width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        this.minecraft = Minecraft.getInstance();
        this.guiLeftMid = width / 2;
        this.guiTopMid = height / 2;
        this.message = message;
    }


    public void render(MatrixStack matrixStack) {
        int width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        this.guiTopMid = height / 2;

        currentTime += 1;
        if (currentTime < 17) alpha += 0.06f;
        if (currentTime > stayTime - 19) alpha -= 0.06f;
        RenderSystem.pushMatrix();
        int a = (int) (alpha * 255);
        drawString(matrixStack, minecraft.fontRenderer, message, guiLeftMid - (minecraft.fontRenderer.getStringWidth(message) / 2), guiTopMid - 100, MathHelper.rgb(99, 255, 99) + (a << 24));
        RenderSystem.popMatrix();
    }
}
