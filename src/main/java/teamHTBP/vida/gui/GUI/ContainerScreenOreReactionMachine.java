package teamHTBP.vida.gui.GUI;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import teamHTBP.vida.Vida;

public class ContainerScreenOreReactionMachine extends ContainerScreen<ContainerOreReactionMachine> {
    ResourceLocation Gui = new ResourceLocation(Vida.modId, "textures/gui/orereaction_gui.png");

    public ContainerScreenOreReactionMachine(ContainerOreReactionMachine screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.renderBackground();
        RenderSystem.color4f(1,1,1,1);
        this.minecraft.getTextureManager().bindTexture(Gui);
        int i = (this.width - this.xSize) / 2 ;
        int j = (this.height - this.ySize) / 2 ;
        blit(i, j, 0, 0,176, 211, 256, 256);
    }

    @Override
    public void render(int mouseX, int mouseY, float ticks) {
        this.renderBackground();
        super.render(mouseX, mouseY, ticks);
        renderHoveredToolTip(mouseX, mouseY);
    }
}
