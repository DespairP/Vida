package teamHTBP.vida.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import teamHTBP.vida.Vida;
import teamHTBP.vida.blockentity.crystal.IElementCrystal;
import teamHTBP.vida.client.gui.IVidaHUD;
import teamHTBP.vida.helper.elementHelper.EnumElements;

public class ElementCrystalHUD extends GuiComponent implements IVidaHUD{
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(Vida.MOD_ID, "textures/gui/crystal_hud.png");
    private final IElementCrystal tileEntityCrystal;
    //private final ResourceLocation HUDHELPER = new ResourceLocation(Vida.modId, "textures/gui/altar_hud.png");
    private int fragment_ticks = 0;
    private int progress_ticks = 0;

    public ElementCrystalHUD(IElementCrystal tileEntityCrystal, int fragmentTick, int progress_ticks) {
        width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        minecraft = Minecraft.getInstance();
        this.tileEntityCrystal = tileEntityCrystal;
        this.fragment_ticks = fragmentTick;
        this.progress_ticks = progress_ticks;
    }

    @Override
    public void render(PoseStack matrixStack) {
        if (tileEntityCrystal == null) return;
        int screenWidth = this.width / 2 - 6;
        int screenHeight = this.height / 2 - 40;
        if (fragment_ticks > 33) fragment_ticks = fragment_ticks % 33;
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, HUD);
        blit(matrixStack, screenWidth - 9, screenHeight - 1, 0, 257, 0, 15, 16, 528, 528);
        blit(matrixStack, screenWidth + 15 - 9, screenHeight - 1, 0, 272, 0, 15, 16, 528, 528);
        renderFragment(matrixStack);
        blit(matrixStack, screenWidth, screenHeight, 0, 18, 1, 12, 14, 528, 528);
        renderCrystal(matrixStack);
        // renderCrystal();

    }

    private void renderCrystal(PoseStack matrixStack) {
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

    private void renderFragment(PoseStack matrixStack) {
        int screenWidth = this.width / 2 - 6;
        int screenHeight = this.height / 2 - 40;
        blit(matrixStack, screenWidth + 15 - 9 - progress_ticks, screenHeight - 1, 0, 225 + 15 - progress_ticks, 0 + 16 * fragment_ticks, progress_ticks, 16, 528, 528);
        blit(matrixStack, screenWidth + 15 - 9, screenHeight - 1, 0, 240, 0 + 16 * fragment_ticks, progress_ticks, 16, 528, 528);
    }
}
