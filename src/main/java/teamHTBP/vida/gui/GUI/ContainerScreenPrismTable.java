package teamHTBP.vida.gui.GUI;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import teamHTBP.vida.TileEntity.TileEntityPrismTable;
import teamHTBP.vida.Vida;
import teamHTBP.vida.network.PacketChannel;
import teamHTBP.vida.network.PacketPrismTable;

public class ContainerScreenPrismTable extends ContainerScreen<ContainerPrismTable> {
    private final int textureX = 256;
    private final int textureY = 256;
    ResourceLocation Gui = new ResourceLocation(Vida.modId, "textures/gui/prismtable_gui.png");
    button1 button1 = new button1(this.guiLeft + 85, this.guiTop + 60);
    button2 button2 = new button2(this.guiLeft + 75, this.guiTop + 15);
    mirrorButton mirrorButton = new mirrorButton(0, 0);
    TileEntityPrismTable tileEntityPrismTable;
    private int screenWidth;
    private int screenHeight;
    private int fireAnimationX = 0;
    private int fireAnimationY = 166;

    public ContainerScreenPrismTable(ContainerPrismTable screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 176;
        this.ySize = 166;
        this.tileEntityPrismTable = this.getContainer().tileEntityPrismTable;
    }

    @Override
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        if (p_mouseClicked_5_ == 0) {
            if (p_mouseClicked_1_ > this.guiLeft + 9 && p_mouseClicked_1_ < this.guiLeft + 9 + 16 && p_mouseClicked_3_ > this.guiTop + 59 && p_mouseClicked_3_ < this.guiTop + 59 + 16) {
                PacketChannel.INSTANCE.sendToServer(new PacketPrismTable(this.button1.x + 3 - this.guiLeft,
                        this.button2.y + 3 - this.guiTop, tileEntityPrismTable.getPos().getX(),
                        tileEntityPrismTable.getPos().getY(),
                        this.tileEntityPrismTable.getPos().getZ()));
            }
        }
        return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1, 1, 1, 1);
        this.minecraft.getTextureManager().bindTexture(Gui);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        blit(matrixStack, i, j, 0, 0, 176, 166, 256, 256);
        drawFire(matrixStack, i, j);
        drawMirror(matrixStack, i, j);
    }

    protected void drawFire(MatrixStack matrixStack, int x, int y) {
        if (tileEntityPrismTable.isGem) {
            RenderSystem.pushMatrix();
            x += tileEntityPrismTable.array.get(0);
            y += tileEntityPrismTable.array.get(1);
            int offset1 = Math.abs(this.button1.x + 3 - x - 6);
            int offset2 = Math.abs(this.button2.y + 3 - y - 17);
            if (offset1 < 15 && offset2 < 15) {
                RenderSystem.color4f(1, 1 - (15 - (float) Math.sqrt(offset1 * offset1 + offset2 * offset2)) / 20.0f, 1, 1);
            }
            blit(matrixStack, x, y, 0, fireAnimationX, fireAnimationY, 9, 23, 256, 256);
            this.fireAnimationX += 9;
            if (this.fireAnimationX > 171) {
                this.fireAnimationY += 24;
                if (this.fireAnimationY > 214)
                    this.fireAnimationY = 166;
                this.fireAnimationX = 0;
            }
            RenderSystem.popMatrix();
        } else {
            this.fireAnimationY = 166;
            this.fireAnimationX = 0;
        }
    }

    protected void drawMirror(MatrixStack matrixStack, int i, int j) {
        if (this.tileEntityPrismTable != null && this.tileEntityPrismTable.isMirror) {
            blit(matrixStack, this.button1.x - 4, this.button2.y - 10, 0, 176, 0, 16, 16, 256, 256);
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float ticks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, ticks);
        renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        button1.x = this.guiLeft + 85;
        button1.y = this.guiTop + 60;
        button2.x = this.guiLeft + 75;
        button2.y = this.guiTop + 15;
        mirrorButton.x = this.guiLeft + 9;
        mirrorButton.y = this.guiTop + 59;
        this.addButton(button1);
        this.addButton(button2);
        this.addButton(mirrorButton);
    }

    @Override
    public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        //System.out.println(p_mouseDragged_5_);
        if (p_mouseDragged_5_ == 0) {
            if (p_mouseDragged_1_ > this.guiLeft + 86 && p_mouseDragged_1_ < this.guiLeft + 160 && p_mouseDragged_3_ > this.guiTop + 61 && p_mouseDragged_3_ < this.guiTop + 70) {
                button1.x = (int) p_mouseDragged_1_;
            }
            if (p_mouseDragged_1_ > this.guiLeft + 76 && p_mouseDragged_1_ < this.guiLeft + 82 && p_mouseDragged_3_ > this.guiTop + 16 && p_mouseDragged_3_ < this.guiTop + 55) {
                button2.y = (int) p_mouseDragged_3_;
            }
        }
        return super.mouseDragged(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_, p_mouseDragged_8_);
    }
}

class button1 extends AbstractButton {
    ResourceLocation Gui = new ResourceLocation(Vida.modId, "textures/gui/prismtable_gui.png");

    public button1(int xIn, int yIn) {
        super(xIn, yIn, 5, 10, new StringTextComponent(" "));
    }

    @Override
    public void onPress() {

    }

    @Override
    public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        this.onDrag(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_6_, p_mouseDragged_8_);
        return true;
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double dragX, double dragY) {
        super.onDrag(mouseX, mouseY, dragX, dragY);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(Gui);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        blit(matrixStack, this.x, this.y, 0, 0, 246, 5, 10, 256, 256);
    }


}

class button2 extends AbstractButton {
    ResourceLocation Gui = new ResourceLocation(Vida.modId, "textures/gui/prismtable_gui.png");

    public button2(int xIn, int yIn) {
        super(xIn, yIn, 10, 5, new StringTextComponent(" "));
    }

    @Override
    public void onPress() {


    }

    @Override
    public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        this.onDrag(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_6_, p_mouseDragged_8_);
        return true;
    }

    @Override
    protected void onDrag(double p_onDrag_1_, double p_onDrag_3_, double p_onDrag_5_, double p_onDrag_7_) {
        System.out.println("sss");
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(Gui);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        blit(matrixStack, this.x, this.y, 0, 6, 251, 10, 5, 256, 256);
    }


}

class mirrorButton extends AbstractButton {
    ResourceLocation Gui = new ResourceLocation(Vida.modId, "textures/gui/prismtable_gui.png");

    public mirrorButton(int xIn, int yIn) {
        super(xIn, yIn, 16, 16, new StringTextComponent(" "));
    }

    @Override
    public void onPress() {


    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(Gui);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        if (!this.isHovered)
            blit(matrixStack, this.x, this.y, 0, 18, 240, 16, 16, 256, 256);
        else
            blit(matrixStack, this.x, this.y, 0, 35, 240, 16, 16, 256, 256);

    }


}