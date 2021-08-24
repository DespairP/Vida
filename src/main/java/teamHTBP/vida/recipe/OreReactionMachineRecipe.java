package teamHTBP.vida.recipe;

import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Random;

public class OreReactionMachineRecipe implements IRecipe<IInventory> {

    public static final Serializer SERIALIZER = new Serializer();
    public static final IRecipeType<OreReactionMachineRecipe> RECIPE_TYPE = new IRecipeType<OreReactionMachineRecipe>() {

    };
    private final ResourceLocation id;
    protected Ingredient ingredient;
    private ItemStack output;
    private int maxOutPut;

    public OreReactionMachineRecipe(ResourceLocation id) {
        this.id = id;

    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return this.ingredient.test(inv.getStackInSlot(0));
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        ItemStack stack = this.output.copy();
        stack.setCount(new Random().nextInt(this.maxOutPut) + 1);
        return stack;
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return RECIPE_TYPE;
    }

    public int getMaxOutPut() {
        return this.maxOutPut;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<OreReactionMachineRecipe> {


        @Override
        public OreReactionMachineRecipe read(ResourceLocation recipeId, JsonObject json) {
            OreReactionMachineRecipe recipe = new OreReactionMachineRecipe(recipeId);
            recipe.output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            recipe.maxOutPut = JSONUtils.getInt(json, "maxOutput", 1);
            recipe.ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "ingredient"));
            return recipe;
        }

        @Nullable
        @Override
        public OreReactionMachineRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            OreReactionMachineRecipe recipe = new OreReactionMachineRecipe(recipeId);
            recipe.output = buffer.readItemStack();
            recipe.maxOutPut = buffer.readInt();
            recipe.ingredient = Ingredient.read(buffer);
            return recipe;
        }

        @Override
        public void write(PacketBuffer buffer, OreReactionMachineRecipe recipe) {
            buffer.writeItemStack(recipe.output);
            buffer.writeInt(recipe.maxOutPut);
            recipe.ingredient.write(buffer);
        }
    }
}
