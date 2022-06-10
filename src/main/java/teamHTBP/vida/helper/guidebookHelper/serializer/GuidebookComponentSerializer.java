package teamHTBP.vida.helper.guidebookHelper.serializer;

import com.google.gson.*;
import net.minecraft.item.crafting.Ingredient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.helper.guidebookHelper.GuidebookHelper;
import teamHTBP.vida.helper.guidebookHelper.components.IGuidebookComponent;
import teamHTBP.vida.helper.guidebookHelper.components.TextGuidebookComponent;

import java.lang.reflect.Type;

public class GuidebookComponentSerializer implements JsonSerializer<IGuidebookComponent>, JsonDeserializer<IGuidebookComponent> {
    /**LOGGER*/
    private static final Logger LOGGER = LogManager.getLogger();
    /**GSON*/
    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();


    /**反序列化guidebook component*/
    @Override
    public IGuidebookComponent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        //
        JsonObject component = jsonElement.getAsJsonObject();
        //
        String componentType = component.get("type").getAsString();
        if(componentType == null){
            LOGGER.error("componentType is required!please check the component string:{}", component.toString());
            throw new JsonParseException("componentType is required!please check the component");
        }
        Class<? extends IGuidebookComponent> clazz = getComponentByTypeName(componentType);
        return GSON.fromJson(jsonElement, clazz);
    }

    /**序列化*/
    @Override
    public JsonElement serialize(IGuidebookComponent iGuidebookComponent, Type type, JsonSerializationContext jsonSerializationContext) {
        throw new AssertionError("iGuidebook serialize cannot be called,please check the code");
    }

    public Class<? extends IGuidebookComponent> getComponentByTypeName(String type){
        return GuidebookHelper.GUIDEBOOK_COMPONENTS.get(type);
    }
}
