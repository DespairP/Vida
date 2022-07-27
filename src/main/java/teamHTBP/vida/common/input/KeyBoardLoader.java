package teamHTBP.vida.common.input;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBoardLoader {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(KeyBoardBottle.MESSAGE_KEY);
        ClientRegistry.registerKeyBinding(KeyBoardBottle.MESSAGE_KEY_1);
        ClientRegistry.registerKeyBinding(KeyBoardBottle.MESSAGE_KEY_2);
        ClientRegistry.registerKeyBinding(KeyBoardBottle.MESSAGE_KEY_3);
    }

}
