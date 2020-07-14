package teamHTBP.vida.modelRender;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.Block.BlockLoader;
import teamHTBP.vida.TileEntity.TileEntityLoader;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderLoader {
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(BlockLoader.saplingVida.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockLoader.purfiedCauldron.get(), RenderType.getCutout());
    }

    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(TileEntityLoader.TileEntityPurfiedCauldron.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderPurfiedCauldron(tileEntityRendererDispatcher);
        }));
    }
}
