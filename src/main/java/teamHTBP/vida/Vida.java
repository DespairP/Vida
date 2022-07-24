package teamHTBP.vida;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.block.BlockLoader;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.client.event.listener.VidaClientEventHandler;
import teamHTBP.vida.entity.EntityLoader;
import teamHTBP.vida.event.server.datapack.ModDataPacks;
import teamHTBP.vida.item.ItemLoader;
import teamHTBP.vida.menu.ContainerTypeLoader;
import teamHTBP.vida.particle.ParticleLoader;
import teamHTBP.vida.recipe.RecipeManager;
import teamHTBP.vida.worldGen.GenLoader;


/**
 * Vida核心类
 **/
@Mod("vida")
public class Vida {
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "vida"; //mod的ID字符串，用于材质包获取id，或其他用途

    public Vida() {
        MinecraftForge.EVENT_BUS.register(VidaClientEventHandler.class);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockLoader.BLOCKS.register(bus);
        ItemLoader.ITEMS.register(bus);
        ParticleLoader.PARTICLE.register(bus);
        TileEntityLoader.TILE_ENTITY_DEFERRED_REGISTER.register(bus);
        EntityLoader.ENTITY_TYPES.register(bus);
        ContainerTypeLoader.CONTAINER_TYPES.register(bus);

        GenLoader.register(bus);
        RecipeManager.register(bus);
        ModDataPacks.register(bus);
    }
}
