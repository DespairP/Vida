package teamHTBP.vida.gui.gui;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.gui.menu.ContainerTypeLoader;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GuiLoader {
    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ContainerTypeLoader.prismTable.get(), ContainerScreenPrismTable::new);
            MenuScreens.register(ContainerTypeLoader.oreReaction.get(), ContainerScreenOreReactionMachine::new);
            MenuScreens.register(ContainerTypeLoader.bottles.get(), ContainerScreenBottles::new);
            MenuScreens.register(ContainerTypeLoader.inject.get(), ContainerScreenInjectTable::new);
            MenuScreens.register(ContainerTypeLoader.bluePrints.get(), ContainerScreenBluePrint::new);
        });
    }
}
