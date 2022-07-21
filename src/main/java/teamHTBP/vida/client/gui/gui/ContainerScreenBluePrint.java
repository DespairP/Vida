package teamHTBP.vida.client.gui.gui;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import teamHTBP.vida.Vida;
import teamHTBP.vida.client.gui.gui.base.ModBaseGui;
import teamHTBP.vida.menu.ContainerBluePrint;
import teamHTBP.vida.menu.slot.BluePrintScreenSlot;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ContainerScreenBluePrint extends ModBaseGui<ContainerBluePrint> {
    public final ResourceLocation GUI = new ResourceLocation(Vida.MOD_ID, "textures/gui/belt_gui.png");
    private final ContainerBluePrint container;
    private ArrayList<BluePrintScreenSlot> slots;

    public ContainerScreenBluePrint(ContainerBluePrint screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.container = screenContainer;
    }

    @Override
    protected void init() {
        super.init();
        initBluePrintSlot();
    }

    public void initBluePrintSlot() {
        slots = Lists.newArrayList(
                new BluePrintScreenSlot(BluePrintScreenSlot.BluePrintSlotPosition.TopLeft),
                new BluePrintScreenSlot(BluePrintScreenSlot.BluePrintSlotPosition.TopRight),
                new BluePrintScreenSlot(BluePrintScreenSlot.BluePrintSlotPosition.Left),
                new BluePrintScreenSlot(BluePrintScreenSlot.BluePrintSlotPosition.Middle),
                new BluePrintScreenSlot(BluePrintScreenSlot.BluePrintSlotPosition.Right),
                new BluePrintScreenSlot(BluePrintScreenSlot.BluePrintSlotPosition.BottomLeft),
                new BluePrintScreenSlot(BluePrintScreenSlot.BluePrintSlotPosition.BottomRight)
        );
        slots.get(0).setNeibourSlots(slots.get(1), slots.get(2), slots.get(3));
        slots.get(1).setNeibourSlots(slots.get(0), slots.get(4), slots.get(4));
        slots.get(2).setNeibourSlots(slots.get(0), slots.get(3), slots.get(5));
        slots.get(3).setNeibourSlots(slots.get(0), slots.get(1), slots.get(2), slots.get(4), slots.get(5), slots.get(6));
        slots.get(4).setNeibourSlots(slots.get(1), slots.get(3), slots.get(6));
        slots.get(5).setNeibourSlots(slots.get(2), slots.get(3), slots.get(6));
        slots.get(6).setNeibourSlots(slots.get(3), slots.get(4), slots.get(5));
    }

    public void refreshSlotAttrs() {
        AtomicInteger integer = new AtomicInteger(0);
        slots.forEach(slot -> {
            slot.setHasItem(container.slots.get(integer.getAndIncrement()).container.getItem(0));
        });
        slots.forEach(slot -> {
            slot.refreshMaxLightness();
        });
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int x, int y) {
        this.renderBackground(matrixStack);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, GUI);
        blit(matrixStack, this.leftPos - 12, this.topPos - 56, 0, 16, 0, 176, 211, 256, 256);
        if (slots == null) initBluePrintSlot();
        refreshSlotAttrs();
        slots.forEach(slot -> drawSlots(matrixStack, slot));
    }

    /**
     * 绘制格子
     */
    public void drawSlots(PoseStack matrixStack, BluePrintScreenSlot slot) {
        matrixStack.pushPose();
        float lightness = (float) slot.changeLightnessAndGet();
        RenderSystem.setShaderColor(lightness, lightness, lightness, 0.9f);
        switch (slot.position) {
            case TopLeft -> blitSlot(matrixStack, 83 - 34, 41 - 65);
            case TopRight -> blitSlot(matrixStack, 108 - 34, 41 - 65);
            case Left -> blitSlot(matrixStack, 71 - 34, 64 - 65);
            case Middle -> blitSlot(matrixStack, 96 - 34, 64 - 65);
            case Right -> blitSlot(matrixStack, 121 - 34, 64 - 65);
            case BottomLeft -> blitSlot(matrixStack, 83 - 34, 87 - 65);
            case BottomRight -> blitSlot(matrixStack, 108 - 34, 87 - 65);
            default -> {}
        }
        matrixStack.popPose();
    }

    public void blitSlot(PoseStack matrixStack, int screenX, int screenY) {
        blit(matrixStack, this.leftPos + screenX, this.topPos + screenY, 0, 215, 206, 28, 32, 256, 256);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int x, int y) {
    }
}
