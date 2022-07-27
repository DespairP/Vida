package teamHTBP.vida.datapack.guidebook;

import com.google.common.collect.MapMaker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.DataPackRegistries;
import net.minecraft.resources.IResourceManager;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.Vida;
import teamHTBP.vida.network.PacketChannel;
import teamHTBP.vida.network.PacketGuidebook;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * 用于读取指导手册的所有条目,
 * 服务器和客户端创建时会生成一个GuideBookHandler
 *
 * <br/>
 * 首先，当服务器创建时会调用{@link GuideBookGuideEventHandler#dataPackRegistry(AddReloadListenerEvent)}开始注册,
 * 注册时会调用{@link GuideBookGuideHandler#apply(Map, IResourceManager, IProfiler)}方法进行json解析
 * 注意在服务器环境中有一{@link GuideBookGuideEventHandler#dataPackMap}用于存服务端的handler;
 *
 */
@Mod.EventBusSubscriber(modid = Vida.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GuideBookGuideEventHandler {
    /**服务端的Handler*/
    private static final ConcurrentMap<DataPackRegistries, GuideBookGuideHandler> dataPackMap = new MapMaker().weakKeys().makeMap();
    /**LOGGER*/
    public final static Logger LOGGER = LogManager.getLogger();
    /**客户端的Handler*/
    public static GuideBookGuideHandler clientHandler = new GuideBookGuideHandler();

    /**加入新的data数据包监听器*/
    @SubscribeEvent
    public static void dataPackRegistry(AddReloadListenerEvent event) {
        GuideBookGuideHandler guideBookHandler = new GuideBookGuideHandler();
        if(dataPackMap.putIfAbsent(event.getDataPackRegistries(), guideBookHandler) != null){
            LOGGER.error("duplicated datapack registries");
        }
        event.addListener(guideBookHandler);
    }

    /**当玩家进入服务器时，服务器将数据包传给客户端*/
    @SubscribeEvent
    public static void loginSyncDataPack(PlayerEvent.PlayerLoggedInEvent event){
        PlayerEntity entity = event.getPlayer();
        if(!(entity instanceof ServerPlayerEntity)){
            return;
        }
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) entity;
        //从server取到Handler里的guideMap
        GuideBookGuideHandler handler = dataPackMap.get(serverPlayer.getServer().getDataPackRegistries());
        PacketGuidebook guidebook = handler.createSyncPacket();
        //将服务器的数据
        PacketChannel.INSTANCE.sendTo(guidebook, serverPlayer.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }

    /**当用户登出时，清除客户端存储的guidemap*/
    @SubscribeEvent
    public static void clientDisconnect(ClientPlayerNetworkEvent.LoggedOutEvent event){
        clientHandler.clear();
    }

    /**获取服务端的Handler*/
    public static GuideBookGuideHandler getServerHandler(World world){
        if(world.getServer() != null){
            LOGGER.error("expected error:get server GuidebookHandler in client world");
            return null;
        }
        return dataPackMap.get(world.getServer().getDataPackRegistries());
    }
}