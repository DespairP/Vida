package teamHTBP.vida.helper.guidebook.serializer;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.helper.guidebook.GuidebookHelper;
import teamHTBP.vida.helper.guidebook.components.IGuidebookComponent;
import teamHTBP.vida.helper.guidebook.components.TextGuidebookComponent;
import teamHTBP.vida.helper.json.serializer.ItemStackSerializer;

import java.lang.reflect.Type;

public class GuidebookComponentSerializer implements JsonSerializer<IGuidebookComponent>, JsonDeserializer<IGuidebookComponent> {
    /**LOGGER*/
    private static final Logger LOGGER = LogManager.getLogger();
    /**GSON*/
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(IGuidebookComponent.class, new GuidebookComponentSerializer())
            .registerTypeAdapter(ItemStack.class,new ItemStackSerializer())
            .registerTypeAdapter(TextGuidebookComponent.class,new GuidebookTextComponentSerializer())
            .excludeFieldsWithoutExposeAnnotation()
            .create();


    /**反序列化guidebook component*/
    @Override
    public IGuidebookComponent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        //
        JsonObject component = jsonElement.getAsJsonObject();
        //
        JsonElement componentTypeElement = component.get("type");
        if(componentTypeElement == null){
            LOGGER.error("componentType is required!please check the component string:{}", component.toString());
            throw new JsonParseException("componentType is required!please check the component");
        }
        String componentType = componentTypeElement.getAsString();
        Class<? extends IGuidebookComponent> clazz = getComponentByTypeName(componentType);
        return GSON.fromJson(jsonElement, clazz);
    }

    /**序列化*/
    @Override
    public JsonElement serialize(IGuidebookComponent iGuidebookComponent, Type type, JsonSerializationContext jsonSerializationContext) {
        Class<? extends IGuidebookComponent> guideBookComponentClazz = getComponentByTypeName(iGuidebookComponent.getType());
        if(guideBookComponentClazz == null){
            LOGGER.error("cannot find componentType!please check the component type:{}", iGuidebookComponent.toString());
            throw new JsonParseException("cannot find componentType!please check the component type");
        }
        Type componentType = TypeToken.get(guideBookComponentClazz).getType();
        return jsonSerializationContext.serialize(iGuidebookComponent, componentType);
    }

    /**根据type来获取组件类型*/
    public Class<? extends IGuidebookComponent> getComponentByTypeName(String type){
        return GuidebookHelper.getComponentByTypeName(type);
    }
}
