package teamHTBP.vida.recipe.recipes.recipe;

import com.google.gson.annotations.Expose;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import teamHTBP.vida.recipe.recipes.base.BaseRecipe;
import teamHTBP.vida.recipe.recipes.object.RecipeFluidStack;
import teamHTBP.vida.recipe.recipes.register.RecipeSerializers;
import teamHTBP.vida.recipe.recipes.register.RecipeTypes;

import java.util.List;
import java.util.Objects;

/**
 * @author DustW
 **/
public class FermenterFluidRecipe extends BaseRecipe<FermenterFluidRecipe> {
    @Expose
    public Ingredient input;
    @Expose
    public ItemStack output;
    @Expose
    public RecipeFluidStack fluidStack;

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return getRecipeOutput();
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
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
                Objects.equals(this.fluidStack.fluidName, inputFluid.getFluid().getRegistryName().toString()) &&
                inputFluid.getAmount() > fluidStack.amount;
    }

    @Override
    public boolean matches(List<ItemStack> inputs) {
        return input.test(inputs.get(0));
    }
}
