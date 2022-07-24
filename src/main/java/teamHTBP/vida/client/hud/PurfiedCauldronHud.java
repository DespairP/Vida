package teamHTBP.vida.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import teamHTBP.vida.Vida;
import teamHTBP.vida.blockentity.TileEntityPurifiedCauldron;

public class PurfiedCauldronHud extends GuiComponent {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(Vida.MOD_ID, "textures/gui/hud.png");
    private final TileEntityPurifiedCauldron tileEntityPurifiedCauldron;

    public PurfiedCauldronHud(TileEntityPurifiedCauldron tileEntityPurifiedCauldron) {
        width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        minecraft = Minecraft.getInstance();
        this.tileEntityPurifiedCauldron = tileEntityPurifiedCauldron;
    }

    public void render(PoseStack matrixStack) {
        if (tileEntityPurifiedCauldron == null) return;
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, HUD);
        int screenWidth = this.width / 2 - 6;
        int screenHeight = this.height / 2 - 20;
        blit(matrixStack, screenWidth, screenHeight, 0, 25, 16, 14, 10, 64, 64);
        blit(matrixStack, screenWidth + 3, screenHeight - 10, 0, 28, 6, 8, 5, 64, 64);
        if (tileEntityPurifiedCauldron.isWater) {
            blit(matrixStack, screenWidth + 20, screenHeight, 0, 52, 2, 7, 12, 64, 64);
        } else {
            blit(matrixStack, screenWidth + 20, screenHeight, 0, 52, 34, 7, 12, 64, 64);
        }
        if (tileEntityPurifiedCauldron.isFire) {
            blit(matrixStack, screenWidth + 20, screenHeight - 15, 0, 51, 18, 12, 12, 64, 64);
        } else {
            blit(matrixStack, screenWidth + 20, screenHeight - 15, 0, 51, 50, 12, 12, 64, 64);
        }
        if (!tileEntityPurifiedCauldron.meltItem.isEmpty()) {
            blit(matrixStack, screenWidth + 3, screenHeight - 6, 0, 28, 11, 8, 5, 64, 64);
            Minecraft.getInstance().getItemRenderer().renderGuiItem(tileEntityPurifiedCauldron.meltItem, screenWidth - 1, screenHeight - 27);

        }
    }
}
