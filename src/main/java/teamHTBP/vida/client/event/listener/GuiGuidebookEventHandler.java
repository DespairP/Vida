package teamHTBP.vida.client.event.listener;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.Vida;
import teamHTBP.vida.helper.guidebook.GuidebookHelper;

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
