package teamHTBP.vida.modelRender;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.texture.AtlasTexture;
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

    public static ResourceLocation goldgemLocation = new ResourceLocation(Vida.modId, "model/goldgem");
    public static ResourceLocation woodgemLocation = new ResourceLocation(Vida.modId, "model/woodgem");
    public static ResourceLocation aquagemLocation = new ResourceLocation(Vida.modId, "model/aquagem");
    public static ResourceLocation firegemLocation = new ResourceLocation(Vida.modId, "model/firegem");
    public static ResourceLocation earthgemLocation = new ResourceLocation(Vida.modId, "model/earthgem");

    public static ResourceLocation  goldlogoLocation = new ResourceLocation(Vida.modId,  "model/goldlogo");
    public static ResourceLocation  woodlogoLocation = new ResourceLocation(Vida.modId,  "model/woodlogo");
    public static ResourceLocation  aqualogoLocation = new ResourceLocation(Vida.modId,  "model/aqualogo");
    public static ResourceLocation  firelogoLocation = new ResourceLocation(Vida.modId,  "model/firelogo");
    public static ResourceLocation earthlogoLocation = new ResourceLocation(Vida.modId, "model/earthlogo");
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(BlockLoader.saplingVida.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockLoader.purfiedCauldron.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockLoader.elementcoreAltar.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockLoader.prismTable.get(), RenderType.getTranslucent());
    }

    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(TileEntityLoader.TileEntityPurfiedCauldron.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderPurfiedCauldron(tileEntityRendererDispatcher);
        }));

        ClientRegistry.bindTileEntityRenderer(TileEntityLoader.TileEntityGemShower.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderGemShower(tileEntityRendererDispatcher);
        }));

        ClientRegistry.bindTileEntityRenderer(TileEntityLoader.TileEntityElementCoreAltar.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderElementCoreAltar(tileEntityRendererDispatcher);
        }));

        ClientRegistry.bindTileEntityRenderer(TileEntityLoader.TileEntityPrismTable.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderPrismTable(tileEntityRendererDispatcher);
        }));

        ClientRegistry.bindTileEntityRenderer(TileEntityLoader.TileEntityCollector.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderCollector(tileEntityRendererDispatcher);
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


       event.addSprite(goldgemLocation);
       event.addSprite(woodgemLocation);
       event.addSprite(aquagemLocation);
       event.addSprite(firegemLocation);
       event.addSprite(earthgemLocation);
       event.addSprite(goldlogoLocation);
       event.addSprite(woodlogoLocation);
       event.addSprite(aqualogoLocation);
       event.addSprite(firelogoLocation);
       event.addSprite(earthlogoLocation);


   }



}
