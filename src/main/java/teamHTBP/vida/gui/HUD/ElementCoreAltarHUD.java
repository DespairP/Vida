package teamHTBP.vida.gui.HUD;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.Vida;

public class ElementCoreAltarHUD extends AbstractGui {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(Vida.MOD_ID, "textures/gui/altar_hud.png");
    private final ResourceLocation HUDHELPER = new ResourceLocation(Vida.MOD_ID, "textures/gui/altar_hud.png");
    private final TileEntityElementCoreAltar tileEntityElementCoreAltar;

    public ElementCoreAltarHUD(TileEntityElementCoreAltar tileEntityElementCoreAltar) {
        width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        minecraft = Minecraft.getInstance();
        this.tileEntityElementCoreAltar = tileEntityElementCoreAltar;
    }

    public void render(MatrixStack matrixStack) {
        if (tileEntityElementCoreAltar == null) return;
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(HUD);
        int screenWidth = this.width / 2 - 28;
        int screenHeight = this.height / 2 - 66;
        blit(matrixStack, screenWidth, screenHeight, 0, 0, 0, 56, 56, 80, 80);
        blit(matrixStack, screenWidth + 4, screenHeight + 80, 0, 0, 75, 48, 4, 80, 80);
        float length = (tileEntityElementCoreAltar.progress / 30000.0f) * 48.0f;
        blit(matrixStack, screenWidth + 4, screenHeight + 80, 0, 0, 71, (int) length, 4, 80, 80);
        //绘制四个方向的物品
        for (int i = 0; i < 4; i++) {
            GuiPosition position = GuiPosition.values()[i];
            ItemStack stack = tileEntityElementCoreAltar.getStack(i);
            if (stack != ItemStack.EMPTY && !stack.isEmpty()) {
                switch (position) {
                    case LEFT:
                        Minecraft.getInstance().getItemRenderer().renderGuiItem(stack, screenWidth + 1, screenHeight + 20);
                        break;
                    case TOP:
                        Minecraft.getInstance().getItemRenderer().renderGuiItem(stack, screenWidth + 20, screenHeight + 1);
                        break;
                    case RIGHT:
                        Minecraft.getInstance().getItemRenderer().renderGuiItem(stack, screenWidth + 39, screenHeight + 20);
                        break;
                    case BOTTOM:
                        Minecraft.getInstance().getItemRenderer().renderGuiItem(stack, screenWidth + 20, screenHeight + 39);
                        break;
                }

            }
        }
        //绘制核心
        ItemStack stack = tileEntityElementCoreAltar.coreItem;
        Minecraft.getInstance().getItemRenderer().renderGuiItem(stack, screenWidth + 20, screenHeight + 20);
    }


    enum GuiPosition {
        LEFT, RIGHT, TOP, BOTTOM
    }
}
