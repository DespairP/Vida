package teamHTBP.vida.utils.json.serializer;

import com.google.gson.*;
import teamHTBP.vida.helper.elementHelper.ElementManager;
import teamHTBP.vida.helper.elementHelper.IElement;

import java.lang.reflect.Type;

/**
 * @author DustW
 **/
public class IElementSerializer implements BaseSerializer<IElement> {
    @Override
    public IElement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return ElementManager.get(json.getAsString());
    }

    @Override
    public JsonElement serialize(IElement src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getElementName().toString());
    }
}
