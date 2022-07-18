package teamHTBP.vida.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidStack;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.Vida;
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
    public static <C extends IInventory, T extends BaseRecipe<C>> List<T> getBaseRecipe(World level, IRecipeType<T> type) {
        return level.getRecipeManager().getRecipesForType(type);
    }

    @Deprecated
    public static FermenterFluidRecipe getFermenterFuildRecipe(World level, ItemStack itemStack, FluidStack inputFuild) {
        return getBaseRecipe(level, RecipeTypes.FERMENTER_FLUID).stream()
                .filter(s -> s.matches(itemStack, inputFuild)).findFirst().orElse(null);
    }

    /**获取合成表*/
    public static AltarRecipe getAltarRecipe(World level, TileEntityElementCoreAltar altar){
        level.getRecipeManager().getRecipesForType(RecipeTypes.ALTAR);
        return level.getRecipeManager().getRecipesForType(RecipeTypes.ALTAR).stream().filter(
                r -> r.matches(altar)
        ).findFirst().orElse(null);
    }

    public static void register(IEventBus bus) {
        RecipeSerializers.register(bus);
    }
}
