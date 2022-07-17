package teamHTBP.vida;

import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.TileEntity.TileEntityLoader;
import teamHTBP.vida.block.BlockEventLoaderServer;
import teamHTBP.vida.block.BlockLoader;
import teamHTBP.vida.entity.EntityLoader;
import teamHTBP.vida.gui.GUI.ContainerTypeLoader;
import teamHTBP.vida.item.ItemLoader;
import teamHTBP.vida.item.function.ItemElementCoreVoid;
import teamHTBP.vida.particle.ParticleLoader;
import teamHTBP.vida.recipe.RecipeLoader;
import teamHTBP.vida.recipe.recipes.register.RecipeManager;
import teamHTBP.vida.worldGen.GenLoader;


/**
 * Vida核心类
 **/
@Mod("vida")
public class Vida {
    public static final Logger LOGGER = LogManager.getLogger(); // logger

    public static final String MOD_ID = "vida"; //mod的ID字符串，用于材质包获取id，或其他用途

    public Vida() {
        ItemLoader.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockLoader.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ParticleLoader.PARTICLE.register(FMLJavaModLoadingContext.get().getModEventBus());
        GenLoader.FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        TileEntityLoader.TILE_ENTITY_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntityLoader.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ContainerTypeLoader.CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(ItemElementCoreVoid.class);
        MinecraftForge.EVENT_BUS.register(BlockEventLoaderServer.class);
        RecipeLoader.init(FMLJavaModLoadingContext.get().getModEventBus());

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        RecipeManager.register(bus);
        //RecipeLoader.RECIPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        //registerType(OREACTION, OreReactionMachineRecipe.RECIPE_TYPE);
        //RecipeLoader.init(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @Deprecated
    private static void registerType(ResourceLocation name, IRecipeType<?> recipeType) {
        Registry.register(Registry.RECIPE_TYPE, name, recipeType);
    }

    @Deprecated
    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

}
