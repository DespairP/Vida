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
 * Vida合成表基类
 * RecipesBase -> ICraftingRecipe -> IRecipe
 * A 合成表类，可以是继承此类的子类
 * T 使用此合成表的TileEntitiy
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

    /**获取合成结果*/
    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {
        return getRecipeOutput();
    }


    /**有关Advancement的逻辑*/
    @Nullable
    @Override
    public JsonObject getAdvancementJson() {
        return null;
    }

    /**有关Advancement的逻辑,getAdvancementJson不返回null时，此方法也不应该返回null*/
    @Nullable
    @Override
    public ResourceLocation getAdvancementID() {
        return null;
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        return false;
    }

    /**
     * 用于获取tileEntity的合成表
     * @return 该合成表是否是TileEntity对应的合成表
     * */
    public abstract boolean matches(TileEntity tileEntity);
}
