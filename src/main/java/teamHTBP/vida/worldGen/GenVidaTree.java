package teamHTBP.vida.worldGen;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class GenVidaTree extends Feature<TreeConfiguration> {
    public GenVidaTree() {
        super(TreeConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> pContext) {
        return false;
    }
}
