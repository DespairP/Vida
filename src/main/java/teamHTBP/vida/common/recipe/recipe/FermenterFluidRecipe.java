package teamHTBP.vida.common.recipe.recipe;

import com.google.gson.annotations.Expose;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import teamHTBP.vida.common.recipe.RecipeSerializers;
import teamHTBP.vida.common.recipe.RecipeTypes;
import teamHTBP.vida.common.recipe.base.BaseRecipe;

import java.util.List;

/**
 * @author DustW
 **/
public class FermenterFluidRecipe extends BaseRecipe<Inventory> {
    @Expose
    public Ingredient input;
    @Expose
    public ItemStack output;
    @Expose
    public FluidStack fluidStack;

    @Override
    public boolean matches(Inventory inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(Inventory inv) {
        return output.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output.copy();
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeSerializers.PLATE_RECIPE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return RecipeTypes.FERMENTER_FLUID;
    }

    public boolean matches(ItemStack input, FluidStack inputFluid) {
        return
                this.input.test(input) &&
                this.fluidStack.getFluid() == inputFluid.getFluid() &&
                inputFluid.getAmount() > fluidStack.getAmount();
    }

    @Override
    public boolean matches(List<ItemStack> inputs) {
        return input.test(inputs.get(0));
    }
}
