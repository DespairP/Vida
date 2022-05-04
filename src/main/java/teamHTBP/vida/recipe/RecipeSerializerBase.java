package teamHTBP.vida.recipe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import teamHTBP.vida.recipe.altar.AltarRecipe;
import teamHTBP.vida.recipe.recipeobj.RecipeObject;
import teamHTBP.vida.recipe.recipeobj.RecipeObjectType;

import javax.annotation.Nullable;

/**
* 合成表序列化抽象类,一般用于网络数据流传输
*
* @author DustW
* */
public abstract class RecipeSerializerBase<A extends IRecipe<?>> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<A> {
    /**gson实例，用于json的解析*/
    Gson gson = new GsonBuilder().create();

    /**合成表读取流*/
    @Nullable
    @Override
    public A read(ResourceLocation recipeId, PacketBuffer buffer) {
        JsonObject json = gson.fromJson(buffer.readString(), JsonElement.class).getAsJsonObject();
        return read(recipeId, json);
    }

    /**合成表写入流*/
    @Override
    public void write(PacketBuffer buffer, A recipe) {
        JsonObject json = new JsonObject();
        write(json, recipe);
        buffer.writeString(json.toString());
    }

    public JsonObject getJson(RecipeObject<?> obj) {
        JsonObject result = new JsonObject();
        result.add("item", obj.toJson());
        result.addProperty("type", obj.getType().getRegistryName().toString());
        return result;
    }

    public Object fromJson(JsonObject obj) {
        String type = obj.get("type").getAsString();
        return RecipeObjectType.of(new ResourceLocation(type)).apply(null).fromJson(obj.get("item").getAsJsonObject());
    }

    public abstract void write(JsonObject json, A recipe);
}
