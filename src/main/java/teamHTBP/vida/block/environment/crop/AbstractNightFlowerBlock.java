package teamHTBP.vida.block.environment.crop;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.element.IElement;

import java.util.Random;

public class AbstractNightFlowerBlock extends AbstractElementCropsBlock {


    public AbstractNightFlowerBlock(int stage, IElement element) {
        super(stage, element);
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        if (!worldIn.isAreaLoaded(pos, 1)) return;
        if (worldIn.getRawBrightness(pos, 0) >= 9) {
            int i = this.getAge(state);
            //晚上才能生长
            if (i < this.getMaxStage() && (worldIn.getDayTime() >= 12000 || worldIn.getDayTime() < 5000)) {
                float f = getGrowthChance(this, worldIn, pos);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int) (25.0F / f) + 1) == 0)) {
                    worldIn.setBlock(pos, this.withAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            } else if (i == getMaxStage()) {
                worldIn.setBlock(pos, this.withAge(i - 1), 2);
            }
        }
    }
}
