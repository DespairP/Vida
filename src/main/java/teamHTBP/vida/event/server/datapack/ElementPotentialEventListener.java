package teamHTBP.vida.event.server.datapack;

import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.event.server.datapack.ElementPotentialManager;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber
public class ElementPotentialEventListener {
    public static final ElementPotentialManager ELEMENT_POTENTIAL_MANAGER = new ElementPotentialManager();

    @SubscribeEvent
    public static void onEvent(AddReloadListenerEvent event) {
        event.addListener(ELEMENT_POTENTIAL_MANAGER);
    }
}
