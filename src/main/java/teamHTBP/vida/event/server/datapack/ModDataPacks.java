package teamHTBP.vida.event.server.datapack;

import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber
public class ModDataPacks {
    public static final ElementPotentialManager ELEMENT_POTENTIAL_MANAGER = new ElementPotentialManager();

    @SubscribeEvent
    public static void onEvent(AddReloadListenerEvent event) {
        event.addListener(ELEMENT_POTENTIAL_MANAGER);
    }
}
