package teamHTBP.vida.client.gui.screen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.menu.TypeLoaderMenu;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VidaScreenRegistry {
    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(TypeLoaderMenu.prismTable.get(), PrismTableScreen::new);
            MenuScreens.register(TypeLoaderMenu.oreReaction.get(), OreReactionMachineScreen::new);
            MenuScreens.register(TypeLoaderMenu.bottles.get(), BottlesScreen::new);
            MenuScreens.register(TypeLoaderMenu.inject.get(), InjectTableScreen::new);
            MenuScreens.register(TypeLoaderMenu.bluePrints.get(), BluePrintScreen::new);
        });
    }
}
