package teamHTBP.vida.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.datapack.GuideBookPageEventHandler;
import teamHTBP.vida.datapack.GuideBookPageHandler;
import teamHTBP.vida.helper.guidebookHelper.GuidebookSinglePage;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class PacketGuidebookPage {
    private Map<String, List<GuidebookSinglePage>> guideToSinglePage;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = GuideBookPageHandler.GSON;

    public PacketGuidebookPage(Map<String, List<GuidebookSinglePage>> guideToSinglePage){
        this.guideToSinglePage = guideToSinglePage;
    }

    /**序列化*/
    public PacketBuffer toBytes(PacketBuffer buffer){
        if(guideToSinglePage == null){
            throw new NullPointerException("guidebook singlePage is null");
        }
        String mapString = GSON.toJson(guideToSinglePage);
        buffer.writeString(mapString);
        return buffer;
    }

    /**反序列化*/
    public static PacketGuidebookPage fromBytes(PacketBuffer buffer){
        String pagesString = buffer.readString();
        if(pagesString.isEmpty()){
            LOGGER.error("pages in empty");
            return new PacketGuidebookPage(new LinkedHashMap<>());
        }
        return parseGuideToSinglePage(pagesString);
    }

    /**反序列化String为map*/
    public static PacketGuidebookPage parseGuideToSinglePage(String mapString){
        Map<String, List<GuidebookSinglePage>> parsedGuidebookPages = new LinkedHashMap<>();
        JsonParser parser = new JsonParser();
        JsonObject map = parser.parse(mapString).getAsJsonObject();
        //序列化list
        Type listType = new TypeToken<LinkedList<GuidebookSinglePage>>(){}.getType();
        //对map进行遍历
        for(Map.Entry<String, JsonElement> entry : map.entrySet()){
            List<GuidebookSinglePage> page = GSON.fromJson(entry.getValue(), listType);
            parsedGuidebookPages.put(entry.getKey(), page);
        }
        return new PacketGuidebookPage(parsedGuidebookPages);
    }

    //处理packet
    public static void handler(PacketGuidebookPage page, Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{
            LOGGER.debug("page in has received size: {}", page.guideToSinglePage.size());
            GuideBookPageEventHandler.clientHandler.setServerMap(page.guideToSinglePage);
        });
        ctx.get().setPacketHandled(true);
    }
}
