package teamHTBP.vida.client.modelRender;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.common.TileEntity.TileEntityLoader;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.block.VidaBlockLoader;
import teamHTBP.vida.client.modelRender.tileEntityModel.*;

/**
 * 注册渲染类型与精灵图
 * */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderLoader {

    public static ResourceLocation goldgemLocation = new ResourceLocation(Vida.MOD_ID, "model/goldgem");
    public static ResourceLocation woodgemLocation = new ResourceLocation(Vida.MOD_ID, "model/woodgem");
    public static ResourceLocation aquagemLocation = new ResourceLocation(Vida.MOD_ID, "model/aquagem");
    public static ResourceLocation firegemLocation = new ResourceLocation(Vida.MOD_ID, "model/firegem");
    public static ResourceLocation earthgemLocation = new ResourceLocation(Vida.MOD_ID, "model/earthgem");

    public static ResourceLocation goldlogoLocation = new ResourceLocation(Vida.MOD_ID, "model/goldlogo");
    public static ResourceLocation woodlogoLocation = new ResourceLocation(Vida.MOD_ID, "model/woodlogo");
    public static ResourceLocation aqualogoLocation = new ResourceLocation(Vida.MOD_ID, "model/aqualogo");
    public static ResourceLocation firelogoLocation = new ResourceLocation(Vida.MOD_ID, "model/firelogo");
    public static ResourceLocation earthlogoLocation = new ResourceLocation(Vida.MOD_ID, "model/earthlogo");

    public static ResourceLocation goldCrystalLocation = new ResourceLocation(Vida.MOD_ID, "model/goldelementcrystal_animate");
    public static ResourceLocation woodCrystalLocation = new ResourceLocation(Vida.MOD_ID, "model/woodelementcrystal_animate");
    public static ResourceLocation aquaCrystalLocation = new ResourceLocation(Vida.MOD_ID, "model/aquaelementcrystal_animate");
    public static ResourceLocation fireCrystalLocation = new ResourceLocation(Vida.MOD_ID, "model/fireelementcrystal_animate");
    public static ResourceLocation earthCrystalLocation = new ResourceLocation(Vida.MOD_ID, "model/earthelementcrystal_animate");

    public static ResourceLocation goldFaintLightLocation = new ResourceLocation(Vida.MOD_ID, "item/faintlight_gold");
    public static ResourceLocation woodFaintLightLocation = new ResourceLocation(Vida.MOD_ID, "item/faintlight_wood");
    public static ResourceLocation aquaFaintLightLocation = new ResourceLocation(Vida.MOD_ID, "item/faintlight_aqua");
    public static ResourceLocation fireFaintLightLocation = new ResourceLocation(Vida.MOD_ID, "item/faintlight_fire");
    public static ResourceLocation earthFaintLightLocation = new ResourceLocation(Vida.MOD_ID, "item/faintlight_earth");

    public static ResourceLocation injectTable_top = new ResourceLocation(Vida.MOD_ID, "model/injectiontable_top");
    public static ResourceLocation injectTable_side = new ResourceLocation(Vida.MOD_ID, "model/injectiontable_side");


    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(VidaBlockLoader.saplingVida.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VidaBlockLoader.CROP_CRISMCREST.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(VidaBlockLoader.CROP_SWEETCYANREED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VidaBlockLoader.CROP_SULLENHYDRANGEA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VidaBlockLoader.CROP_NITRITETHORNS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VidaBlockLoader.CROP_PLAMSTEM.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VidaBlockLoader.CROP_HEARTOFWAL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VidaBlockLoader.purfiedCauldron.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VidaBlockLoader.CORE_ALTAR.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VidaBlockLoader.prismTable.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(VidaBlockLoader.steleLife.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(VidaBlockLoader.INDIGO_IRIS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VidaBlockLoader.DEADWOOD_TWISTER.get(), RenderType.getCutout());
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
    public static void onAtlasEvent(TextureStitchEvent.Pre event) {
        Vida.LOGGER.info("register Atlas");
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
