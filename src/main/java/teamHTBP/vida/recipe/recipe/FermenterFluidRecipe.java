package teamHTBP.vida.recipe.recipe;

import com.google.gson.annotations.Expose;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fluids.FluidStack;
import teamHTBP.vida.recipe.RecipeSerializers;
import teamHTBP.vida.recipe.RecipeTypes;
import teamHTBP.vida.recipe.base.BaseRecipe;

import java.util.List;

/**
 * @author DustW
 **/
public class FermenterFluidRecipe extends BaseRecipe<Container> {
    @Expose
    public Ingredient input;
    @Expose
    public ItemStack output;
    @Expose
    public FluidStack fluidStack;

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.PLATE_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypes.FERMENTER_FLUID.get();
    }

    public boolean matches(ItemStack input, FluidStack inputFluid) {
        return
                this.input.test(input) &&
                this.fluidStack.getFluid() == inputFluid.getFluid() &&
                inputFluid.getAmount() > fluidStack.getAmount();
    }

    @Override
    public boolean matches(List<ItemStack> inputs) {
        return false;
    }
}
