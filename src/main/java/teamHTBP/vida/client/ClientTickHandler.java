package teamHTBP.vida.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 监听clientTickEvent用于renderer使用
 *
 * @author DustW
 * */
@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientTickHandler {
    private static long tick = 0;

    public static long tick() {
        return tick;
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        tick++;
    }
}
