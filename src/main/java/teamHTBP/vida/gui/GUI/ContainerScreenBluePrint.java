package teamHTBP.vida.gui.GUI;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import teamHTBP.vida.Vida;
import teamHTBP.vida.gui.GUI.ContainerBluePrint;
import teamHTBP.vida.gui.GUI.Slot.BluePrintScreenSlot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class ContainerScreenBluePrint extends ContainerScreen<ContainerBluePrint> {
    public final ResourceLocation GUI = new ResourceLocation(Vida.modId,"textures/gui/belt_gui.png");
    private ArrayList<BluePrintScreenSlot> slots;
    private ContainerBluePrint container;

    public ContainerScreenBluePrint(ContainerBluePrint screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.container = screenContainer;
    }

    @Override
    protected void init() {
        super.init();
        initBluePrintSlot();
    }

    public void initBluePrintSlot(){
        slots = Lists.newArrayList(
                new BluePrintScreenSlot(BluePrintScreenSlot.BluePrintSlotPosition.TopLeft),
                new BluePrintScreenSlot(BluePrintScreenSlot.BluePrintSlotPosition.TopRight),
                new BluePrintScreenSlot(BluePrintScreenSlot.BluePrintSlotPosition.Left),
                new BluePrintScreenSlot(BluePrintScreenSlot.BluePrintSlotPosition.Middle),
                new BluePrintScreenSlot(BluePrintScreenSlot.BluePrintSlotPosition.Right),
                new BluePrintScreenSlot(BluePrintScreenSlot.BluePrintSlotPosition.BottomLeft),
                new BluePrintScreenSlot(BluePrintScreenSlot.BluePrintSlotPosition.BottomRight)
        );
        slots.get(0).setNeibourSlots(slots.get(1),slots.get(2),slots.get(3));
        slots.get(1).setNeibourSlots(slots.get(0),slots.get(4),slots.get(4));
        slots.get(2).setNeibourSlots(slots.get(0),slots.get(3),slots.get(5));
        slots.get(3).setNeibourSlots(slots.get(0),slots.get(1),slots.get(2),slots.get(4),slots.get(5),slots.get(6));
        slots.get(4).setNeibourSlots(slots.get(1),slots.get(3),slots.get(6));
        slots.get(5).setNeibourSlots(slots.get(2),slots.get(3),slots.get(6));
        slots.get(6).setNeibourSlots(slots.get(3),slots.get(4),slots.get(5));
    }

    public void refreshSlotAttrs(){
        AtomicInteger integer = new AtomicInteger(0);
        slots.forEach(slot->{
            slot.setHasItem(container.inventorySlots.get(integer.getAndIncrement()).inventory.getStackInSlot(0));
        });
        slots.forEach(slot->{
            slot.refreshMaxLightness();
        });
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.renderBackground();
        RenderSystem.color4f(1,1,1,1);
        this.minecraft.getTextureManager().bindTexture(GUI);
        blit(this.guiLeft - 12, this.guiTop - 56, 0,16,0,176,211,256,256);
        if(slots == null) initBluePrintSlot();
        refreshSlotAttrs();
        slots.forEach(slot->drawSlots(slot));
    }

    /**绘制格子*/
    public void drawSlots(BluePrintScreenSlot slot){
        RenderSystem.pushMatrix();
        float lightness = (float)slot.changeLightnessAndGet();
        RenderSystem.color4f(lightness,lightness,lightness,0.9f);
        switch (slot.position){
            case TopLeft:
                blitSlot(83 - 34,41 - 65);break;
            case TopRight:
                blitSlot(108 - 34,41 - 65);break;
            case Left:
                blitSlot(71 - 34,64 - 65);break;
            case Middle:
                blitSlot(96 - 34,64 - 65);break;
            case Right:
                blitSlot(121 - 34,64 - 65);break;
            case BottomLeft:
                blitSlot(83 - 34,87 - 65);break;
            case BottomRight:
                blitSlot(108 - 34,87 - 65);break;
        }
        RenderSystem.popMatrix();
    }

    public void blitSlot(int screenX,int screenY){
        blit(this.guiLeft + screenX, this.guiTop + screenY, 0,215,206,28,32,256,256);
    }

    @Override
    public void render(int mouseX, int mouseY, float ticks) {
        this.renderBackground();
        super.render(mouseX, mouseY, ticks);
        renderHoveredToolTip(mouseX, mouseY);
    }
}
