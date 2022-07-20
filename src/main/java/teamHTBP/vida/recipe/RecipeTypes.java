package teamHTBP.vida.recipe;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import teamHTBP.vida.Vida;
import teamHTBP.vida.recipe.recipe.AltarRecipe;
import teamHTBP.vida.recipe.recipe.FermenterFluidRecipe;

/**
 *
 * @author DustW
 **/
public class RecipeTypes {
    public static final RecipeType<FermenterFluidRecipe> FERMENTER_FLUID = register("fermenter_fluid");
    public static final RecipeType<AltarRecipe> ALTAR = register("altar");

    /**注册RecipeTypes*/
    static <T extends Recipe<?>> RecipeType<T> register(final String key) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Vida.MOD_ID ,key), new RecipeType<T>() {
            @Override
            public String toString() {
                return key;
            }
        });
    }
}
