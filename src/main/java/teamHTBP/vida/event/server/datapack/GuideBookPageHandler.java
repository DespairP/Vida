package teamHTBP.vida.event.server.datapack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.helper.guidebookHelper.GuidebookHelper;
import teamHTBP.vida.helper.guidebookHelper.GuidebookSinglePage;
import teamHTBP.vida.helper.guidebookHelper.components.IGuidebookComponent;
import teamHTBP.vida.helper.guidebookHelper.serializer.GuidebookComponentSerializer;
import teamHTBP.vida.network.PacketGuidebookPage;
import teamHTBP.vida.utils.json.serializer.ItemStackSerializer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GuideBookPageHandler extends SimpleJsonResourceReloadListener {
    private static final Logger LOGGER = LogManager.getLogger();
    /**GSON*/
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(IGuidebookComponent.class, new GuidebookComponentSerializer())
            .registerTypeAdapter(ItemStack.class,new ItemStackSerializer())
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    /**
     * 存储所有Pages,GuideId->List Pages
     * Guide可以通过{@link GuideBookGuideHandler#guideMap}来获取,
     * GuideHandler可以通过{@link GuidebookHelper#getGuideHandler(Level)}获取
     * */
    public Map<String, List<GuidebookSinglePage>> guideToSinglePage = new LinkedHashMap<>();


    public GuideBookPageHandler() {
        super(GSON, "guidebook/pages");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        //全部处理并group by guideName
        Map<String,List<GuidebookSinglePage>> pages = objectIn.entrySet().stream().map((entry) -> {
            JsonElement jsonElement = entry.getValue();
            ResourceLocation resourceLocation = entry.getKey();
            GuidebookSinglePage page = GSON.fromJson(jsonElement,GuidebookSinglePage.class);
            page.resourceLocation = resourceLocation;
            LOGGER.debug("page in guide {} has registered: {}",page.guide, page);
            return page;
        }).collect(Collectors.groupingBy(guidebookSinglePage -> guidebookSinglePage.guide));
        //放入map中
        guideToSinglePage.putAll(pages);
        //转发给客户端
    }


    public PacketGuidebookPage createSyncPacket(){
        return new PacketGuidebookPage(guideToSinglePage);
    }

    @OnlyIn(Dist.CLIENT)
    public void clear(){
        this.guideToSinglePage.clear();
    }

    /**传入server端的数据*/
    @OnlyIn(Dist.CLIENT)
    public void setServerMap(Map<String,List<GuidebookSinglePage>> pages){
        this.guideToSinglePage = pages;
    }
}
