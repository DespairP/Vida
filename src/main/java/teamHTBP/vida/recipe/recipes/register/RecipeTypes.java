package teamHTBP.vida.recipe.recipes.register;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.recipe.recipes.base.BaseRecipe;
import teamHTBP.vida.recipe.recipes.recipe.FermenterFluidRecipe;

/**
 * @author DustW
 **/
public class RecipeTypes {
    public static final IRecipeType<FermenterFluidRecipe> FERMENTER_FLUID = register("fermenter_fluid");

    static <T extends IRecipe<?>> IRecipeType<T> register(final String key) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(key), new IRecipeType<T>() {
            @Override
            public String toString() {
                return key;
            }
        });
    }
}
