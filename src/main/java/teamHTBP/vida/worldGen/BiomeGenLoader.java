package teamHTBP.vida.worldGen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Block.BlockLoader;


public class BiomeGenLoader {
    public static final TreeFeatureConfig VIDATREE = (new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(BlockLoader.logVida.get().getDefaultState()),
            new SimpleBlockStateProvider(BlockLoader.leavesVida.get().getDefaultState()),
            new BlobFoliagePlacer(4,0))).heightRandA(8).foliageHeightRandom(3).baseHeight(5).build();


    @SubscribeEvent
    public static void biomeGenSetup(FMLCommonSetupEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES)
        {
            System.out.println("register....SOME");

            biome.addFeature(
                    GenerationStage.Decoration.VEGETAL_DECORATION,
                    GenLoader.vidaTree.get().withConfiguration(VIDATREE)
                    );
        }


    }
}
