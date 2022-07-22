package teamHTBP.vida.client.hud;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.client.hud.element.ElementLevelToolsExpHud;
import teamHTBP.vida.client.hud.element.ElementLevelToolsHud;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HudRegistry {
    private static final ElementLevelToolsHud ELEMENT_LEVEL_TOOLS = new ElementLevelToolsHud();
    private static final ElementLevelToolsExpHud ELEMENT_LEVEL_TOOLS_EXP = new ElementLevelToolsExpHud();

    @SubscribeEvent
    public static void onEvent(FMLClientSetupEvent event) {
        OverlayRegistry.registerOverlayTop("element_level_tools", ELEMENT_LEVEL_TOOLS);
        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.EXPERIENCE_BAR_ELEMENT,
                "element_level_tools_exp", ELEMENT_LEVEL_TOOLS_EXP);
    }
}
