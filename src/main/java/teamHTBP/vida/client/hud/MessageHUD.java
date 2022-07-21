package teamHTBP.vida.client.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.util.Mth;

public class MessageHUD extends GuiComponent {
    private final int guiLeftMid;
    private final Minecraft minecraft;
    public float alpha = 0;
    public int stayTime = 300;
    public int currentTime = 0;
    private int guiTopMid;
    private String message = "";

    public MessageHUD(String message) {
        int width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.minecraft = Minecraft.getInstance();
        this.guiLeftMid = width / 2;
        this.guiTopMid = height / 2;
        this.message = message;
    }


    public void render(PoseStack matrixStack) {
        int width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.guiTopMid = height / 2;

        currentTime += 1;
        if (currentTime < 17) alpha += 0.06f;
        if (currentTime > stayTime - 19) alpha -= 0.06f;
        matrixStack.pushPose();
        int a = (int) (alpha * 255);
        drawString(matrixStack, minecraft.font, message, guiLeftMid - (minecraft.font.width(message) / 2), guiTopMid - 100, Mth.color(99, 255, 99) + (a << 24));
        matrixStack.popPose();
    }
}
