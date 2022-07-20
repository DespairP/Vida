package teamHTBP.vida.block.environment.crop;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import teamHTBP.vida.helper.elementHelper.IElement;

import java.util.Random;

public class AbstractBlockNightFlower extends AbstractBlockElementCrops {


    public AbstractBlockNightFlower(int stage, IElement element) {
        super(stage, element);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
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
