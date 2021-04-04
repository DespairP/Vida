package teamHTBP.vida.gui.HUD;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import teamHTBP.vida.Helper.ColorHelper;

public class MessageHUD extends AbstractGui {
    private final int guiLeftMid;
    private int guiTopMid;
    private final Minecraft minecraft;
    public float alpha = 0;
    private String message = "";
    public int stayTime = 300;
    public int currentTime = 0;

    public MessageHUD(String message) {
        int width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        this.minecraft = Minecraft.getInstance();
        this.guiLeftMid = width / 2;
        this.guiTopMid = height / 2;
        this.message = message;
    }


    public void render() {
        int width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        this.guiTopMid = height / 2;

        currentTime += 1;
        if(currentTime < 17) alpha += 0.06f;
        if(currentTime > stayTime - 19) alpha -= 0.06f;
        RenderSystem.pushMatrix();
        int a = (int)(alpha * 255);
        drawString(minecraft.fontRenderer,message,guiLeftMid - (minecraft.fontRenderer.getStringWidth(message) / 2), guiTopMid - 100, MathHelper.rgb(99,255,99) + (a << 24));
        RenderSystem.popMatrix();
    }
}
