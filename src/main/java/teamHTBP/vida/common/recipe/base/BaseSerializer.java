package teamHTBP.vida.common.recipe.base;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistryEntry;
import teamHTBP.vida.helper.json.JsonUtils;

import javax.annotation.Nullable;

/**
 * 普通合成表序列化
 *
 * RECIPE 实现BaseRecipe 的子类
 *
 * @author DustW
 * @see BaseRecipe
 **/
public class BaseSerializer<C extends Container, RECIPE extends BaseRecipe<C>>
        extends ForgeRegistryEntry<RecipeSerializer<?>>
        implements RecipeSerializer<RECIPE> {

    Class<RECIPE> recipeClass;

    public BaseSerializer(Class<RECIPE> recipeClass) {
        this.recipeClass = recipeClass;
    }

    public JsonObject toJson(RECIPE pRecipe) {
        return JsonUtils.INSTANCE.normal.toJsonTree(pRecipe).getAsJsonObject();
    }

    /**读取Json文件*/
    @Override
    public RECIPE fromJson(ResourceLocation recipeId, JsonObject json) {
        return JsonUtils.INSTANCE.normal.fromJson(json, recipeClass).setID(recipeId).setSerializer(this);
    }

    /**写入Json文件*/
    @Nullable
    @Override
    public RECIPE fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        return JsonUtils.INSTANCE.normal.fromJson(buffer.readUtf(32767), recipeClass).setID(recipeId).setSerializer(this);
    }

    /**流写入Json文件*/
    @Override
    public void toNetwork(FriendlyByteBuf buffer, RECIPE recipe) {
        buffer.writeUtf(JsonUtils.INSTANCE.normal.toJson(recipe));
    }
}
