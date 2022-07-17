package teamHTBP.vida.recipe.utils.json.serializer;

import com.google.gson.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Type;
import java.util.Optional;

/**
 * @author DustW
 **/
public class ItemStackSerializer implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {
    @Override
    public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement resourceKey = json.getAsJsonObject().get("item");
        if(resourceKey == null || resourceKey.isJsonNull()){
            return ItemStack.EMPTY;
        }
        JsonElement count = json.getAsJsonObject().get("count");
        if(count == null || count.isJsonNull()){
            count = new JsonPrimitive(1);
        }
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(resourceKey.getAsString()));

        return new ItemStack(
                Optional.ofNullable(item).orElse(Items.AIR),
                count.getAsInt()
        );
    }

    @Override
    public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty("item", src.getItem().getRegistryName().toString());
        result.addProperty("count", src.getCount());
        return result;
    }
}
