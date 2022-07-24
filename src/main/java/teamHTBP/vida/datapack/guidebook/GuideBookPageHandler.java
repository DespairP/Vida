package teamHTBP.vida.datapack.guidebook;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.datapack.ModDataPack;
import teamHTBP.vida.helper.guidebook.GuidebookHelper;
import teamHTBP.vida.helper.guidebook.GuidebookSinglePage;
import teamHTBP.vida.utils.json.JsonUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GuideBookPageHandler extends ModDataPack<GuideBookPageHandler> {
    public static final GuideBookPageHandler INSTANCE = new GuideBookPageHandler();

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * 存储所有Pages,GuideId->List Pages
     * Guide可以通过{@link GuideBookGuideHandler#guideMap}来获取,
     * GuideHandler可以通过{@link GuidebookHelper#getGuideHandler(Level)}获取
     * */
    public Map<String, List<GuidebookSinglePage>> guideToSinglePage = new LinkedHashMap<>();


    public GuideBookPageHandler() {
        super(JsonUtils.INSTANCE.noExpose, "guidebook/pages");
    }

    @Override
    protected GuideBookPageHandler load() {
        guideToSinglePage.clear();

        //全部处理并group by guideName
        Map<String,List<GuidebookSinglePage>> pages = object.entrySet().stream().map((entry) -> {
            JsonElement jsonElement = entry.getValue();
            ResourceLocation resourceLocation = entry.getKey();
            GuidebookSinglePage page = JsonUtils.INSTANCE.noExpose.fromJson(jsonElement,GuidebookSinglePage.class);
            page.resourceLocation = resourceLocation;
            LOGGER.debug("page in guide {} has registered: {}",page.guide, page);
            return page;
        }).collect(Collectors.groupingBy(guidebookSinglePage -> guidebookSinglePage.guide));
        //放入map中
        guideToSinglePage.putAll(pages);

        return this;
    }

    @Override
    public Class<GuideBookPageHandler> getRegistryType() {
        return GuideBookPageHandler.class;
    }

    public static final Codec<GuideBookPageHandler> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(Codec.STRING.fieldOf("data").forGetter(GuideBookPageHandler::save))
                    .apply(instance, GuideBookPageHandler::load));

    private static GuideBookPageHandler load(String json) {
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
