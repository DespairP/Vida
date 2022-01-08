package teamHTBP.vida.modelRender;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.TileEntity.TileEntityLoader;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.BlockLoader;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderLoader {

    public static ResourceLocation goldgemLocation = new ResourceLocation(Vida.modId, "model/goldgem");
    public static ResourceLocation woodgemLocation = new ResourceLocation(Vida.modId, "model/woodgem");
    public static ResourceLocation aquagemLocation = new ResourceLocation(Vida.modId, "model/aquagem");
    public static ResourceLocation firegemLocation = new ResourceLocation(Vida.modId, "model/firegem");
    public static ResourceLocation earthgemLocation = new ResourceLocation(Vida.modId, "model/earthgem");

    public static ResourceLocation goldlogoLocation = new ResourceLocation(Vida.modId, "model/goldlogo");
    public static ResourceLocation woodlogoLocation = new ResourceLocation(Vida.modId, "model/woodlogo");
    public static ResourceLocation aqualogoLocation = new ResourceLocation(Vida.modId, "model/aqualogo");
    public static ResourceLocation firelogoLocation = new ResourceLocation(Vida.modId, "model/firelogo");
    public static ResourceLocation earthlogoLocation = new ResourceLocation(Vida.modId, "model/earthlogo");

    public static ResourceLocation goldCrystalLocation = new ResourceLocation(Vida.modId, "model/goldelementcrystal_animate");
    public static ResourceLocation woodCrystalLocation = new ResourceLocation(Vida.modId, "model/woodelementcrystal_animate");
    public static ResourceLocation aquaCrystalLocation = new ResourceLocation(Vida.modId, "model/aquaelementcrystal_animate");
    public static ResourceLocation fireCrystalLocation = new ResourceLocation(Vida.modId, "model/fireelementcrystal_animate");
    public static ResourceLocation earthCrystalLocation = new ResourceLocation(Vida.modId, "model/earthelementcrystal_animate");

    public static ResourceLocation goldFaintLightLocation = new ResourceLocation(Vida.modId, "item/faintlight_gold");
    public static ResourceLocation woodFaintLightLocation = new ResourceLocation(Vida.modId, "item/faintlight_wood");
    public static ResourceLocation aquaFaintLightLocation = new ResourceLocation(Vida.modId, "item/faintlight_aqua");
    public static ResourceLocation fireFaintLightLocation = new ResourceLocation(Vida.modId, "item/faintlight_fire");
    public static ResourceLocation earthFaintLightLocation = new ResourceLocation(Vida.modId, "item/faintlight_earth");

    public static ResourceLocation injectTable_top = new ResourceLocation(Vida.modId, "model/injectiontable_top");
    public static ResourceLocation injectTable_side = new ResourceLocation(Vida.modId, "model/injectiontable_side");


    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(BlockLoader.saplingVida.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockLoader.crimsonCrest.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockLoader.sweetCyanReed.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockLoader.sullenHydrangea.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockLoader.nitriteThorns.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockLoader.plamStem.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockLoader.heartOfWal.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockLoader.purfiedCauldron.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockLoader.elementcoreAltar.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockLoader.prismTable.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(BlockLoader.steleLife.get(), RenderType.getTranslucent());
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

        ClientRegistry.bindTileEntityRenderer(TileEntityLoader.TileEntityCrystalGold.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderGoldCrystal(tileEntityRendererDispatcher, 1);
        }));

        ClientRegistry.bindTileEntityRenderer(TileEntityLoader.TileEntityCrystalWood.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderWoodCrystal(tileEntityRendererDispatcher, 2);
        }));

        ClientRegistry.bindTileEntityRenderer(TileEntityLoader.TileEntityCrystalAqua.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderLoaderAquaCrystal(tileEntityRendererDispatcher, 3);
        }));

        ClientRegistry.bindTileEntityRenderer(TileEntityLoader.TileEntityCrystalFire.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderFireCrystal(tileEntityRendererDispatcher, 4);
        }));

        ClientRegistry.bindTileEntityRenderer(TileEntityLoader.TileEntityCrystalEarth.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderEarthCrystal(tileEntityRendererDispatcher, 5);
        }));

        ClientRegistry.bindTileEntityRenderer(TileEntityLoader.TileEntityInjectTable.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderInjectTable(tileEntityRendererDispatcher);
        }));


    }

    @SubscribeEvent
    public static void onAltlasEvent(TextureStitchEvent.Pre event) {
        Vida.LOGGER.info("register Altlas");
        ResourceLocation stitching = event.getMap().getTextureLocation();
        if (!stitching.equals(AtlasTexture.LOCATION_BLOCKS_TEXTURE)) {
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

        event.addSprite(goldCrystalLocation);
        event.addSprite(woodCrystalLocation);
        event.addSprite(aquaCrystalLocation);
        event.addSprite(fireCrystalLocation);
        event.addSprite(earthCrystalLocation);

        event.addSprite(injectTable_top);
        event.addSprite(injectTable_side);
    }


}
