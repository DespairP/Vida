package teamHTBP.vida.utils.json.serializer;

import com.google.gson.*;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Type;

/**
 * @author DustW
 **/
public class ItemSerializer implements BaseSerializer<Item> {
    @Override
    public Item deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(json.getAsString()));
    }

    @Override
    public JsonElement serialize(Item src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getRegistryName().toString());
    }
}
