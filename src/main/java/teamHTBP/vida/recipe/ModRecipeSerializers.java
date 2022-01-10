package teamHTBP.vida.recipe;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;
import teamHTBP.vida.recipe.altar.AltarRecipe;
import teamHTBP.vida.recipe.altar.AltarRecipeSerializer;

import java.util.function.Supplier;

/**
 * @author DustW
 */
public class ModRecipeSerializers {
    private static final DeferredRegister<IRecipeSerializer<?>> SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Vida.MOD_ID);

    public static final RegistryObject<RecipeSerializerBase<AltarRecipe>> ALTAR = register("altar", AltarRecipeSerializer::new);

    private static <T extends IRecipe<?>> RegistryObject<RecipeSerializerBase<T>> register(String name, Supplier<RecipeSerializerBase<T>> obj) {
        return SERIALIZER.register(name, obj);
    }

    public static void register(IEventBus bus) {
        SERIALIZER.register(bus);
    }
}
