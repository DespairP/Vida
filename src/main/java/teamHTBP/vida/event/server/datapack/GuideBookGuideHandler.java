package teamHTBP.vida.event.server.datapack;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.fml.common.thread.EffectiveSide;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.helper.guidebookHelper.Guide;
import teamHTBP.vida.network.PacketChannel;
import teamHTBP.vida.network.PacketGuidebook;

import java.util.*;
import java.util.stream.Collectors;


public class GuideBookGuideHandler extends JsonReloadListener {
    /**Handler中的所有guide,这里可能为客户端的也可能是服务端的*/
    public Map<String, Guide> guideMap = new LinkedHashMap<>();
    /**GSON*/
    public final static Gson GSON = new Gson();
    /**LOGGER*/
    public final static Logger LOGGER = LogManager.getLogger();


    public GuideBookGuideHandler() {
        super(GSON, "guides");
    }

    /**解析data数据包*/
    @Override
    public void apply(Map<ResourceLocation, JsonElement> objectIn, IResourceManager resourceManagerIn, IProfiler profilerIn) {
        objectIn.forEach((resourceLocation, jsonElement)->{
            Guide guide = GSON.fromJson(jsonElement,Guide.class);
            guideMap.put(guide.id, guide);
            LOGGER.debug("vida guide:{} is registered", guide);
        });

        // 向客户端传递datapack
        if(EffectiveSide.get().isServer() && ServerLifecycleHooks.getCurrentServer() != null){
            PacketChannel.INSTANCE.send(PacketDistributor.ALL.noArg(), createSyncPacket());
        }
    }

    /**建立sync数据包*/
    public PacketGuidebook createSyncPacket(){
        return new PacketGuidebook(guideMap);
    }

    /**将传入的guide放入此处*/
    @OnlyIn(Dist.CLIENT)
    public void setGuideMapFromServer(Map<String,Guide> serverGuideMap){
        guideMap = serverGuideMap;
    }

    public void clear(){
        guideMap.clear();
    }

    public Guide getFirstGuide(){
        return guideMap.size() > 0 ? guideMap.entrySet().iterator().next().getValue() : null;
    }

    /**获取所有指导*/
    public Map<String,Guide> getAllGuides(){
        return guideMap;
    }

    /**获取搜索的map*/
    public Map<String,Guide> searchGuides(String keyword){
        return guideMap
                .entrySet()
                .stream()
                .filter(guideEntry-> !guideEntry.getValue().name.contains(keyword))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}


