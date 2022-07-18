package teamHTBP.vida.recipe;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import teamHTBP.vida.Vida;
import teamHTBP.vida.recipe.recipe.AltarRecipe;
import teamHTBP.vida.recipe.recipe.FermenterFluidRecipe;

/**
 *
 * @author DustW
 **/
public class RecipeTypes {
    public static final IRecipeType<FermenterFluidRecipe> FERMENTER_FLUID = register("fermenter_fluid");
    public static final IRecipeType<AltarRecipe> ALTAR = register("altar");

    /**注册RecipeTypes*/
    static <T extends IRecipe<?>> IRecipeType<T> register(final String key) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Vida.MOD_ID ,key), new IRecipeType<T>() {
            @Override
            public String toString() {
                return key;
            }
        });
    }
}
