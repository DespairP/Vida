package teamHTBP.vida.recipe;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;

public class RecipeLoader {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Vida.modId);
    public static final RegistryObject<OreReactionMachineRecipe.Serializer> OREREACTION_RECIPE = RECIPES.register("orereaction_recipe", OreReactionMachineRecipe.Serializer::new);

}
