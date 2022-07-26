package teamHTBP.vida.block.environment.crop;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamHTBP.vida.core.element.EnumElements;
import teamHTBP.vida.utils.color.ColorHelper;

import java.util.Random;

public class ElementCropSpecialBlock extends AbstractElementCropsBlock {
    private final ColorHelper helper;


    /**
     * 生成一株特殊植物，*特殊植物没有任何元素构成*
     */
    public ElementCropSpecialBlock(int stage, ColorHelper helper) {
        super(stage, EnumElements.NONE);
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
