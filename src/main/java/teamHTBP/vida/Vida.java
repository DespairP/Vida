package teamHTBP.vida;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.TileEntity.TileEntityLoader;
import teamHTBP.vida.block.VidaBlockLoader;
import teamHTBP.vida.entity.EntityLoader;
import teamHTBP.vida.event.client.VidaClientEventHandler;
import teamHTBP.vida.event.server.BlockEventLoaderServer;
import teamHTBP.vida.gui.GUI.ContainerTypeLoader;
import teamHTBP.vida.item.ItemLoader;
import teamHTBP.vida.item.function.ItemElementCoreVoid;
import teamHTBP.vida.particle.ParticleLoader;
import teamHTBP.vida.recipe.RecipeManager;
import teamHTBP.vida.worldGen.GenLoader;


/**
 * Vida核心类
 **/
@Mod("vida")
public class Vida {
    public static final Logger LOGGER = LogManager.getLogger(); // logger

    public static final String MOD_ID = "vida"; //mod的ID字符串，用于材质包获取id，或其他用途

    public Vida() {
        MinecraftForge.EVENT_BUS.register(VidaClientEventHandler.class);
        ItemLoader.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        VidaBlockLoader.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ParticleLoader.PARTICLE.register(FMLJavaModLoadingContext.get().getModEventBus());
        GenLoader.FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        TileEntityLoader.TILE_ENTITY_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntityLoader.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ContainerTypeLoader.CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(ItemElementCoreVoid.class);
        MinecraftForge.EVENT_BUS.register(BlockEventLoaderServer.class);
        RecipeManager.register(FMLJavaModLoadingContext.get().getModEventBus());
        //RecipeLoader.RECIPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        //RecipeLoader.RECIPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        //registerType(OREACTION, OreReactionMachineRecipe.RECIPE_TYPE);
        //RecipeLoader.init(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
