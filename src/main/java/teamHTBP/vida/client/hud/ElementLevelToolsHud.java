package teamHTBP.vida.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.ForgeIngameGui;
import teamHTBP.vida.Vida;
import teamHTBP.vida.client.animation.TimeInterpolator;
import teamHTBP.vida.client.animation.animator.Animator;
import teamHTBP.vida.client.animation.valueholder.FloatPropertyValueHolder;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.item.staff.IElementLevelTools;
import teamHTBP.vida.utils.color.RGBAColor;

public class ElementLevelToolsHud extends Hud {
    private static final ResourceLocation HUD = new ResourceLocation(Vida.MOD_ID, "textures/gui/pickaxe_hud.png");

    private ItemStack last = ItemStack.EMPTY;

    Animator in;
    Animator out;

    @Override
    protected void init() {
        in = Animator.of(this, new FloatPropertyValueHolder<>(ALPHA, 0f, 100f))
                .interpolator(TimeInterpolator.ACCELERATE).durationTick(20);

        out = Animator.of(this, new FloatPropertyValueHolder<>(ALPHA, 100f, 0f))
                .interpolator(TimeInterpolator.DECELERATE).durationTick(20);
    }

    void animTick(boolean isIncrease) {
        if (isIncrease) {
            out.cancel();
            in.start();
        } else {
            in.cancel();
            out.start();
        }
    }

    @Override
    public void renderInner(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        gui.setupOverlayRenderState(true, false);

        RenderSystem.setShaderTexture(0, HUD);

        ItemStack itemStack = player().getMainHandItem();

        boolean isIncrease = itemStack.getItem() instanceof IElementLevelTools;

        if (isIncrease && last != itemStack) {
            last = itemStack;
            in.setTickPercent(.5F);
        }

        animTick(isIncrease);

        float alpha = this.alpha / 100F;

        if (alpha == 0) {
            last = ItemStack.EMPTY;
        }

        RenderSystem.setShaderColor(1, 1, 1, alpha);

        if (!last.isEmpty() && last.getItem() instanceof IElementLevelTools item) {
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
