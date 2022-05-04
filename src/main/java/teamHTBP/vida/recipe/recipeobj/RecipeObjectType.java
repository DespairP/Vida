package teamHTBP.vida.recipe.recipeobj;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 合成表元素的类型 <br/>
 * O 合成物品类型 <br/>
 * T 合成元素类型 <br/>
 * [例子]: ItemStack 对应 RecipeItemStack <br/>
 * @author DustW
 * @see RecipeObjectTypeHandler
 * */
public class RecipeObjectType<O, T extends RecipeObject<O>> extends ForgeRegistryEntry<RecipeObjectType<O, T>> {
    Function<O, T> recipeObj; //函数(O)->T

    private RecipeObjectType(Function<O, T> recipeObj) {
        this.recipeObj = recipeObj;
    }


    public static <Z> Function<Z, ? extends RecipeObject<Z>> of(ResourceLocation name) {
        RecipeObjectType<?, ? extends RecipeObject<?>> type = RecipeObjectTypeHandler.TYPES.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue().getRegistryName(), name)).collect(Collectors.toList()).get(0).getValue();

        return (Function<Z, ? extends RecipeObject<Z>>) type.recipeObj;
    }

    /**
     * 将Object转换成RecipeObject元素
     * 传入ItemStack会得到RecipeItemStack
     *
     * @param obj 将要转换的实例，可以是ItemStack、Item、Tag、Block 见：{@link RecipeObjectTypeHandler#TYPES}
     * */
    public static <Z> RecipeObject<Z> of(Z obj) {
        List<Map.Entry<Class<?>, RecipeObjectType<?, ? extends RecipeObject<?>>>> a = RecipeObjectTypeHandler.TYPES.entrySet().stream()
                .filter(entry -> entry.getKey().isInstance(obj))
                .collect(Collectors.toList());
        RecipeObjectType<?, ? extends RecipeObject<?>> type = a
                .get(0).getValue();

        return ((Function<Z, ? extends RecipeObject<Z>>) type.recipeObj).apply(obj);
    }

    /**
     * 建造方法 <br/>
     * 相当于new RecipeObjectType((o)->T);<br/>
     * X 相对于 O ：合成物品类型 <br/>
     * Y 相对于 T ： 合成元素类型 <br/>
     * */
    public static <X, Y extends RecipeObject<X>> RecipeObjectType<X, Y> create(Function<X, Y> recipeObj) {
        return new RecipeObjectType<>(recipeObj);
    }
}
