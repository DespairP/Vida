package teamHTBP.vida.gui.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import teamHTBP.vida.Vida;
import teamHTBP.vida.blockentity.TileEntityOreReationMachine;
import teamHTBP.vida.gui.gui.base.ModBaseGui;
import teamHTBP.vida.gui.menu.ContainerOreReactionMachine;

public class ContainerScreenOreReactionMachine extends ModBaseGui<ContainerOreReactionMachine> {
    ResourceLocation Gui = new ResourceLocation(Vida.MOD_ID, "textures/gui/orereaction_gui.png");
    TileEntityOreReationMachine tileEntityOreReationMachine;

    public ContainerScreenOreReactionMachine(ContainerOreReactionMachine screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.tileEntityOreReationMachine = this.getMenu().machine;
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(matrixStack);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, Gui);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2 - 30;
        blit(matrixStack, i, j, 0, 0, 176, 211, 256, 256);
        //渲染箭头
        if (tileEntityOreReationMachine.isCooking()) {
            int progress = (int) (tileEntityOreReationMachine.array.get(1) * 18.0f / (tileEntityOreReationMachine.MAX_COOKTIME * 1.0f));
            blit(matrixStack, i + 79, j + 64, 0, 232, 0, 8, progress, 256, 256);
        }
        //System.out.println(tileEntityOreReationMachine.isBurning());
        if (tileEntityOreReationMachine.isBurning()) {
            int progress = (int) (tileEntityOreReationMachine.array.get(0) * 72.0f / (tileEntityOreReationMachine.MAX_BURNTIME * 1.0f));
            blit(matrixStack, i + 52, j + 37 + 72 - progress, 0, 248, 0 + 72 - progress, 8, progress, 256, 256);
        }
        if (tileEntityOreReationMachine.getGoldEnergy() > 0) {
            int progress = (int) (tileEntityOreReationMachine.getGoldEnergy() * 72.0f / (tileEntityOreReationMachine.MAX_GOLDENERGY * 1.0f));
            blit(matrixStack, i + 32, j + 37 + 72 - progress, 0, 240, 0 + 72 - progress, 8, progress, 256, 256);
        }
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float ticks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, ticks);
        renderTooltip(matrixStack, mouseX, mouseY);
    }
}
