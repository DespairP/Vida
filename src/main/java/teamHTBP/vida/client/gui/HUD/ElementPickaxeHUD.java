package teamHTBP.vida.client.gui.HUD;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.Vida;
import teamHTBP.vida.client.gui.IVidaHUD;
import teamHTBP.vida.core.element.EnumElements;
import teamHTBP.vida.common.item.staff.ItemElementPickaxe;

public class ElementPickaxeHUD extends AbstractGui implements IVidaHUD {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(Vida.MOD_ID, "textures/gui/pickaxe_hud.png");
    private final ItemStack itemStack;
    float alpha;

    public ElementPickaxeHUD(ItemStack stack, int alpha) {
        width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        minecraft = Minecraft.getInstance();
        this.itemStack = stack;
        this.alpha = alpha / 100.0f;
    }

    public void render(MatrixStack matrixStack) {
        if (itemStack == null) return;
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, alpha);
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        int screenWidth = 3;
        int screenHeight = (int) (this.height * 4.5F / 6.0F);
        EnumElements pixel_element = ((ItemElementPickaxe) itemStack.getItem()).getItemElement();
        CompoundNBT nbt = itemStack.getOrCreateTag();
        int level = nbt.getInt("level");
        int exp = nbt.getInt("pickaxeEXP");
        int progress = (int) (exp * 16.0f / (level * 500.0f));
        if (progress >= 16) progress = 16;
        RenderSystem.scaled(1.2f, 1.2f, 1.2f);
        this.minecraft.getTextureManager().bindTexture(HUD);
        switch (pixel_element) {
            case GOLD:
                blit(matrixStack, screenWidth, screenHeight, 0, 0, 0, 16, 16, 35, 119);
                break;
            case WOOD:
                blit(matrixStack, screenWidth, screenHeight, 0, 48, 0, 16, 16, 35, 119);
                break;
            case AQUA:
                blit(matrixStack, screenWidth, screenHeight, 0, 32, 0, 16, 16, 35, 119);
                break;
            case FIRE:
                blit(matrixStack, screenWidth, screenHeight, 0, 16, 0, 16, 16, 35, 119);
                break;
            case EARTH:
                blit(matrixStack, screenWidth, screenHeight, 0, 64, 0, 16, 16, 35, 119);
                break;
        }
        //System.out.println(progress);
        switch (pixel_element) {
            case GOLD:
                blit(matrixStack, screenWidth, screenHeight + 16 - progress, 0, 0, 32 - progress, 16, progress, 35, 119);
                break;
            case WOOD:
                blit(matrixStack, screenWidth, screenHeight + 16 - progress, 0, 48, 32 - progress, 16, progress, 35, 119);
                break;
            case AQUA:
                blit(matrixStack, screenWidth, screenHeight + 16 - progress, 0, 32, 32 - progress, 16, progress, 35, 119);
                break;
            case FIRE:
                blit(matrixStack, screenWidth, screenHeight + 16 - progress, 0, 16, 32 - progress, 16, progress, 35, 119);
                break;
            case EARTH:
                blit(matrixStack, screenWidth, screenHeight + 16 - progress, 0, 64, 32 - progress, 16, progress, 35, 119);
                break;
        }
        RenderSystem.pushMatrix();
        if (level >= 5) {
            int offsetU = ((level / 5) - 1) < 6 ? (level / 5) - 1 : 5;
            blit(matrixStack, screenWidth + 16, screenHeight, 0, 80 + 6 * offsetU, 0, 6, 6, 35, 119);
        }
        RenderSystem.popMatrix();
        RenderSystem.scaled(0.95f, 0.95f, 0.95f);
        drawCenteredString(matrixStack, minecraft.fontRenderer, level + "", screenWidth + 16, screenHeight + 19, 62900 + level * 100);
        RenderSystem.popMatrix();
    }
}
