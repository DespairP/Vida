package teamHTBP.vida.network;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PacketLoader {

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        PacketChannel.register();
    }
}
