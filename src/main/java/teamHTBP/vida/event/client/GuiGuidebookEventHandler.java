package teamHTBP.vida.event.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.Vida;
import teamHTBP.vida.event.custom.GuidebookComponentRegisterEvent;
import teamHTBP.vida.helper.guidebookHelper.GuidebookHelper;
import teamHTBP.vida.helper.guidebookHelper.components.CraftingGuidebookComponent;
import teamHTBP.vida.helper.guidebookHelper.components.ModelGuidebookComponent;
import teamHTBP.vida.helper.guidebookHelper.components.TextGuidebookComponent;

/**
 * 关于Guidebook的事件处理
 * */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Vida.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GuiGuidebookEventHandler {

    /**当玩家登出的时候清空Guidebook Manager*/
    @SubscribeEvent
    public static void clearManager(ClientPlayerNetworkEvent.LoggedOutEvent event){
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()->{
            GuidebookHelper.getManager().forceClear();
        });
    }



}
