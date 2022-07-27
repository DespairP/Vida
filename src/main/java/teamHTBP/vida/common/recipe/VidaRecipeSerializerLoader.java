package teamHTBP.vida.common.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.recipe.base.BaseSerializer;
import teamHTBP.vida.common.recipe.recipe.AltarRecipe;
import teamHTBP.vida.common.recipe.recipe.FermenterFluidRecipe;

/**
 * @author DustW
 **/
public class VidaRecipeSerializerLoader {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Vida.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> PLATE_RECIPE =
            SERIALIZER.register("fermenter", () -> new BaseSerializer<>(FermenterFluidRecipe.class));

    public static final RegistryObject<RecipeSerializer<?>> ALTAR =
            SERIALIZER.register("altar", () -> new BaseSerializer<>(AltarRecipe.class));
}
