package teamHTBP.vida.gui.GUI;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import teamHTBP.vida.Vida;

public class ContainerScreenBottles extends ContainerScreen<ContainerBottles> {
    ResourceLocation Gui = new ResourceLocation(Vida.MOD_ID, "textures/gui/bottles_gui.png");

    public ContainerScreenBottles(ContainerBottles screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1, 1, 1, 1);
        this.minecraft.getTextureManager().bind(Gui);
        blit(matrixStack, this.leftPos, this.topPos, 0, 0, 176, 141, 256, 256);
        if (menu.isStack3Lock) {
            blit(matrixStack, this.leftPos + 104, this.topPos + 12, 0, 246, 0, 10, 13, 256, 256);
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float ticks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, ticks);
        renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {

    }
}
