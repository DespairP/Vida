package teamHTBP.vida.Block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class BlockDeepStoneBrickStraight extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;


    public BlockDeepStoneBrickStraight() {
        super(Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.0F,3.0F));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.fillStateContainer(builder);
    }
}
