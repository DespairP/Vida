package teamHTBP.vida.recipe.altar;

import com.google.gson.JsonObject;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.helper.element.IElement;
import teamHTBP.vida.recipe.ModRecipeSerializers;
import teamHTBP.vida.recipe.RecipesBase;
import teamHTBP.vida.recipe.recipeobj.RecipeItem;
import teamHTBP.vida.recipe.recipeobj.RecipeObject;
import teamHTBP.vida.recipe.recipeobj.RecipeObjectType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author DustW
 */
public class AltarRecipe extends RecipesBase<AltarRecipe, TileEntityElementCoreAltar> {
    public Core core;
    public Other other;

    public IElement element;
    public ItemStack result;

    public AltarRecipe(IElement element, ItemStack result, Core core, Other other) {
        this.core = core;
        this.other = other;
        this.element = element;
        this.result = result;
    }

    @Override
    public void serialize(JsonObject json) {
        ModRecipeSerializers.ALTAR.get().write(json, this);
    }

    @Override
    public boolean matches(TileEntityElementCoreAltar altar) {
        if (core.recipeObject.matches(altar.coreItem)) {
            ArrayList<Integer> markList = new ArrayList<>();

            a : for (int i = 0; i < other.content.size(); i++) {
                for (int i1 = 0; i1 < altar.altarItem.length; i1++) {
                    if (other.content.get(i).matches(altar.altarItem[i1]) && !markList.contains(i1)) {
                        markList.add(i1);
                        continue a;
                    }
                }
            }

            return markList.size() == altar.altarItem.length;
        }

        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return result.copy();
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.ALTAR.get();
    }

    public static class Core {
        public final RecipeObject<?> recipeObject;

        public Core(Object core) {
            this.recipeObject = RecipeObjectType.of(core);
        }
    }

    public static class Other {
        public final List<RecipeObject<?>> content;

        public Other(Object o1, Object o2, Object o3, Object o4) {
            content = Arrays.asList(
                    RecipeObjectType.of(o1),
                    RecipeObjectType.of(o2),
                    RecipeObjectType.of(o3),
                    RecipeObjectType.of(o4)
            );
        }
    }
}
