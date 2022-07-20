package teamHTBP.vida.worldGen;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class VidaTree extends AbstractTreeGrower {
    @Nullable
    @Override
    public Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random randomIn, boolean p_225546_2_) {
        return GenLoader.VIDA_TREE.getHolder().get();
    }
}