package teamHTBP.vida.common.recipe;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.recipe.base.BaseRecipe;
import teamHTBP.vida.common.recipe.recipe.AltarRecipe;
import teamHTBP.vida.common.recipe.recipe.FermenterFluidRecipe;

/**
 *
 * @author DustW
 **/
public class VidaRecipeTypeLoader {
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registry.RECIPE_TYPE.key(), Vida.MOD_ID);

    public static final RegistryObject<RecipeType<FermenterFluidRecipe>> FERMENTER_FLUID = register("fermenter_fluid");
    public static final RegistryObject<RecipeType<AltarRecipe>> ALTAR = register("altar");

    private static <C extends Container, TYPE extends BaseRecipe<C>> RegistryObject<RecipeType<TYPE>> register(String name) {
        return TYPES.register(name, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return new ResourceLocation(Vida.MOD_ID, name).toString();
            }
        });
    }
}