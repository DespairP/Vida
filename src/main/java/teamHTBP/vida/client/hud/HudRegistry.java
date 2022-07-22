package teamHTBP.vida.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HudRegistry {
    private static final ElementLevelToolsHud ELEMENT_LEVEL_TOOLS = new ElementLevelToolsHud();

    @SubscribeEvent
    public static void onEvent(FMLClientSetupEvent event) {
        final Minecraft mc = Minecraft.getInstance();

        OverlayRegistry.registerOverlayTop("element_level_tools", ELEMENT_LEVEL_TOOLS);
    }
}
