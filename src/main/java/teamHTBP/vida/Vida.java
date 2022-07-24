package teamHTBP.vida;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.block.VidaBlockRegistry;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.client.event.listener.VidaClientEventHandler;
import teamHTBP.vida.entity.VidaEntityRegistry;
import teamHTBP.vida.datapack.ModDataPacks;
import teamHTBP.vida.item.VidaItemRegistry;
import teamHTBP.vida.menu.TypeLoaderMenu;
import teamHTBP.vida.network.PacketManager;
import teamHTBP.vida.particle.ParticleLoader;
import teamHTBP.vida.recipe.RecipeManager;
import teamHTBP.vida.world.generation.GenerationRegistry;


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

        VidaBlockRegistry.BLOCKS.register(bus);
        VidaItemRegistry.ITEMS.register(bus);
        ParticleLoader.PARTICLE.register(bus);
        TileEntityLoader.TILE_ENTITY_DEFERRED_REGISTER.register(bus);
        VidaEntityRegistry.ENTITY_TYPES.register(bus);
        TypeLoaderMenu.CONTAINER_TYPES.register(bus);

        GenerationRegistry.register(bus);
        RecipeManager.register(bus);
        ModDataPacks.register(bus);

        PacketManager.register();
    }
}
