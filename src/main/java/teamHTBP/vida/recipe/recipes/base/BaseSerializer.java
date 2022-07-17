package teamHTBP.vida.recipe.recipes.base;

import com.google.gson.JsonObject;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;
import teamHTBP.vida.utils.json.JsonUtils;

import javax.annotation.Nullable;

/**
 * @author DustW
 **/
public class BaseSerializer<RECIPE extends BaseRecipe<RECIPE>>
        extends ForgeRegistryEntry<IRecipeSerializer<?>>
        implements IRecipeSerializer<RECIPE> {

    Class<RECIPE> recipeClass;

    public BaseSerializer(Class<RECIPE> recipeClass) {
        this.recipeClass = recipeClass;
    }

    public JsonObject toJson(RECIPE pRecipe) {
        return JsonUtils.INSTANCE.normal.toJsonTree(pRecipe).getAsJsonObject();
    }

    @Override
    public RECIPE read(ResourceLocation recipeId, JsonObject json) {
        return JsonUtils.INSTANCE.normal.fromJson(json, recipeClass).setID(recipeId);
    }

    @Nullable
    @Override
    public RECIPE read(ResourceLocation recipeId, PacketBuffer buffer) {
        return JsonUtils.INSTANCE.normal.fromJson(buffer.readString(32767), recipeClass);
    }

    @Override
    public void write(PacketBuffer buffer, RECIPE recipe) {
        buffer.writeString(JsonUtils.INSTANCE.normal.toJson(recipe));
    }
}
