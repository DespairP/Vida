package teamHTBP.vida.gui.GUI;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GuiLoader {
    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ContainerTypeLoader.prismTable.get(), (ContainerPrismTable screenContainer, PlayerInventory inv, ITextComponent titleIn) -> {
            return new ContainerScreenPrismTable(screenContainer, inv, titleIn);
        });
        ScreenManager.registerFactory(ContainerTypeLoader.oreReaction.get(), (ContainerOreReactionMachine screenContainer, PlayerInventory inv, ITextComponent titleIn) -> {
            return new ContainerScreenOreReactionMachine(screenContainer, inv, titleIn);
        });
        ScreenManager.registerFactory(ContainerTypeLoader.bottles.get(), (ContainerBottles bottles,PlayerInventory inv, ITextComponent titleIn) -> {
            return new ContainerScreenBottles(bottles, inv, titleIn);
        });
    }

}