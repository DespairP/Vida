package teamHTBP.vida.recipe;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.recipe.RecipeManager;
import teamHTBP.vida.recipe.altar.AltarRecipe;
import teamHTBP.vida.recipe.altar.AltarRecipeSerializer;
import teamHTBP.vida.recipe.utils.base.BaseSerializer;
import teamHTBP.vida.recipe.utils.base.BaseTileEntityRecipeSerializer;
import teamHTBP.vida.recipe.utils.recipe.FermenterFluidRecipe;

/**
 * @author DustW
 **/
public class RecipeSerializers {
    private static final DeferredRegister<IRecipeSerializer<?>> SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RecipeManager.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<?>> PLATE_RECIPE =
            SERIALIZER.register("fermenter", () -> new BaseSerializer<>(FermenterFluidRecipe.class));

    public static final RegistryObject<BaseTileEntityRecipeSerializer<AltarRecipe>> ALTAR =
            SERIALIZER.register("altar", AltarRecipeSerializer::new);

    public static void register(IEventBus bus) {
        SERIALIZER.register(bus);
    }
}
