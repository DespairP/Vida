package teamHTBP.vida.recipe;

import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;
import teamHTBP.vida.recipe.altar.AltarRecipe;
import teamHTBP.vida.recipe.altar.AltarRecipeSerializer;

import java.util.function.Supplier;

/**
 * Mod所含有的Recipe全部会储存在这个类中
 * */
public class RecipeLoader {

    /**
     * 初始化方法
     * @see Vida#Vida()
     * */
    public static void init(IEventBus bus){
        RECIPES.register(bus);
    }

    /**合成表注册*/
    private static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Vida.MOD_ID);
    /**核心祭坛*/
    public static final RegistryObject<RecipeSerializerBase<AltarRecipe>> ALTAR = register("altar", AltarRecipeSerializer::new);

    @Deprecated
    public static final RegistryObject<OreReactionMachineRecipe.Serializer> OREREACTION_RECIPE = RECIPES.register("orereaction_recipe", OreReactionMachineRecipe.Serializer::new);


    private static <T extends IRecipe<?>> RegistryObject<RecipeSerializerBase<T>> register(String name, Supplier<RecipeSerializerBase<T>> obj) {
        return RECIPES.register(name, obj);
    }

    /**根据TileEntity获取合成表*/
    public static <R extends ICraftingRecipe> R find(World world, TileEntity tileEntity) {
        for (ICraftingRecipe recipe : world.getRecipeManager().getRecipesForType(IRecipeType.CRAFTING)) {
            if (recipe instanceof RecipesBase && ((RecipesBase) recipe).matches(tileEntity)) {
                return (R) recipe;
            }
        }
        return null;
    }

}
