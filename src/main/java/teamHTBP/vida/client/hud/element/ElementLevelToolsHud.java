package teamHTBP.vida.client.hud.element;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.ForgeIngameGui;
import teamHTBP.vida.Vida;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.item.staff.IElementLevelTools;
import teamHTBP.vida.utils.color.RGBAColor;

public class ElementLevelToolsHud extends ElementToolsHud {
    private static final ResourceLocation HUD = new ResourceLocation(Vida.MOD_ID, "textures/gui/pickaxe_hud.png");

    @Override
    public void renderInner(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        gui.setupOverlayRenderState(true, false);

        checkLastAndAnimTick();

        if (alpha > 0 && last.getItem() instanceof IElementLevelTools item) {
            float alpha = this.alpha / 100F;

            RenderSystem.setShaderTexture(0, HUD);
            RenderSystem.setShaderColor(1, 1, 1, alpha);

            // 根据物品元素进行渲染
            EnumElements element = item.getItemElement();

            int u = switch (element) {
                case VOID, GOLD, NONE -> 0;
                case WOOD -> 48;
                case AQUA -> 32;
                case FIRE -> 16;
                case EARTH -> 64;
            };

            // 定位渲染位置
            int renderX = 3;
            int renderY = (int) (height * 5.5F / 6.0F);

            // 底图
            GuiComponent.blit(poseStack, renderX, renderY, 0,
                    u, 0,
                    16, 16,
                    119, 35);

            int level = item.getCurrentLevel(last);
            double exp = item.getCurrentLevelXP(last);
            int progressHeight = (int) (exp * 16.0f / (level * 500.0f));

            // 进度
            GuiComponent.blit(poseStack, renderX, renderY + 16 - progressHeight, 0,
                    u, 32 - progressHeight,
                    16, progressHeight,
                    119, 35);

            // 显示工具等级
            GuiComponent.drawCenteredString(poseStack, mc.font, level + "", renderX + 16, renderY + 10,
                    RGBAColor.getColorCodeFromRGBA(126, 186, 137, (int) (255 * alpha)));
        }
    }
}
