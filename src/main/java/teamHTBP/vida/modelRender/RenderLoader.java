package teamHTBP.vida.modelRender;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSpriteStitcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.Block.BlockLoader;
import teamHTBP.vida.TileEntity.TileEntityLoader;
import teamHTBP.vida.Vida;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderLoader {

    public static ResourceLocation gemLocation = new ResourceLocation(Vida.modId, "model/goldgem");
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

        ClientRegistry.bindTileEntityRenderer(TileEntityLoader.TileEntityGemShower.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderGemShower(tileEntityRendererDispatcher);
        }));
    }

   @SubscribeEvent
   public static void onAltlasEvent(TextureStitchEvent.Pre event){
       Vida.LOGGER.info("register Altlas");
       ResourceLocation stitching = event.getMap().getTextureLocation();
       if(!stitching.equals(AtlasTexture.LOCATION_BLOCKS_TEXTURE))
       {
           return;
       }


       event.addSprite(gemLocation);
   }


}
