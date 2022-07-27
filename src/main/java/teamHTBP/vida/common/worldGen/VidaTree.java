package teamHTBP.vida.common.worldGen;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import teamHTBP.vida.common.block.VidaBlockLoader;

import javax.annotation.Nullable;
import java.util.Random;

public class VidaTree extends Tree {
    public static final BaseTreeFeatureConfig VIDATREE = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(VidaBlockLoader.logVida.get().getDefaultState()),
            new SimpleBlockStateProvider(VidaBlockLoader.leavesVida.get().getDefaultState()),
            new BlobFoliagePlacer(
                    FeatureSpread.create(3), FeatureSpread.create(0), 3
            ), new StraightTrunkPlacer(
            10, 2, 2
    ), new TwoLayerFeature(10, 5, 5))).build();

    @Nullable
    @Override
    public ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return GenLoader.vidaTree.get().withConfiguration(VIDATREE);
    }
}