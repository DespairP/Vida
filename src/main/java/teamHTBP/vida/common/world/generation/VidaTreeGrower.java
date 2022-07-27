package teamHTBP.vida.common.world.generation;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class VidaTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    public Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random randomIn, boolean p_225546_2_) {
        return VidaGenerationLoader.VIDA_TREE.getHolder().get();
    }
}