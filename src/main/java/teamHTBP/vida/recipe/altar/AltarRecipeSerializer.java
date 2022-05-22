package teamHTBP.vida.recipe.altar;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.helper.elementHelper.IElement;
import teamHTBP.vida.recipe.utils.base.BaseTileEntityRecipeSerializer;

import java.util.Objects;

/**
 * @author DustW
 */
public class AltarRecipeSerializer extends BaseTileEntityRecipeSerializer<AltarRecipe> {
    @Override
    public AltarRecipe read(ResourceLocation recipeId, JsonObject json) {
        IElement element = EnumElements.valueOf(json.get("element").getAsString());
        Item core = ForgeRegistries.ITEMS.getValue(new ResourceLocation(json.get("core").getAsString()));
        Item o1 =   ForgeRegistries.ITEMS.getValue(new ResourceLocation(json.get("other").getAsJsonObject().get("item1").getAsString()));
        Item o2 =   ForgeRegistries.ITEMS.getValue(new ResourceLocation(json.get("other").getAsJsonObject().get("item2").getAsString()));
        Item o3 =   ForgeRegistries.ITEMS.getValue(new ResourceLocation(json.get("other").getAsJsonObject().get("item3").getAsString()));
        Item o4 =   ForgeRegistries.ITEMS.getValue(new ResourceLocation(json.get("other").getAsJsonObject().get("item4").getAsString()));
        return AltarRecipe.builder().others(o1,o2,o3,o4).result(ItemStack.EMPTY).element(element).core(core).build();
    }

    @Override
    public void write(JsonObject json, AltarRecipe recipe) {
        json.addProperty("element", recipe.element.getRegistryName().toString());
        JsonObject other = new JsonObject();
        for(int i = 0 ; i < 4 ; i ++) {
            other.addProperty("item" + (i + 1), Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(recipe.other.get(i).getItem())).toString());
        }
        json.add("other", other);
        json.addProperty("core", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(recipe.core.getItem())).toString());
        json.addProperty("element",recipe.element.toString());
        //json.add("result", Ingredient.fromStacks(recipe.result).serialize());
    }
}
