package teamHTBP.vida.Network;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkHooks;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PacketLoader {

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        PacketChannel.register();
    }
}
