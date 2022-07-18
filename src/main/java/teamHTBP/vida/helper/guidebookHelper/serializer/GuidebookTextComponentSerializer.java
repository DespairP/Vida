package teamHTBP.vida.helper.guidebookHelper.serializer;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.helper.guidebookHelper.components.TextGuidebookComponent;

import java.lang.reflect.Type;

public class GuidebookTextComponentSerializer implements JsonDeserializer<TextGuidebookComponent> {
    /**LOGGER*/
    private static final Logger LOGGER = LogManager.getLogger();
    /**GSON*/
    private static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    @Override
    public TextGuidebookComponent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        //optional field:autoMaxLength
        //这会导致无限递归：jsonDeserializationContext.deserialize(),因为会使用上一个context的GSON
        TextGuidebookComponent component = GSON.fromJson(jsonElement, type);
        JsonObject object = jsonElement.getAsJsonObject();
        //如果有autoMaxLength字段,组件最大长度设为GUI最大长度
        if(object.has("autoMaxLength")){
            component.maxLength = TextGuidebookComponent.MAX_GUI_LENGTH;
        }
        return component;
    }

}
