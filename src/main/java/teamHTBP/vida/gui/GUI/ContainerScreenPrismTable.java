package teamHTBP.vida.gui.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import teamHTBP.vida.Vida;
import teamHTBP.vida.blockentity.TileEntityPrismTable;
import teamHTBP.vida.gui.gui.base.ModBaseGui;
import teamHTBP.vida.gui.menu.ContainerPrismTable;
import teamHTBP.vida.network.PacketChannel;
import teamHTBP.vida.network.PacketPrismTable;

public class ContainerScreenPrismTable extends ModBaseGui<ContainerPrismTable> {
    ResourceLocation Gui = new ResourceLocation(Vida.MOD_ID, "textures/gui/prismtable_gui.png");
    Button1 button1 = new Button1(this.leftPos + 85, this.topPos + 60);
    Button2 button2 = new Button2(this.leftPos + 75, this.topPos + 15);
    MirrorButton mirrorButton = new MirrorButton(0, 0);
    TileEntityPrismTable tileEntityPrismTable;
    private int fireAnimationX = 0;
    private int fireAnimationY = 166;

    public ContainerScreenPrismTable(ContainerPrismTable screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.tileEntityPrismTable = this.getMenu().tileEntityPrismTable;
    }

    @Override
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        if (p_mouseClicked_5_ == 0) {
            if (p_mouseClicked_1_ > this.leftPos + 9 && p_mouseClicked_1_ < this.leftPos + 9 + 16 && p_mouseClicked_3_ > this.topPos + 59 && p_mouseClicked_3_ < this.topPos + 59 + 16) {
                PacketChannel.INSTANCE.sendToServer(new PacketPrismTable(this.button1.x + 3 - this.leftPos,
                        this.button2.y + 3 - this.topPos, tileEntityPrismTable.getBlockPos().getX(),
                        tileEntityPrismTable.getBlockPos().getY(),
                        this.tileEntityPrismTable.getBlockPos().getZ()));
            }
        }
        return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(matrixStack);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, Gui);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        blit(matrixStack, i, j, 0, 0, 176, 166, 256, 256);
        drawFire(matrixStack, i, j);
        drawMirror(matrixStack, i, j);
    }

    protected void drawFire(PoseStack matrixStack, int x, int y) {
        if (tileEntityPrismTable.isGem) {
            matrixStack.pushPose();
            x += tileEntityPrismTable.array.get(0);
            y += tileEntityPrismTable.array.get(1);
            int offset1 = Math.abs(this.button1.x + 3 - x - 6);
            int offset2 = Math.abs(this.button2.y + 3 - y - 17);
            if (offset1 < 15 && offset2 < 15) {
                RenderSystem.setShaderColor(1, 1 - (15 - (float) Math.sqrt(offset1 * offset1 + offset2 * offset2)) / 20.0f, 1, 1);
            }
            blit(matrixStack, x, y, 0, fireAnimationX, fireAnimationY, 9, 23, 256, 256);
            this.fireAnimationX += 9;
            if (this.fireAnimationX > 171) {
                this.fireAnimationY += 24;
                if (this.fireAnimationY > 214)
                    this.fireAnimationY = 166;
                this.fireAnimationX = 0;
            }
            matrixStack.popPose();
        } else {
            this.fireAnimationY = 166;
            this.fireAnimationX = 0;
        }
    }

    protected void drawMirror(PoseStack matrixStack, int i, int j) {
        if (this.tileEntityPrismTable != null && this.tileEntityPrismTable.isMirror) {
            blit(matrixStack, this.button1.x - 4, this.button2.y - 10, 0, 176, 0, 16, 16, 256, 256);
        }
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float ticks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, ticks);
        renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        button1.x = this.leftPos + 85;
        button1.y = this.topPos + 60;
        button2.x = this.leftPos + 75;
        button2.y = this.topPos + 15;
        mirrorButton.x = this.leftPos + 9;
        mirrorButton.y = this.topPos + 59;
        this.addWidget(button1);
        this.addWidget(button2);
        this.addWidget(mirrorButton);
    }

    @Override
    public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        //System.out.println(p_mouseDragged_5_);
        if (p_mouseDragged_5_ == 0) {
            if (p_mouseDragged_1_ > this.leftPos + 86 && p_mouseDragged_1_ < this.leftPos + 160 && p_mouseDragged_3_ > this.topPos + 61 && p_mouseDragged_3_ < this.topPos + 70) {
                button1.x = (int) p_mouseDragged_1_;
            }
            if (p_mouseDragged_1_ > this.leftPos + 76 && p_mouseDragged_1_ < this.leftPos + 82 && p_mouseDragged_3_ > this.topPos + 16 && p_mouseDragged_3_ < this.topPos + 55) {
                button2.y = (int) p_mouseDragged_3_;
            }
        }
        return super.mouseDragged(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_, p_mouseDragged_8_);
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int x, int y) {

    }

    static class Button1 extends AbstractButton {
        ResourceLocation Gui = new ResourceLocation(Vida.MOD_ID, "textures/gui/prismtable_gui.png");

        public Button1(int xIn, int yIn) {
            super(xIn, yIn, 5, 10, new TextComponent(" "));
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
        public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            Minecraft minecraft = Minecraft.getInstance();
            RenderSystem.setShaderTexture(0, Gui);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
            blit(matrixStack, this.x, this.y, 0, 0, 246, 5, 10, 256, 256);
        }

        @Override
        public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

        }
    }

    static class Button2 extends AbstractButton {
        ResourceLocation Gui = new ResourceLocation(Vida.MOD_ID, "textures/gui/prismtable_gui.png");

        public Button2(int xIn, int yIn) {
            super(xIn, yIn, 10, 5, new TextComponent(" "));
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
        public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            Minecraft minecraft = Minecraft.getInstance();
            RenderSystem.setShaderTexture(0, Gui);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
            blit(matrixStack, this.x, this.y, 0, 6, 251, 10, 5, 256, 256);
        }


        @Override
        public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

        }
    }

    static class MirrorButton extends AbstractButton {
        ResourceLocation Gui = new ResourceLocation(Vida.MOD_ID, "textures/gui/prismtable_gui.png");

        public MirrorButton(int xIn, int yIn) {
            super(xIn, yIn, 16, 16, new TextComponent(" "));
        }

        @Override
        public void onPress() {


        }

        @Override
        public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            Minecraft minecraft = Minecraft.getInstance();
            RenderSystem.setShaderTexture(0, Gui);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
            if (!this.isHovered)
                blit(matrixStack, this.x, this.y, 0, 18, 240, 16, 16, 256, 256);
            else
                blit(matrixStack, this.x, this.y, 0, 35, 240, 16, 16, 256, 256);

        }

        @Override
        public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

        }
    }
}