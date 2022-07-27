package teamHTBP.vida.datagen.recipes;


import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.Vida;
import teamHTBP.vida.core.element.EnumElements;
import teamHTBP.vida.common.item.ItemLoader;
import teamHTBP.vida.common.recipe.RecipeSerializers;
import teamHTBP.vida.common.recipe.recipe.AltarRecipe;

/**
 * @author DustW
 */
public class AlterRecipes extends ModGenRecipes {
    @Override
    protected void addRecipes() {
        addRecipe("gold_1", AltarRecipe.builder()
                .element(EnumElements.GOLD)
                .core(ItemLoader.ELEMENTCORE_GOLD.get())
                .others(Items.GOLD_INGOT, Items.DIAMOND, Items.REDSTONE, Items.IRON_INGOT)
                .build());

        addRecipe("gold_2", AltarRecipe.builder()
                .element(EnumElements.GOLD)
                .core(ItemLoader.ELEMENTCORE_WOOD.get())
                .others(Items.GOLD_INGOT, Items.DIAMOND, Items.REDSTONE, Items.IRON_INGOT)
                .build());
    }

    void addRecipe(String name, AltarRecipe recipe) {
        recipe.type = RecipeSerializers.ALTAR.get().getRegistryName().toString();
        addRecipe(new ResourceLocation(Vida.MOD_ID, name), baseRecipe(recipe), "alter");
    }
}
