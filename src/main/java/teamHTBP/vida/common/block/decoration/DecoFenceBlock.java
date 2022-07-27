package teamHTBP.vida.common.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;

public class DecoFenceBlock extends FenceBlock {
    public DecoFenceBlock(Properties properties) {
        super(properties);
    }

    /**栅栏连接逻辑*/
    public boolean canConnect(BlockState state, boolean isSideSolid, Direction direction) {
        Block block = state.getBlock();
        boolean flag = this.isWoodenFence(block) || block instanceof DecoFenceBlock;
        boolean flag1 = block instanceof FenceGateBlock && FenceGateBlock.isParallel(state, direction);
        return !cannotAttach(block) && isSideSolid || flag || flag1;
    }

    public boolean isWoodenFence(Block block) {
        return block.isIn(BlockTags.FENCES) && block.isIn(BlockTags.WOODEN_FENCES) == this.getDefaultState().isIn(BlockTags.WOODEN_FENCES);
    }
}
