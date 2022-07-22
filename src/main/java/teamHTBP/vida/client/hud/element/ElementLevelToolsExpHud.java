package teamHTBP.vida.client.hud.element;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.ForgeIngameGui;
import teamHTBP.vida.Vida;
import teamHTBP.vida.item.staff.IElementLevelTools;
import teamHTBP.vida.utils.color.RGBAColor;
import teamHTBP.vida.utils.math.IntRange;

public class ElementLevelToolsExpHud extends ElementToolsHud {
    private static final ResourceLocation VIDA_ICONS = new ResourceLocation(Vida.MOD_ID, "textures/gui/vida_icons.png");

    private IntRange toolExpProgress = new IntRange(0,183,0);

    @Override
    protected void renderInner(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        gui.setupOverlayRenderState(true, false);
        
        checkLastAndAnimTick();

        //渲染工具经验
        if(alpha > 0 && last.getItem() instanceof IElementLevelTools item) {
            RGBAColor color = item.getItemElement().getElementRGBAColor();

            if (color == null) {
                return;
            }

            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();
            float alpha = this.alpha / 100F;

            RenderSystem.setShaderTexture(0, VIDA_ICONS);
            RenderSystem.setShaderColor(r / 255.0F, g / 255.0F, b / 255.0F, alpha);

            double toolExp = item.getCurrentLevelXP(last);

            if (toolExp > 0) {
                double nextToolExp = item.getNextLevelRequiredXP(last);
                int toolExpWidth = (int) (toolExp / nextToolExp * 183.0F);
                toolExpProgress = toolExpProgress.get() == toolExpWidth ? toolExpProgress : new IntRange(toolExpProgress.get(), toolExpWidth, 0);

                int xPos = width / 2 - 91;
                int yPos = height - 32 + 6;

                GuiComponent.blit(poseStack, xPos, yPos, 0,
                        0, 8,
                        toolExpProgress.increase(2), 3,
                        256, 256);
            }

            int level = item.getCurrentLevel(last);

            if (level > 0) {
                String s = "" + level;
                int i1 = (width - mc.font.width(s)) / 2 + 20;
                int j1 = height - 31 - 4;
                mc.font.draw(poseStack, s, (float) (i1 + 1), (float) j1, RGBAColor.getColorCodeFromRGBA(0, 0, 0, (int) (alpha * 255.0F)));
                mc.font.draw(poseStack, s, (float) (i1 - 1), (float) j1, RGBAColor.getColorCodeFromRGBA(0, 0, 0, (int) (alpha * 255.0F)));
                mc.font.draw(poseStack, s, (float) i1, (float) (j1 + 1), RGBAColor.getColorCodeFromRGBA(0, 0, 0, (int) (alpha * 255.0F)));
                mc.font.draw(poseStack, s, (float) i1, (float) (j1 - 1), RGBAColor.getColorCodeFromRGBA(0, 0, 0, (int) (alpha * 255.0F)));
                mc.font.draw(poseStack, s, (float) i1, (float) j1, RGBAColor.getColorCodeFromRGBA(r, g, b, (int) (alpha * 255.0F)));
            }
        }
    }
}
