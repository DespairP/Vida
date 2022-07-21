package teamHTBP.vida.event.server.datapack;

import com.google.common.collect.MapMaker;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.Vida;
import teamHTBP.vida.network.PacketChannel;
import teamHTBP.vida.network.PacketGuidebookPage;

import java.util.concurrent.ConcurrentMap;

@Mod.EventBusSubscriber(modid = Vida.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GuideBookPageEventHandler {
    /**客户端实例*/
    public static final GuideBookPageHandler clientHandler = new GuideBookPageHandler();
    /**服务端的Handler*/
    private static final ConcurrentMap<MinecraftServer.ReloadableResources, GuideBookPageHandler> dataPackMap = new MapMaker().weakKeys().makeMap();
    /**LOGGER*/
    public final static Logger LOGGER = LogManager.getLogger();


    /**加入新的data数据包监听器*/
    @SubscribeEvent
    public static void dataPackRegistry(AddReloadListenerEvent event) {
        GuideBookPageHandler guideBookPageHandler = new GuideBookPageHandler();
        // if(dataPackMap.putIfAbsent(ServerLifecycleHooks.getCurrentServer().getServerResources(), guideBookPageHandler) != null){
        //     LOGGER.error("duplicated datapack registries");
        // }
        event.addListener(guideBookPageHandler);
    }

    /**当玩家进入服务器时，服务器将数据包传给客户端*/
    @SubscribeEvent
    public static void loginSyncDataPack(PlayerEvent.PlayerLoggedInEvent event){
        Player entity = event.getPlayer();
        if(!(entity instanceof ServerPlayer)){
            return;
        }
        ServerPlayer serverPlayer = (ServerPlayer) entity;
        //从server取到Handler里的guideMap
        GuideBookPageHandler handler = dataPackMap.get(serverPlayer.getServer().getServerResources());

        if (handler == null) {
            return;
        }

        PacketGuidebookPage guidebook = handler.createSyncPacket();
        //将服务器的数据
        PacketChannel.INSTANCE.sendTo(guidebook, serverPlayer.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    /**当用户登出时，清除客户端存储的guidemap*/
    @SubscribeEvent
    public static void clientDisconnect(ClientPlayerNetworkEvent.LoggedOutEvent event){
        clientHandler.clear();
    }

    /**获取服务端的Handler*/
    public static GuideBookPageHandler getServerHandler(Level world){
        if(world.getServer() != null){
            LOGGER.error("expected error:get server GuidebookHandler in client world");
            return null;
        }
        return dataPackMap.get(world.getServer().getServerResources());
    }
}
