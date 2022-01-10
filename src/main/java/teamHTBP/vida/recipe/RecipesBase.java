package teamHTBP.vida.recipe;

import com.google.gson.JsonObject;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.recipe.altar.AltarRecipe;

import javax.annotation.Nullable;

/**
 * @author DustW
 */
public abstract class RecipesBase<A extends IForgeRegistryEntry<A>, T extends TileEntity> extends ForgeRegistryEntry<A> implements ICraftingRecipe, IFinishedRecipe {
    @Override
    public ResourceLocation getID() {
        return getRegistryName();
    }

    @Override
    public ResourceLocation getId() {
        return getID();
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {
        return getRecipeOutput();
    }

    @Nullable
    @Override
    public JsonObject getAdvancementJson() {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getAdvancementID() {
        return null;
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        return false;
    }

    public abstract boolean matches(T altar);
}
