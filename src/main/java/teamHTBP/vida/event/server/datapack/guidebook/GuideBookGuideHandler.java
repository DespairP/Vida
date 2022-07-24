package teamHTBP.vida.event.server.datapack.guidebook;


import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.event.server.datapack.ModDataPack;
import teamHTBP.vida.helper.guidebook.Guide;
import teamHTBP.vida.utils.json.JsonUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**datapack相关guidebook的guide处理器*/
public class GuideBookGuideHandler extends ModDataPack<GuideBookGuideHandler> {
    public static final GuideBookGuideHandler INSTANCE = new GuideBookGuideHandler();

    /**Handler中的所有guide,这里可能为客户端的也可能是服务端的*/
    public Map<String, Guide> guideMap = new LinkedHashMap<>();
    /**LOGGER*/
    public final static Logger LOGGER = LogManager.getLogger();


    public GuideBookGuideHandler() {
        super(JsonUtils.INSTANCE.noExpose, "guidebook/guides");
    }

    @Override
    protected GuideBookGuideHandler load() {
        guideMap.clear();

        object.forEach((resourceLocation, jsonElement)->{
            Guide guide = JsonUtils.INSTANCE.noExpose.fromJson(jsonElement,Guide.class);
            guideMap.put(guide.id, guide);
            LOGGER.debug("vida guide:{} is registered", guide);
        });

        return this;
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

    @Override
    public Class<GuideBookGuideHandler> getRegistryType() {
        return GuideBookGuideHandler.class;
    }

    public static final Codec<GuideBookGuideHandler> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(Codec.STRING.fieldOf("data").forGetter(GuideBookGuideHandler::save))
                    .apply(instance, GuideBookGuideHandler::load));

    private static GuideBookGuideHandler load(String json) {
        INSTANCE.object = JsonUtils.INSTANCE.noExpose.fromJson(json,
                TypeToken.getParameterized(Map.class, ResourceLocation.class, JsonElement.class).getType());

        if (INSTANCE.object != null) {
            return INSTANCE.load();
        }
        else {
            return INSTANCE;
        }
    }
}


