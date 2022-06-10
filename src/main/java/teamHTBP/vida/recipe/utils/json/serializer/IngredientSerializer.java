package teamHTBP.vida.recipe.utils.json.serializer;

import com.google.gson.*;
import net.minecraft.item.crafting.Ingredient;

import java.lang.reflect.Type;

/**
 * @author DustW
 **/
public class IngredientSerializer implements JsonSerializer<Ingredient>, JsonDeserializer<Ingredient> {
    @Override
    public Ingredient deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Ingredient.deserialize(json);
    }

    @Override
    public JsonElement serialize(Ingredient src, Type typeOfSrc, JsonSerializationContext context) {
        return src.serialize();
    }
}
