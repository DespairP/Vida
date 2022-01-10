package teamHTBP.vida.recipe.recipeobj;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RecipeObjectType<O, T extends RecipeObject<O>> extends ForgeRegistryEntry<RecipeObjectType<O, T>> {
    Function<O, T> recipeObj;

    private RecipeObjectType(Function<O, T> recipeObj) {
        this.recipeObj = recipeObj;
    }

    public static <Z> Function<Z, ? extends RecipeObject<Z>> of(ResourceLocation name) {
        RecipeObjectType<?, ? extends RecipeObject<?>> type = RecipeObjectTypeHandler.TYPES.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue().getRegistryName(), name)).collect(Collectors.toList()).get(0).getValue();

        return (Function<Z, ? extends RecipeObject<Z>>) type.recipeObj;
    }

    public static <Z> RecipeObject<Z> of(Z obj) {
        List<Map.Entry<Class<?>, RecipeObjectType<?, ? extends RecipeObject<?>>>> a = RecipeObjectTypeHandler.TYPES.entrySet().stream()
                .filter(entry -> entry.getKey().isInstance(obj))
                .collect(Collectors.toList());
        RecipeObjectType<?, ? extends RecipeObject<?>> type = a
                .get(0).getValue();

        return ((Function<Z, ? extends RecipeObject<Z>>) type.recipeObj).apply(obj);
    }

    public static <X, Y extends RecipeObject<X>> RecipeObjectType<X, Y> create(Function<X, Y> recipeObj) {
        return new RecipeObjectType<>(recipeObj);
    }
}
