package teamHTBP.vida.client.render;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.BlockLoader;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.client.render.tileEntityModel.*;

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
        ItemBlockRenderTypes.setRenderLayer(BlockLoader.saplingVida.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockLoader.CROP_CRISMCREST.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockLoader.CROP_SWEETCYANREED.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockLoader.CROP_SULLENHYDRANGEA.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockLoader.CROP_NITRITETHORNS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockLoader.CROP_PLAMSTEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockLoader.CROP_HEARTOFWAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockLoader.purfiedCauldron.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockLoader.CORE_ALTAR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockLoader.prismTable.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockLoader.steleLife.get(), RenderType.translucent());
    }

    @SubscribeEvent
    public static void onClientEvent(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(TileEntityLoader.TileEntityPurfiedCauldron.get(), TileEntityRenderPurfiedCauldron::new);

        event.registerBlockEntityRenderer(TileEntityLoader.TileEntityGemShower.get(), (TileEntityRenderGemShower::new));

        event.registerBlockEntityRenderer(TileEntityLoader.TileEntityElementCoreAltar.get(), (TileEntityRenderElementCoreAltar::new));

        event.registerBlockEntityRenderer(TileEntityLoader.TileEntityPrismTable.get(), (TileEntityRenderPrismTable::new));

        event.registerBlockEntityRenderer(TileEntityLoader.TileEntityCollector.get(), (TileEntityRenderCollector::new));

        event.registerBlockEntityRenderer(TileEntityLoader.TileEntityCrystalGold.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderGoldCrystal(tileEntityRendererDispatcher, 1);
        }));

        event.registerBlockEntityRenderer(TileEntityLoader.TileEntityCrystalWood.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderWoodCrystal(tileEntityRendererDispatcher, 2);
        }));

        event.registerBlockEntityRenderer(TileEntityLoader.TileEntityCrystalAqua.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderLoaderAquaCrystal(tileEntityRendererDispatcher, 3);
        }));

        event.registerBlockEntityRenderer(TileEntityLoader.TileEntityCrystalFire.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderFireCrystal(tileEntityRendererDispatcher, 4);
        }));

        event.registerBlockEntityRenderer(TileEntityLoader.TileEntityCrystalEarth.get(), (tileEntityRendererDispatcher -> {
            return new TileEntityRenderEarthCrystal(tileEntityRendererDispatcher, 5);
        }));

        event.registerBlockEntityRenderer(TileEntityLoader.TileEntityInjectTable.get(), (TileEntityRenderInjectTable::new));


    }

    @SubscribeEvent
    public static void onAtlasEvent(TextureStitchEvent.Pre event) {
        Vida.LOGGER.info("register Atlas");
        ResourceLocation stitching = event.getAtlas().location();
        if (!stitching.equals(InventoryMenu.BLOCK_ATLAS)) {
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
