package teamHTBP.vida.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.ForgeIngameGui;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.item.staff.IElementLevelTools;
import teamHTBP.vida.utils.color.RGBAColor;
import teamHTBP.vida.utils.math.FloatRange;
import teamHTBP.vida.utils.math.IntRange;

public class ElementLevelToolsHud {
    private static final IntRange progress = new IntRange(0,17,0);
    private static final FloatRange ALPHA = new FloatRange(0,100,0);

    private static ItemStack last = ItemStack.EMPTY;

    public static void renderElementLevelTools(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        final Minecraft mc = Minecraft.getInstance();

        ItemStack itemStack = mc.player.getMainHandItem();

        boolean isIncrease = itemStack.getItem() instanceof IElementLevelTools;

        if (isIncrease && last != itemStack) {
            last = itemStack;
        }

        float alphaDiff = (1 - partialTick) * 4;

        float alphaNum = isIncrease ? ALPHA.increase(alphaDiff) : ALPHA.decrease(alphaDiff);

        float alpha = alphaNum / 100F;
        RenderSystem.setShaderColor(1, 1, 1, alpha);

        if (alpha == 0) {
            last = ItemStack.EMPTY;
        }

        if (!last.isEmpty() && last.getItem() instanceof IElementLevelTools item) {
            int level = item.getCurrentLevel(last);
            double exp = item.getCurrentLevelXP(last);
            int progressHeight = progress.set((int) (exp * 16.0f / (level * 500.0f)));
            // 定位渲染位置
            int renderX = 3;
            int renderY = (int) (height * 5.5F / 6.0F);

            // 根据物品元素进行渲染
            EnumElements element = item.getItemElement();

            int u = switch (element) {
                case VOID, GOLD, NONE -> 0;
                case WOOD ->  48;
                case AQUA ->  32;
                case FIRE ->  16;
                case EARTH -> 64;
            };

            //renderTexture(poseStack, renderX, renderY, 16, 16, u, 0);

            GuiComponent.blit(poseStack, renderX, renderY, 0,
                    u, 0,
                    16, 16,
                    119, 35);

            //renderTexture(poseStack, renderX, renderY + 16 - progressHeight, 16, progressHeight, u, 32 - progressHeight);

            GuiComponent.blit(poseStack, renderX, renderY + 16 - progressHeight, 0,
                    u, 32 - progressHeight,
                    16, progressHeight,
                    119, 35);

            // 显示工具等级
            GuiComponent.drawCenteredString(poseStack, mc.font, level + "", renderX + 16, renderY + 10,
                    RGBAColor.getColorCodeFromRGBA(126, 186, 137, (int) (255 * alphaNum / 100)));
        }
    }
}
