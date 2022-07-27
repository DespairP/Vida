package teamHTBP.vida.datagen.recipes;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import teamHTBP.vida.Vida;
import teamHTBP.vida.core.element.EnumElements;
import teamHTBP.vida.common.item.VidaItemLoader;
import teamHTBP.vida.common.recipe.VidaRecipeSerializerLoader;
import teamHTBP.vida.common.recipe.recipe.AltarRecipe;

/**
 * @author DustW
 */
public class AlterRecipeSet extends VidaRecipeSet {
    @Override
    protected void addRecipes() {
        addRecipe("gold_1", AltarRecipe.builder()
                .element(EnumElements.GOLD)
                .core(VidaItemLoader.ELEMENTCORE_GOLD.get())
                .others(Items.GOLD_INGOT, Items.DIAMOND, Items.REDSTONE, Items.IRON_INGOT)
                .build());

        addRecipe("gold_2", AltarRecipe.builder()
                .element(EnumElements.GOLD)
                .core(VidaItemLoader.ELEMENTCORE_WOOD.get())
                .others(Items.GOLD_INGOT, Items.DIAMOND, Items.REDSTONE, Items.IRON_INGOT)
                .build());
    }

    void addRecipe(String name, AltarRecipe recipe) {
        recipe.type = VidaRecipeSerializerLoader.ALTAR.get().getRegistryName().toString();
        addRecipe(new ResourceLocation(Vida.MOD_ID, name), baseRecipe(recipe), "alter");
    }
}
