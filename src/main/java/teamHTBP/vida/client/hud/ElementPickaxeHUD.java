package teamHTBP.vida.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.Vida;
import teamHTBP.vida.client.gui.IVidaHUD;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.item.staff.ItemElementPickaxe;

public class ElementPickaxeHUD extends GuiComponent implements IVidaHUD {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(Vida.MOD_ID, "textures/gui/pickaxe_hud.png");
    private final ItemStack itemStack;
    float alpha;

    public ElementPickaxeHUD(ItemStack stack, int alpha) {
        width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        minecraft = Minecraft.getInstance();
        this.itemStack = stack;
        this.alpha = alpha / 100.0f;
    }

    @Override
    public void render(PoseStack matrixStack) {
        if (itemStack == null) return;
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
        matrixStack.pushPose();
        RenderSystem.enableBlend();
        int screenWidth = 3;
        int screenHeight = (int) (this.height * 4.5F / 6.0F);
        EnumElements pixel_element = ((ItemElementPickaxe) itemStack.getItem()).getItemElement();
        CompoundTag nbt = itemStack.getOrCreateTag();
        int level = nbt.getInt("level");
        int exp = nbt.getInt("pickaxeEXP");
        int progress = (int) (exp * 16.0f / (level * 500.0f));
        if (progress >= 16) progress = 16;
        matrixStack.scale(1.2f, 1.2f, 1.2f);
        RenderSystem.setShaderTexture(0, HUD);
        switch (pixel_element) {
            case GOLD -> blit(matrixStack, screenWidth, screenHeight, 0, 0, 0, 16, 16, 35, 119);
            case WOOD -> blit(matrixStack, screenWidth, screenHeight, 0, 48, 0, 16, 16, 35, 119);
            case AQUA -> blit(matrixStack, screenWidth, screenHeight, 0, 32, 0, 16, 16, 35, 119);
            case FIRE -> blit(matrixStack, screenWidth, screenHeight, 0, 16, 0, 16, 16, 35, 119);
            case EARTH -> blit(matrixStack, screenWidth, screenHeight, 0, 64, 0, 16, 16, 35, 119);
        }
        //System.out.println(progress);
        switch (pixel_element) {
            case GOLD -> blit(matrixStack, screenWidth, screenHeight + 16 - progress, 0, 0, 32 - progress, 16, progress, 35, 119);
            case WOOD -> blit(matrixStack, screenWidth, screenHeight + 16 - progress, 0, 48, 32 - progress, 16, progress, 35, 119);
            case AQUA -> blit(matrixStack, screenWidth, screenHeight + 16 - progress, 0, 32, 32 - progress, 16, progress, 35, 119);
            case FIRE -> blit(matrixStack, screenWidth, screenHeight + 16 - progress, 0, 16, 32 - progress, 16, progress, 35, 119);
            case EARTH -> blit(matrixStack, screenWidth, screenHeight + 16 - progress, 0, 64, 32 - progress, 16, progress, 35, 119);
        }
        matrixStack.pushPose();
        if (level >= 5) {
            int offsetU = ((level / 5) - 1) < 6 ? (level / 5) - 1 : 5;
            blit(matrixStack, screenWidth + 16, screenHeight, 0, 80 + 6 * offsetU, 0, 6, 6, 35, 119);
        }
        matrixStack.popPose();
        matrixStack.scale(0.95f, 0.95f, 0.95f);
        drawCenteredString(matrixStack, minecraft.font, level + "", screenWidth + 16, screenHeight + 19, 62900 + level * 100);
        matrixStack.popPose();
    }
}
