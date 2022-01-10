package teamHTBP.vida.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;
import teamHTBP.vida.recipe.recipeobj.RecipeObjectType;

/**
 * @author DustW
 */
public class RecipesManager {
    public static void init(IEventBus bus) {
        ModRecipeSerializers.register(bus);
    }

    /** 找合成表 */
    public static <R extends ICraftingRecipe> R find(World world, TileEntity tileEntity) {
        for (ICraftingRecipe recipe : world.getRecipeManager().getRecipesForType(IRecipeType.CRAFTING)) {
            if (recipe instanceof RecipesBase && ((RecipesBase) recipe).matches(tileEntity)) {
                return (R) recipe;
            }
        }

        return null;
    }
}
