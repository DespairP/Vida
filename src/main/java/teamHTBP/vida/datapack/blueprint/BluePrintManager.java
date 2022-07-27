package teamHTBP.vida.datapack.blueprint;

import com.google.common.collect.MapMaker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.DataPackRegistries;
import net.minecraft.resources.IResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.thread.EffectiveSide;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.common.capability.blueprintCapability.Blueprint;
import teamHTBP.vida.helper.blueprintHelper.EnumBlueprintRarity;
import teamHTBP.vida.helper.blueprintHelper.EnumBlueprintType;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**PENDING,还未上线*/
public class BluePrintManager extends JsonReloadListener {
    /**蓝图管理*/
    private final Map<ResourceLocation, Blueprint> blueprints = Collections.emptyMap();
    /**Gson*/
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    /**LOGGER*/
    private static final Logger LOGGER = LogManager.getLogger();
    /**manager,注意：这里服务端和客户端都拥有*/
    private static final BluePrintManager clientManager = new BluePrintManager();
    /**cocurrent map*/
    @OnlyIn(Dist.DEDICATED_SERVER)
    private static final ConcurrentMap<DataPackRegistries, BluePrintManager> map = new MapMaker().weakKeys().makeMap();

    public BluePrintManager() {
        super(GSON, "blueprints");
    }

    /**解析文件夹内的datapack*/
    @Override
    protected void apply(Map<ResourceLocation, JsonElement> objectIn, IResourceManager resourceManagerIn, IProfiler profilerIn) {
        Map<ResourceLocation,Blueprint> processBlueprints = objectIn.entrySet().stream().map(
                (set)->{
                    Blueprint blueprint = parseBlueprint(set.getKey(), set.getValue());
                    return new AbstractMap.SimpleEntry<>(set.getKey(), blueprint);
                }
        ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
        blueprints.putAll(processBlueprints);
        // 向客户端传递datapack
        if(EffectiveSide.get().isServer() && ServerLifecycleHooks.getCurrentServer() != null){
            //TODO
        }
    }

    public Blueprint parseBlueprint(ResourceLocation location,JsonElement element){
        JsonObject object = element.getAsJsonObject();
        EnumBlueprintRarity rarity = EnumBlueprintRarity.valueOf(Optional.ofNullable(object.get("rarity").getAsString()).orElse("NORMAL"));
        EnumBlueprintType type = EnumBlueprintType.valueOf(Optional.ofNullable(object.get("type").getAsString()).orElse("OTHERS"));
        return new Blueprint(rarity, location.toString(), type, null);
    }


    public void clear(){
        blueprints.clear();
    }

    /**根据不同的情况获取manager*/
    public BluePrintManager getManager(World world){
        if(world.isRemote){
            return clientManager;
        }
        MinecraftServer server = world.getServer();
        if(server == null){
            throw new NullPointerException("server is not server");
        }
        return map.get(server.getDataPackRegistries());
    }

    /**当用户登录时，将服务端datapack传输到客户端*/
    @SubscribeEvent
    public void playerLogin(PlayerEvent.PlayerLoggedInEvent event){
        if(!(event.getPlayer() instanceof ServerPlayerEntity)) return;
        //do sync server -> client
    }

    /**当用户登出时，清除客户端dackpack*/
    @SubscribeEvent
    public void clientDisconnect(ClientPlayerNetworkEvent.LoggedOutEvent event){
        clientManager.clear();
    }

    /**注册datapack时，将datapack放入map中*/
    @OnlyIn(Dist.DEDICATED_SERVER)
    @SubscribeEvent
    public void dataPackRegister(AddReloadListenerEvent event){
        BluePrintManager serverManager = new BluePrintManager();
        if(map.putIfAbsent(event.getDataPackRegistries(),serverManager) == null){
            throw new IllegalArgumentException("duplicate manger");
        }
        event.addListener(clientManager);
    }
}
