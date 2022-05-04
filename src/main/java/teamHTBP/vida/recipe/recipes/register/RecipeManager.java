package teamHTBP.vida.recipe.recipes.register;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidStack;
import teamHTBP.vida.Vida;
import teamHTBP.vida.recipe.recipes.base.BaseRecipe;
import teamHTBP.vida.recipe.recipes.recipe.FermenterFluidRecipe;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author DustW
 **/
public class RecipeManager {
    public static final String MOD_ID = Vida.MOD_ID;

    public static <T extends BaseRecipe<T>> List<T> getRecipe(World level, IRecipeType<T> type) {
        return level.getRecipeManager().getRecipes().stream().map(r -> {
            if (r.getType() == type) {
                return (T) r;
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static FermenterFluidRecipe getFermenterFuildRecipe(World level, ItemStack itemStack, FluidStack inputFuild) {
        return getRecipe(level, RecipeTypes.FERMENTER_FLUID).stream()
                .filter(s -> s.matches(itemStack, inputFuild)).findFirst().orElse(null);
    }

    public static void register(IEventBus bus) {
        RecipeSerializers.register(bus);
    }
}
