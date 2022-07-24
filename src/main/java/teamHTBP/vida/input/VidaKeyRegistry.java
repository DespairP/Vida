package teamHTBP.vida.input;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VidaKeyRegistry {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(BottleKeys.MESSAGE_KEY);
        ClientRegistry.registerKeyBinding(BottleKeys.MESSAGE_KEY_1);
        ClientRegistry.registerKeyBinding(BottleKeys.MESSAGE_KEY_2);
        ClientRegistry.registerKeyBinding(BottleKeys.MESSAGE_KEY_3);
    }

}
