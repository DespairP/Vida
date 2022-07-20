package teamHTBP.vida.event.server.datapack;

import com.google.common.collect.MapMaker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.util.thread.EffectiveSide;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.capability.blueprintCapability.Blueprint;
import teamHTBP.vida.helper.blueprintHelper.EnumBlueprintRarity;
import teamHTBP.vida.helper.blueprintHelper.EnumBlueprintType;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

// TODO 需要重写
/**PENDING,还未上线*/
public class BluePrintManager extends SimpleJsonResourceReloadListener {
    /**蓝图管理*/
    private final Map<ResourceLocation, Blueprint> blueprints = Collections.emptyMap();
    /**Gson*/
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    /**LOGGER*/
    private static final Logger LOGGER = LogManager.getLogger();
    /**manager,注意：这里服务端和客户端都拥有*/
    private static final BluePrintManager clientManager = new BluePrintManager();
    /**cocurrent map*/
    private static final ConcurrentMap<ReloadableServerResources, BluePrintManager> map = new MapMaker().weakKeys().makeMap();

    public BluePrintManager() {
        super(GSON, "blueprints");
    }

    /**解析文件夹内的datapack*/
    @Override
    protected void apply(Map<ResourceLocation, JsonElement> objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
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

    public Blueprint parseBlueprint(ResourceLocation location, JsonElement element){
        JsonObject object = element.getAsJsonObject();
        EnumBlueprintRarity rarity = EnumBlueprintRarity.valueOf(Optional.ofNullable(object.get("rarity").getAsString()).orElse("NORMAL"));
        EnumBlueprintType type = EnumBlueprintType.valueOf(Optional.ofNullable(object.get("type").getAsString()).orElse("OTHERS"));
        return new Blueprint(rarity, location.toString(), type, null);
    }


    public void clear(){
        blueprints.clear();
    }

    /**根据不同的情况获取manager*/
    public BluePrintManager getManager(Level world){
        if(world.isClientSide){
            return clientManager;
        }
        MinecraftServer server = world.getServer();
        if(server == null){
            throw new NullPointerException("server is not server");
        }
        return map.get(server.getServerResources());
    }

    /**当用户登录时，将服务端datapack传输到客户端*/
    @SubscribeEvent
    public void playerLogin(PlayerEvent.PlayerLoggedInEvent event){
        if(!(event.getPlayer() instanceof ServerPlayer)) return;
        //do sync server -> client
    }

    /**当用户登出时，清除客户端dackpack*/
    @SubscribeEvent
    public void clientDisconnect(ClientPlayerNetworkEvent.LoggedOutEvent event){
        clientManager.clear();
    }

    /**注册datapack时，将datapack放入map中*/
    @SubscribeEvent
    public void dataPackRegister(AddReloadListenerEvent event){
        BluePrintManager serverManager = new BluePrintManager();
        if(map.putIfAbsent(event.getServerResources(),serverManager) == null){
            throw new IllegalArgumentException("duplicate manger");
        }
        event.addListener(clientManager);
    }
}
