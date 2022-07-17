package teamHTBP.vida.utils.json.serializer;

import com.google.gson.*;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.helper.element.ElementManager;
import teamHTBP.vida.helper.element.IElement;

import java.lang.reflect.Type;

/**
 * @author DustW
 **/
public class IElementSerializer implements BaseSerializer<IElement> {
    @Override
    public IElement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return ElementManager.ELEMENTS.get(new ResourceLocation(json.getAsString()));
    }

    @Override
    public JsonElement serialize(IElement src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getElementName().toString());
    }
}
