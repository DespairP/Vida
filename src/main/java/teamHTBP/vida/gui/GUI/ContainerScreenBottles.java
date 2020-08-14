package teamHTBP.vida.gui.GUI;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import teamHTBP.vida.Vida;

public class ContainerScreenBottles extends ContainerScreen<ContainerBottles> {
    private int screenWidth,textureX = 256;
    private int screenHeight,textureY = 256;
    ResourceLocation Gui = new ResourceLocation(Vida.modId, "textures/gui/bottles_gui.png");

    public ContainerScreenBottles(ContainerBottles screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.renderBackground();
        RenderSystem.color4f(1,1,1,1);
        this.minecraft.getTextureManager().bindTexture(Gui);
        blit(this.guiLeft , this.guiTop, 0, 0,176, 141, 256, 256);
        if(container.isStack3Lock){
            blit(this.guiLeft + 104, this.guiTop + 12, 0, 246,0,10, 13, 256, 256);
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float ticks) {
        this.renderBackground();
        super.render(mouseX, mouseY, ticks);
        renderHoveredToolTip(mouseX, mouseY);
    }


}
