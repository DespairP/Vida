package teamHTBP.vida.recipe.utils.base;

import com.google.gson.JsonObject;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;
import teamHTBP.vida.recipe.utils.json.JsonUtils;

import javax.annotation.Nullable;

/**
 * 普通合成表序列化
 *
 * RECIPE 实现BaseRecipe 的子类
 *
 * @author DustW
 * @see BaseRecipe
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

    /**读取Json文件*/
    @Override
    public RECIPE read(ResourceLocation recipeId, JsonObject json) {
        return JsonUtils.INSTANCE.normal.fromJson(json, recipeClass).setID(recipeId);
    }

    /**写入Json文件*/
    @Nullable
    @Override
    public RECIPE read(ResourceLocation recipeId, PacketBuffer buffer) {
        return JsonUtils.INSTANCE.normal.fromJson(buffer.readString(32767), recipeClass);
    }

    /**流写入Json文件*/
    @Override
    public void write(PacketBuffer buffer, RECIPE recipe) {
        buffer.writeString(JsonUtils.INSTANCE.normal.toJson(recipe));
    }
}
