package teamHTBP.vida.worldGen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Block.BlockLoader;
import teamHTBP.vida.Vida;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeGenLoader {

    @SubscribeEvent
    public static void biomeGenSetup(FMLCommonSetupEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES)
        {
            Vida.LOGGER.info("biome Feature register");
            if(biome == Biomes.FOREST || biome == Biomes.PLAINS)
            biome.addFeature(
                    GenerationStage.Decoration.VEGETAL_DECORATION,
                    GenLoader.vidaTree.get().withConfiguration(VidaTree.VIDATREE).withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(4)
                      )));
        }


    }
}
