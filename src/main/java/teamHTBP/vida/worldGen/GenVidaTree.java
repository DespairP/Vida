package teamHTBP.vida.worldGen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class GenVidaTree extends Feature<BaseTreeFeatureConfig> {
    public GenVidaTree() {
        super(BaseTreeFeatureConfig.CODEC);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BaseTreeFeatureConfig config) {
        return false;
    }
}
