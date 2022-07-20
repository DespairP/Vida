package teamHTBP.vida.recipe;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidStack;
import teamHTBP.vida.Vida;
import teamHTBP.vida.blockentity.TileEntityElementCoreAltar;
import teamHTBP.vida.recipe.base.BaseRecipe;
import teamHTBP.vida.recipe.recipe.AltarRecipe;
import teamHTBP.vida.recipe.recipe.FermenterFluidRecipe;

import java.util.List;

/**
 * @author DustW
 **/
public class RecipeManager {
    public static final String MOD_ID = Vida.MOD_ID;

    /**获取BaseRecipe相关的合成*/
    public static <C extends Container, T extends BaseRecipe<C>> List<T> getBaseRecipe(Level level, RecipeType<T> type) {
        return level.getRecipeManager().getAllRecipesFor(type);
    }

    @Deprecated
    public static FermenterFluidRecipe getFermenterFuildRecipe(Level level, ItemStack itemStack, FluidStack inputFuild) {
        return getBaseRecipe(level, RecipeTypes.FERMENTER_FLUID).stream()
                .filter(s -> s.matches(itemStack, inputFuild)).findFirst().orElse(null);
    }

    /**获取合成表*/
    public static AltarRecipe getAltarRecipe(Level level, TileEntityElementCoreAltar altar){
        level.getRecipeManager().getAllRecipesFor(RecipeTypes.ALTAR);
        return level.getRecipeManager().getAllRecipesFor(RecipeTypes.ALTAR).stream().filter(
                r -> r.matches(altar)
        ).findFirst().orElse(null);
    }

    public static void register(IEventBus bus) {
        RecipeSerializers.register(bus);
    }
}
