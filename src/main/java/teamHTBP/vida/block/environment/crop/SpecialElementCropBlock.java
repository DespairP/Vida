package teamHTBP.vida.block.environment.crop;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.element.EnumElements;

import java.util.Random;

public class SpecialElementCropBlock extends AbstractElementCropsBlock {
    /**
     * 生成一株特殊植物，*特殊植物没有任何元素构成*
     */
    public SpecialElementCropBlock(int stage) {
        super(stage, EnumElements.NONE);
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        //super.animateTick(stateIn, worldIn, pos, rand);
        float x = pos.getX();
        float y = pos.getY();
        float z = pos.getZ();
    }
}
