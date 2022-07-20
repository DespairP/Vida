package teamHTBP.vida.block.environment.crop;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.utils.color.ColorHelper;

import java.util.Random;

public class BlockElementCropSpecial extends AbstractBlockElementCrops {
    private final ColorHelper helper;


    /**
     * 生成一株特殊植物，*特殊植物没有任何元素构成*
     */
    public BlockElementCropSpecial(int stage, ColorHelper helper) {
        super(stage, EnumElements.NONE);
        this.helper = helper;
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        //super.animateTick(stateIn, worldIn, pos, rand);
        float x = pos.getX();
        float y = pos.getY();
        float z = pos.getZ();
    }


}
