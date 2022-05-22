package teamHTBP.vida.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.IForgeRegistryEntry;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.Vida;
import teamHTBP.vida.recipe.altar.AltarRecipe;
import teamHTBP.vida.recipe.utils.base.BaseRecipe;
import teamHTBP.vida.recipe.utils.base.BaseTileEntityRecipes;
import teamHTBP.vida.recipe.utils.recipe.FermenterFluidRecipe;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author DustW
 **/
public class RecipeManager {
    public static final String MOD_ID = Vida.MOD_ID;

    /**获取BaseRecipe相关的合成*/
    public static <T extends BaseRecipe<T>> List<T> getBaseRecipe(World level, IRecipeType<T> type) {
        return level.getRecipeManager().getRecipes().stream().map(
                r -> (r.getType() == type ? (T)r : null)
        ).filter(Objects::nonNull).collect(Collectors.toList());
    }

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
