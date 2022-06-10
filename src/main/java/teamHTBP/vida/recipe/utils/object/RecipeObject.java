package teamHTBP.vida.recipe.utils.object;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;

/**
 * RecipeObject 合成表元素基类 <br/>
 * T 元素基类 <br/>
 * [例子]：1个雪球是合成物品，1个雪球作为 {合成表的一格} 就是一个 {合成表元素} <br/>
 * @author DustW
 * */
@Deprecated
public abstract class RecipeObject<T> {
    /**合成表元素中的合成物品实例，可以是ItemStack，Item...*/
    T obj;

    public RecipeObject(T obj) {
        this.obj = obj;
    }


    public abstract Ingredient getIngredient();

    /**
     * ItemStack是否匹配当前元素
     * @param itemStack 将要匹配的ItemStack
     * @return 是否匹配
     * */
    public abstract boolean matches(ItemStack itemStack);

    /**
     * 将当前合成表元素的合成物品转为Json元素
     * @return 转换为JsonObject的合成表元素
     * */
    public abstract JsonObject toJson();

    /**
     * 将Json转为 合成表元素的合成物品的实例
     * @return 合成物品实例
     * */
    public abstract T fromJson(JsonObject json);

    /**
     * 将合成物品转为NBT
     * @return nbt
     * */
    public abstract CompoundNBT toNBT();

    /**
     * 从NBT转换为合成物品
     * @return 合成物品
     * */
    public abstract T fromNBT(CompoundNBT nbt);

    /**
     * 获取合成元素类型
     * @return 合成元素类型
     * */
    public abstract RecipeObjectType<T, ? extends RecipeObject<T>> getType();
}
