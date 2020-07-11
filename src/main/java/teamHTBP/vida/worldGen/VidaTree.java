package teamHTBP.vida.worldGen;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import teamHTBP.vida.Block.BlockLoader;

import javax.annotation.Nullable;
import java.util.Random;

public class VidaTree extends Tree {
    public static final TreeFeatureConfig VIDATREE = (new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(BlockLoader.logVida.get().getDefaultState()),
            new SimpleBlockStateProvider(BlockLoader.leavesVida.get().getDefaultState()),
            new BlobFoliagePlacer(3,0))).baseHeight(10).build();
    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return GenLoader.vidaTree.get().withConfiguration(VIDATREE);
    }
}