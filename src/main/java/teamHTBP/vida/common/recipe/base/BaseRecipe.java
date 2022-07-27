package teamHTBP.vida.common.recipe.base;

import com.google.gson.annotations.Expose;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import teamHTBP.vida.helper.ContainerHelper;

import java.util.List;

/**
 * 普通实现合成表方案
 *
 * SELF 继承了BaseRecipe的子类，也就是自己本身
 *
 * @author DustW
 **/
public abstract class BaseRecipe<T extends Container> implements Recipe<T> {
    @Expose(deserialize = false, serialize = false)
    ResourceLocation id;
    @Expose
    public String type;

    public abstract boolean matches(List<ItemStack> inputs);

    public <C extends Container, R extends BaseRecipe<C>> R setID(ResourceLocation id) {
        this.id = id;
        return (R) this;
    }

    @Override
    public boolean matches(T pContainer, Level pLevel) {
        return matches(ContainerHelper.getItems(pContainer));
    }

    @Override
    public ItemStack assemble(T pContainer) {
        return getResultItem();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    private RecipeSerializer<?> serializer;

    public <C extends Container, R extends BaseRecipe<C>> R setSerializer(RecipeSerializer<?> serializer) {
        this.serializer = serializer;
        return (R) this;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
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
