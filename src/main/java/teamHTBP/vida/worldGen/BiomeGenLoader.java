package teamHTBP.vida.worldGen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Block.BlockLoader;
import teamHTBP.vida.Capability.ElementHelper;
import teamHTBP.vida.Vida;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeGenLoader {

    @SubscribeEvent
    public static void biomeGenSetup(FMLCommonSetupEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES)
        {
            if(biome == Biomes.FOREST || biome == Biomes.PLAINS)
            biome.addFeature(
                    GenerationStage.Decoration.VEGETAL_DECORATION,
                    GenLoader.vidaTree.get().withConfiguration(VidaTree.VIDATREE).withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(4)
                      )));
        }


    }

    @SubscribeEvent
    public static void oreGenSetup(FMLCommonSetupEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES)
        {
            int element =ElementHelper.getBiomeElement(biome);
             if(element == 1){
                 biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                         Feature.ORE.withConfiguration(
                                 new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                         BlockLoader.goldElementOre.get().getDefaultState(),
                                         2)
                         ).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(30, 10, 10)))
                 );
             }else if(element == 2) {
                 biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                         Feature.ORE.withConfiguration(
                                 new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                         BlockLoader.woodElementOre.get().getDefaultState(),
                                         2)
                         ).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(30, 10, 10)))
                 );
             }else if(element == 3) {
                 biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                         Feature.ORE.withConfiguration(
                                 new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                         BlockLoader.aquaElementOre.get().getDefaultState(),
                                         2)
                         ).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(30, 10, 10)))
                 );
             }else if(element == 4) {
                 biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                         Feature.ORE.withConfiguration(
                                 new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                         BlockLoader.fireElementOre.get().getDefaultState(),
                                         2)
                         ).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(30, 10, 10)))
                 );
             }else if(element == 5) {
                 biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                         Feature.ORE.withConfiguration(
                                 new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                         BlockLoader.earthElementOre.get().getDefaultState(),
                                         2)
                         ).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(30, 10, 10)))
                 );
             }
        }


    }


}
