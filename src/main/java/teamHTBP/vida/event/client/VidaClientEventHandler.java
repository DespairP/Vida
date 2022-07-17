package teamHTBP.vida.event.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.Vida;
import teamHTBP.vida.event.custom.GuidebookComponentRegisterEvent;
import teamHTBP.vida.helper.guidebookHelper.GuidebookHelper;
import teamHTBP.vida.helper.guidebookHelper.components.CraftingGuidebookComponent;
import teamHTBP.vida.helper.guidebookHelper.components.ModelGuidebookComponent;
import teamHTBP.vida.helper.guidebookHelper.components.TextGuidebookComponent;

@Mod.EventBusSubscriber(modid = Vida.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VidaClientEventHandler {
    @SubscribeEvent
    public static void onInitializeClient(FMLClientSetupEvent event){
        registerComponent();
    }

    public static void registerComponent(){
        GuidebookHelper.addBookComponent("model", ModelGuidebookComponent.class);
        GuidebookHelper.addBookComponent("text", TextGuidebookComponent.class);
        GuidebookHelper.addBookComponent("crafting", CraftingGuidebookComponent.class);
    }
}
