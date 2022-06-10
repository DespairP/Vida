package teamHTBP.vida.gui.HUD;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.TileEntity.IElementCrystal;
import teamHTBP.vida.Vida;
import teamHTBP.vida.gui.IVidaHUD;
import teamHTBP.vida.helper.elementHelper.EnumElements;

public class ElementCrystalHUD extends AbstractGui implements IVidaHUD{
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(Vida.MOD_ID, "textures/gui/crystal_hud.png");
    private final IElementCrystal tileEntityCrystal;
    //private final ResourceLocation HUDHELPER = new ResourceLocation(Vida.modId, "textures/gui/altar_hud.png");
    private int fragment_ticks = 0;
    private int progress_ticks = 0;

    public ElementCrystalHUD(IElementCrystal tileEntityCrystal, int fragmentTick, int progress_ticks) {
        width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        minecraft = Minecraft.getInstance();
        this.tileEntityCrystal = tileEntityCrystal;
        this.fragment_ticks = fragmentTick;
        this.progress_ticks = progress_ticks;
    }

    public void render(MatrixStack matrixStack) {
        if (tileEntityCrystal == null) return;
        int screenWidth = this.width / 2 - 6;
        int screenHeight = this.height / 2 - 40;
        if (fragment_ticks > 33) fragment_ticks = fragment_ticks % 33;
        RenderSystem.color4f(1, 1, 1, 1);
        this.minecraft.getTextureManager().bindTexture(HUD);
        blit(matrixStack, screenWidth - 9, screenHeight - 1, 0, 257, 0, 15, 16, 528, 528);
        blit(matrixStack, screenWidth + 15 - 9, screenHeight - 1, 0, 272, 0, 15, 16, 528, 528);
        renderFragment(matrixStack);
        blit(matrixStack, screenWidth, screenHeight, 0, 18, 1, 12, 14, 528, 528);
        renderCrystal(matrixStack);
        // renderCrystal();

    }

    private void renderCrystal(MatrixStack matrixStack) {
        EnumElements element = tileEntityCrystal.getElement();
        int screenWidth = this.width / 2 - 6;
        int screenHeight = this.height / 2 - 40;
        int progress = (int) (tileEntityCrystal.getEnergyStored() * 14.0f / tileEntityCrystal.getMaxEnergy());
        //System.out.println(tileEntityCrystal.getEnergyStored());
        switch (element) {
            case GOLD:
                blit(matrixStack, screenWidth, screenHeight + 14 - progress, 18, 65 + 14 - progress, 12, progress, 528, 528);
        }
    }

    private void renderFragment(MatrixStack matrixStack) {
        int screenWidth = this.width / 2 - 6;
        int screenHeight = this.height / 2 - 40;
        blit(matrixStack, screenWidth + 15 - 9 - progress_ticks, screenHeight - 1, 0, 225 + 15 - progress_ticks, 0 + 16 * fragment_ticks, progress_ticks, 16, 528, 528);
        blit(matrixStack, screenWidth + 15 - 9, screenHeight - 1, 0, 240, 0 + 16 * fragment_ticks, progress_ticks, 16, 528, 528);
    }
}
