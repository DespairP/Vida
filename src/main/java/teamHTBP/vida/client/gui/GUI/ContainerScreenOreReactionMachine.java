package teamHTBP.vida.client.gui.GUI;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import teamHTBP.vida.common.TileEntity.TileEntityOreReationMachine;
import teamHTBP.vida.Vida;

public class ContainerScreenOreReactionMachine extends ContainerScreen<ContainerOreReactionMachine> {
    ResourceLocation Gui = new ResourceLocation(Vida.MOD_ID, "textures/gui/orereaction_gui.png");
    TileEntityOreReationMachine tileEntityOreReationMachine;

    public ContainerScreenOreReactionMachine(ContainerOreReactionMachine screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.tileEntityOreReationMachine = this.getContainer().machine;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1, 1, 1, 1);
        this.minecraft.getTextureManager().bindTexture(Gui);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2 - 30;
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
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float ticks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, ticks);
        renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }
}
