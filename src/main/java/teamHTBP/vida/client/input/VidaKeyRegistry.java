package teamHTBP.vida.client.input;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.Vida;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VidaKeyRegistry {
    public static final KeyMapping TEST = new KeyMapping("test", InputConstants.KEY_P, Vida.MOD_ID);

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(BottleKeys.MESSAGE_KEY);
        ClientRegistry.registerKeyBinding(BottleKeys.MESSAGE_KEY_1);
        ClientRegistry.registerKeyBinding(BottleKeys.MESSAGE_KEY_2);
        ClientRegistry.registerKeyBinding(BottleKeys.MESSAGE_KEY_3);

        ClientRegistry.registerKeyBinding(TEST);
    }

}
