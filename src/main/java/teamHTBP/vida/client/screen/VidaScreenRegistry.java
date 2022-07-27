package teamHTBP.vida.client.screen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.common.menu.VidaMenuTypeLoader;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VidaScreenRegistry {
    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(VidaMenuTypeLoader.prismTable.get(), PrismTableScreen::new);
            MenuScreens.register(VidaMenuTypeLoader.oreReaction.get(), OreReactionMachineScreen::new);
            MenuScreens.register(VidaMenuTypeLoader.bottles.get(), BottlesScreen::new);
            MenuScreens.register(VidaMenuTypeLoader.inject.get(), InjectTableScreen::new);
            MenuScreens.register(VidaMenuTypeLoader.bluePrints.get(), BluePrintScreen::new);
        });
    }
}
