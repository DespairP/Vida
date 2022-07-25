package teamHTBP.vida.client.hud.element;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.ForgeIngameGui;
import teamHTBP.vida.Vida;
import teamHTBP.vida.client.atlas.VidaAtlasManager;
import teamHTBP.vida.element.IElement;
import teamHTBP.vida.helper.render.RenderHelper;
import teamHTBP.vida.item.staff.IElementLevelTools;
import teamHTBP.vida.utils.color.RGBAColor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ElementLevelToolsHud extends ElementToolsHud {
    private static final ResourceLocation HUD = new ResourceLocation(Vida.MOD_ID, "textures/gui/pickaxe_hud.png");

    static final Map<IElement, ResourceLocation> emptyCache = new HashMap<>();
    static final Map<IElement, ResourceLocation> cache = new HashMap<>();

    static ResourceLocation transEmpty(IElement element) {
        ResourceLocation elementName = element.getElementName();
        return new ResourceLocation(elementName.getNamespace(), "vida_icons/element/" + elementName.getPath() + "_empty");
    }

    static ResourceLocation trans(IElement element) {
        ResourceLocation elementName = element.getElementName();
        return new ResourceLocation(elementName.getNamespace(), "vida_icons/element/" + elementName.getPath());
    }

    @Override
    public void renderInner(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        gui.setupOverlayRenderState(true, false);

        checkLastAndAnimTick();

        if (alpha > 0 && lastStack.getItem() instanceof IElementLevelTools item) {
            float alpha = this.alpha / 100F;

            IElement element = item.getElement();
            Function<ResourceLocation, TextureAtlasSprite> atlas = Minecraft.getInstance()
                    .getTextureAtlas(VidaAtlasManager.ICONS);
            TextureAtlasSprite emptyIcon = atlas
                    .apply(emptyCache.computeIfAbsent(element, ElementLevelToolsHud::transEmpty));

            RenderSystem.setShaderTexture(0, VidaAtlasManager.ICONS);
            RenderSystem.setShaderColor(1, 1, 1, alpha);

            // 定位渲染位置
            int renderX = 3;
            int renderY = (int) (height * 5.5F / 6.0F);

            // 底图
            GuiComponent.blit(poseStack, renderX, renderY, 0, emptyIcon.getWidth(), emptyIcon.getHeight(), emptyIcon);

            int level = item.getCurrentLevel(lastStack);
            double exp = item.getCurrentLevelXP(lastStack);
            double percent = exp / item.getNextLevelRequiredXP(lastStack);

            TextureAtlasSprite icon = atlas.apply(cache.computeIfAbsent(element, ElementLevelToolsHud::trans));
            
            RenderHelper.blitVertical(poseStack, renderX, renderY, icon.getHeight(), icon.getWidth(), 0, icon, percent);

            // 显示工具等级
            int a = (int) (255 * alpha);

            if (a > 10) {
                GuiComponent.drawCenteredString(poseStack, mc.font, level + "", renderX + 16, renderY + 10,
                        RGBAColor.getColorCodeFromRGBA(126, 186, 137, a));
            }
        }
    }
}
