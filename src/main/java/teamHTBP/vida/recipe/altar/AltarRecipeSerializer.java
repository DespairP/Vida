package teamHTBP.vida.recipe.altar;

import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;
import teamHTBP.vida.helper.element.ElementHelper;
import teamHTBP.vida.helper.element.EnumElements;
import teamHTBP.vida.helper.element.IElement;
import teamHTBP.vida.recipe.RecipeSerializerBase;
import teamHTBP.vida.recipe.recipeobj.RecipeObject;

import javax.annotation.Nullable;

/**
 * @author DustW
 */
public class AltarRecipeSerializer extends RecipeSerializerBase<AltarRecipe> {
    @Override
    public AltarRecipe read(ResourceLocation recipeId, JsonObject json) {
        //TODO 可能得改
        IElement element = EnumElements.valueOf(json.get("element").getAsString());
        Object core = fromJson(json.get("core").getAsJsonObject());
        Object o1 = fromJson(json.get("other").getAsJsonObject().get("item1").getAsJsonObject());
        Object o2 = fromJson(json.get("other").getAsJsonObject().get("item2").getAsJsonObject());
        Object o3 = fromJson(json.get("other").getAsJsonObject().get("item3").getAsJsonObject());
        Object o4 = fromJson(json.get("other").getAsJsonObject().get("item4").getAsJsonObject());
        ItemStack result = Ingredient.deserializeItemList(json.get("result").getAsJsonObject()).getStacks().iterator().next();
        return AltarRecipe.builder().others(o1,o2,o3,o4).result(result).element(element).core(core).build();
    }

    @Override
    public void write(JsonObject json, AltarRecipe recipe) {
        json.addProperty("element", recipe.element.getRegistryName().toString());
        JsonObject other = new JsonObject();
        other.add("item1", getJson(recipe.other.get(0)));
        other.add("item2", getJson(recipe.other.get(1)));
        other.add("item3", getJson(recipe.other.get(2)));
        other.add("item4", getJson(recipe.other.get(3)));
        json.add("other", other);
        json.add("core", getJson(recipe.core));
        json.add("result", Ingredient.fromStacks(recipe.result).serialize());
    }
}
