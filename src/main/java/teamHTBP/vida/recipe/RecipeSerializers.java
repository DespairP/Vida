package teamHTBP.vida.recipe;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.recipe.base.BaseSerializer;
import teamHTBP.vida.recipe.recipe.AltarRecipe;
import teamHTBP.vida.recipe.recipe.FermenterFluidRecipe;

/**
 * @author DustW
 **/
public class RecipeSerializers {
    private static final DeferredRegister<IRecipeSerializer<?>> SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RecipeManager.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<?>> PLATE_RECIPE =
            SERIALIZER.register("fermenter", () -> new BaseSerializer<>(FermenterFluidRecipe.class));

    public static final RegistryObject<IRecipeSerializer<?>> ALTAR =
            SERIALIZER.register("altar", () -> new BaseSerializer<>(AltarRecipe.class));

    public static void register(IEventBus bus) {
        SERIALIZER.register(bus);
    }
}
