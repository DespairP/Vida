package teamHTBP.vida.gui.HUD;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.TileEntity.TileEntityPurfiedCauldron;
import teamHTBP.vida.Vida;

public class PurfiedCauldronHUD extends AbstractGui {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(Vida.MOD_ID, "textures/gui/hud.png");
    private final TileEntityPurfiedCauldron tileEntityPurfiedCauldron;

    public PurfiedCauldronHUD(TileEntityPurfiedCauldron tileEntityPurfiedCauldron) {
        width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        minecraft = Minecraft.getInstance();
        this.tileEntityPurfiedCauldron = tileEntityPurfiedCauldron;
    }

    public void render(MatrixStack matrixStack) {
        if (tileEntityPurfiedCauldron == null) return;
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(HUD);
        int screenWidth = this.width / 2 - 6;
        int screenHeight = this.height / 2 - 20;
        blit(matrixStack, screenWidth, screenHeight, 0, 25, 16, 14, 10, 64, 64);
        blit(matrixStack, screenWidth + 3, screenHeight - 10, 0, 28, 6, 8, 5, 64, 64);
        if (tileEntityPurfiedCauldron.isWater) {
            blit(matrixStack, screenWidth + 20, screenHeight, 0, 52, 2, 7, 12, 64, 64);
        } else {
            blit(matrixStack, screenWidth + 20, screenHeight, 0, 52, 34, 7, 12, 64, 64);
        }
        if (tileEntityPurfiedCauldron.isFire) {
            blit(matrixStack, screenWidth + 20, screenHeight - 15, 0, 51, 18, 12, 12, 64, 64);
        } else {
            blit(matrixStack, screenWidth + 20, screenHeight - 15, 0, 51, 50, 12, 12, 64, 64);
        }
        if (!tileEntityPurfiedCauldron.meltItem.isEmpty()) {
            blit(matrixStack, screenWidth + 3, screenHeight - 6, 0, 28, 11, 8, 5, 64, 64);
            Minecraft.getInstance().getItemRenderer().renderGuiItem(tileEntityPurfiedCauldron.meltItem, screenWidth - 1, screenHeight - 27);

        }
    }
}
