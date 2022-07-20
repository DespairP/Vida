package teamHTBP.vida.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.event.server.datapack.GuideBookGuideEventHandler;
import teamHTBP.vida.helper.guidebookHelper.Guide;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PacketGuidebook {
    private Map<String, Guide> guideMap;
    private static final Logger LOGGER = LogManager.getLogger();


    /**传递pack的时候使用*/
    public PacketGuidebook(Map<String, Guide> guideMap) {
        this.guideMap = guideMap;
    }

    /**读取Packet,并处理成需要的格式*/
    public static PacketGuidebook fromBytes(PacketBuffer buffer){
        final Gson gson = new Gson();
        String guideMapString = buffer.readUtf();
        LOGGER.debug("get server guides: {}", guideMapString);
        return parseGuideMapString(gson, guideMapString);
    }

    /**解析Packet传入的String,并转为*/
    private static PacketGuidebook parseGuideMapString(Gson gson,String guideMapString){
        Map<String,Guide> parsedGuideMap = new LinkedHashMap<>();
        JsonParser parser = new JsonParser();
        JsonObject map = parser.parse(guideMapString).getAsJsonObject();
        //对map进行遍历
        for(Map.Entry<String, JsonElement> entry : map.entrySet()){
            Guide guide = gson.fromJson(entry.getValue(), Guide.class);
            parsedGuideMap.put(entry.getKey(), guide);
        }
        //进行封装
        return new PacketGuidebook(parsedGuideMap);
    }

    /**将数据写入packet*/
    public void toBytes(PacketBuffer buffer){
        if(guideMap == null){
            LOGGER.error("packet from guide is null,cannot send the packet");
            throw new NullPointerException("guideMap is null");
        }
        Gson gson = new Gson();
        String mapString = gson.toJson(guideMap);
        LOGGER.debug("server is sending guide packet:");
        buffer.writeUtf(mapString);
    }

    /**如何处理数据包*/
    public static void handler(PacketGuidebook guidebook,Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(()->{
            LOGGER.debug("get from server : {}", guidebook.guideMap);
            //放入客户端的guidebook中即可
            GuideBookGuideEventHandler.clientHandler.setGuideMapFromServer(guidebook.guideMap);
        });
        ctx.get().setPacketHandled(true);
    }
}
