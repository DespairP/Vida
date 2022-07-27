package teamHTBP.vida.common.recipe.base;

import com.google.gson.annotations.Expose;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;

import java.util.List;

/**
 * 普通实现合成表方案
 *
 *
 * @author DustW
 **/
public abstract class BaseRecipe<T extends IInventory> implements IRecipe<T> {
    @Expose(deserialize = false, serialize = false)
    ResourceLocation id;
    @Expose
    public String type;

    public abstract boolean matches(List<ItemStack> inputs);

    public <C extends IInventory, R extends BaseRecipe<C>> R setID(ResourceLocation id) {
        this.id = id;
        return (R) this;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    private IRecipeSerializer<?> serializer;

    public <C extends IInventory, R extends BaseRecipe<C>> R setSerializer(IRecipeSerializer<?> serializer) {
        this.serializer = serializer;
        return (R) this;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return this.serializer;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BaseRecipe && ((BaseRecipe) obj).id.equals(id);
    }
}
