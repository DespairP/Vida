package teamHTBP.vida.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.Vida;
import teamHTBP.vida.blockentity.TileEntityCollector;

public class CollectorHud extends GuiComponent {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(Vida.MOD_ID, "textures/gui/collector_hud.png");
    private final TileEntityCollector tileEntityCollector;

    public CollectorHud(TileEntityCollector tileEntityCollector) {
        width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        minecraft = Minecraft.getInstance();
        this.tileEntityCollector = tileEntityCollector;
    }

    public void render(PoseStack matrixStack) {
        if (tileEntityCollector == null) return;
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, HUD);
        int screenWidth = this.width / 2 - 10;
        int screenHeight = this.height / 2 - 66;
        blit(matrixStack, screenWidth + 2, screenHeight + 30, 0, 6, 29, 20, 3, 48, 48);
        blit(matrixStack, screenWidth + 4, screenHeight + 40, 0, 8, 33, 15, 13, 48, 48);
        blit(matrixStack, screenWidth, screenHeight, 0, 4, 4, 24, 24, 48, 48);
        int progress = 10 * tileEntityCollector.getCollection() / tileEntityCollector.MAX_COLLECTION;
        int progressPercent = (int) ((tileEntityCollector.getCollection() * 1.0f / tileEntityCollector.MAX_COLLECTION) * 100);
        if (tileEntityCollector.isCollect) {
            blit(matrixStack, screenWidth + 2, screenHeight + 30, 0, 26, 29, progress, 3, 48, 48);
            blit(matrixStack, screenWidth + 2 + 20 - progress, screenHeight + 30, 0, 26 + 20 - progress, 29, progress, 3, 48, 48);
        }
        if (tileEntityCollector.isCollect && tileEntityCollector.getCollection() == 0) {
            blit(matrixStack, screenWidth + 20, screenHeight + 20, 0, 31, 5, 13, 13, 48, 48);
        } else if (tileEntityCollector.isCollect) {
            blit(matrixStack, screenWidth + 20, screenHeight + 20, 0, 31, 19, 13, 10, 48, 48);
        }
        drawString(matrixStack, minecraft.font, progressPercent + "%", screenWidth + 7, screenHeight - 10, 60000);

        //System.out.println(tileEntityCollector.getCollection());
        if (tileEntityCollector.coreItem != ItemStack.EMPTY && !tileEntityCollector.coreItem.isEmpty()) {
            ItemStack stack = tileEntityCollector.coreItem;
            Minecraft.getInstance().getItemRenderer().renderGuiItem(stack, screenWidth + 4, screenHeight + 4);
        }

    }
}
