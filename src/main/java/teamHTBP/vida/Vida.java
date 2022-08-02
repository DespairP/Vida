package teamHTBP.vida;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.client.event.listener.VidaClientEventHandler;
import teamHTBP.vida.common.block.VidaBlockLoader;
import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.common.datapack.VidaDataPackLoader;
import teamHTBP.vida.common.entity.VidaEntityLoader;
import teamHTBP.vida.common.item.VidaItemLoader;
import teamHTBP.vida.common.menu.VidaMenuTypeLoader;
import teamHTBP.vida.common.particle.type.VidaParticleTypeLoader;
import teamHTBP.vida.common.recipe.VidaRecipeSerializerLoader;
import teamHTBP.vida.common.recipe.VidaRecipeTypeLoader;
import teamHTBP.vida.common.world.generation.VidaGenerationLoader;
import teamHTBP.vida.network.VidaPacketManager;


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

        VidaBlockLoader.BLOCKS.register(bus);
        VidaItemLoader.ITEMS.register(bus);
        VidaParticleTypeLoader.PARTICLE.register(bus);
        VidaBlockEntityLoader.BLOCK_ENTITIES.register(bus);
        VidaEntityLoader.ENTITY_TYPES.register(bus);
        VidaMenuTypeLoader.MENU_TYPE.register(bus);
        VidaRecipeTypeLoader.TYPES.register(bus);
        VidaRecipeSerializerLoader.SERIALIZER.register(bus);

        VidaGenerationLoader.register(bus);
        VidaDataPackLoader.register(bus);
        VidaPacketManager.register();
    }
}
