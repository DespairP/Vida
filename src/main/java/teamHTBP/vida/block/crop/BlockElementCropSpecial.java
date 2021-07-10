package teamHTBP.vida.block.crop;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamHTBP.vida.helper.ColorHelper;

import java.util.Random;

public class BlockElementCropSpecial extends AbstractBlockElementCrops{
    private ColorHelper helper;


    /**
     * 生成一株特殊植物，*特殊植物没有任何元素构成*
     * */
    public BlockElementCropSpecial(int stage,ColorHelper helper) {
        super(stage, 0);
        this.helper = helper;
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        //super.animateTick(stateIn, worldIn, pos, rand);
        float x = pos.getX();
        float y = pos.getY();
        float z = pos.getZ();
    }


}
