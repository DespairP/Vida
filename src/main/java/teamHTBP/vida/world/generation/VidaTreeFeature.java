package teamHTBP.vida.world.generation;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class VidaTreeFeature extends Feature<TreeConfiguration> {
    public VidaTreeFeature() {
        super(TreeConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> pContext) {
        return false;
    }
}
