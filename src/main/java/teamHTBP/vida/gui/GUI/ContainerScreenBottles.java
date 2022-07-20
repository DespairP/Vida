package teamHTBP.vida.gui.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import teamHTBP.vida.Vida;
import teamHTBP.vida.gui.gui.base.ModBaseGui;
import teamHTBP.vida.gui.menu.ContainerBottles;

public class ContainerScreenBottles extends ModBaseGui<ContainerBottles> {
    ResourceLocation Gui = new ResourceLocation(Vida.MOD_ID, "textures/gui/bottles_gui.png");

    public ContainerScreenBottles(ContainerBottles screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(matrixStack);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, Gui);
        blit(matrixStack, this.leftPos, this.topPos, 0, 0, 176, 141, 256, 256);
        if (menu.isStack3Lock) {
            blit(matrixStack, this.leftPos + 104, this.topPos + 12, 0, 246, 0, 10, 13, 256, 256);
        }
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float ticks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, ticks);
        renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack p_230451_1_, int p_230451_2_, int p_230451_3_) {

    }
}
