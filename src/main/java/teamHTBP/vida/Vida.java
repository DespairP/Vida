package teamHTBP.vida;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.common.TileEntity.TileEntityLoader;
import teamHTBP.vida.common.block.VidaBlockLoader;
import teamHTBP.vida.common.entity.EntityLoader;
import teamHTBP.vida.common.worldGen.BiomeLoader;
import teamHTBP.vida.event.client.VidaClientEventHandler;
import teamHTBP.vida.event.server.BlockEventLoaderServer;
import teamHTBP.vida.client.gui.GUI.ContainerTypeLoader;
import teamHTBP.vida.common.item.ItemLoader;
import teamHTBP.vida.common.item.function.ItemElementCoreVoid;
import teamHTBP.vida.common.particle.ParticleLoader;
import teamHTBP.vida.common.recipe.RecipeManager;
import teamHTBP.vida.common.worldGen.GenLoader;


/**
 * Vida核心类
 **/
@Mod("vida")
public class Vida {
    public static final Logger LOGGER = LogManager.getLogger(); // logger

    public static final String MOD_ID = "vida"; //mod的ID字符串，用于材质包获取id，或其他用途

    public Vida() {
        MinecraftForge.EVENT_BUS.register(VidaClientEventHandler.class);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemLoader.ITEMS.register(bus);
        VidaBlockLoader.BLOCKS.register(bus);
        ParticleLoader.PARTICLE.register(bus);
        GenLoader.FEATURES.register(bus);
        BiomeLoader.BIOMES.register(bus);
        TileEntityLoader.TILE_ENTITY_DEFERRED_REGISTER.register(bus);
        EntityLoader.ENTITY_TYPES.register(bus);
        ContainerTypeLoader.CONTAINER_TYPES.register(bus);
        MinecraftForge.EVENT_BUS.register(ItemElementCoreVoid.class);
        MinecraftForge.EVENT_BUS.register(BlockEventLoaderServer.class);
        RecipeManager.register(bus);
    }
}
