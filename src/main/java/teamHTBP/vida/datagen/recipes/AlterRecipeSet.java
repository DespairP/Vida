package teamHTBP.vida.datagen.recipes;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import teamHTBP.vida.Vida;
import teamHTBP.vida.element.EnumElements;
import teamHTBP.vida.item.VidaItemRegistry;
import teamHTBP.vida.recipe.RecipeSerializers;
import teamHTBP.vida.recipe.recipe.AltarRecipe;

/**
 * @author DustW
 */
public class AlterRecipeSet extends VidaRecipeSet {
    @Override
    protected void addRecipes() {
        addRecipe("gold_1", AltarRecipe.builder()
                .element(EnumElements.GOLD)
                .core(VidaItemRegistry.ELEMENTCORE_GOLD.get())
                .others(Items.GOLD_INGOT, Items.DIAMOND, Items.REDSTONE, Items.IRON_INGOT)
                .build());

        addRecipe("gold_2", AltarRecipe.builder()
                .element(EnumElements.GOLD)
                .core(VidaItemRegistry.ELEMENTCORE_WOOD.get())
                .others(Items.GOLD_INGOT, Items.DIAMOND, Items.REDSTONE, Items.IRON_INGOT)
                .build());
    }

    void addRecipe(String name, AltarRecipe recipe) {
        recipe.type = RecipeSerializers.ALTAR.get().getRegistryName().toString();
        addRecipe(new ResourceLocation(Vida.MOD_ID, name), baseRecipe(recipe), "alter");
    }
}
