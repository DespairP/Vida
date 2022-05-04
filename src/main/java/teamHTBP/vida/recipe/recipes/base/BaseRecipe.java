package teamHTBP.vida.recipe.recipes.base;

import com.google.gson.annotations.Expose;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

import java.util.List;

/**
 * @author DustW
 **/
public abstract class BaseRecipe<SELF extends BaseRecipe<SELF>> implements IRecipe<IInventory> {
    @Expose(deserialize = false, serialize = false)
    ResourceLocation id;
    @Expose
    public String type;

    public abstract boolean matches(List<ItemStack> inputs);

    public SELF setID(ResourceLocation id) {
        this.id = id;
        return self();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    private SELF self() {
        return (SELF) this;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BaseRecipe && ((BaseRecipe) obj).id.equals(id);
    }
}
