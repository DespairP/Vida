package teamHTBP.vida.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.Vida;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HudRegistry {
    public static IIngameOverlay ELEMENT_LEVEL_TOOLS;

    private static final ResourceLocation HUD = new ResourceLocation(Vida.MOD_ID, "textures/gui/pickaxe_hud.png");

    @SubscribeEvent
    public static void onEvent(FMLClientSetupEvent event) {
        final Minecraft mc = Minecraft.getInstance();

        OverlayRegistry.registerOverlayTop("element_level_tools",
                ELEMENT_LEVEL_TOOLS = (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
                    if (!mc.options.hideGui) {
                        gui.setupOverlayRenderState(true, false);

                        RenderSystem.setShaderTexture(0, HUD);
                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                        RenderSystem.enableBlend();

                        ElementLevelToolsHud.renderElementLevelTools(gui, poseStack, partialTick, screenWidth, screenHeight);

                        RenderSystem.disableBlend();
                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    }
                });
    }
}
