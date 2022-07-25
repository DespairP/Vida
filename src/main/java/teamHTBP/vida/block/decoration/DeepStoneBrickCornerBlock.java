package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import teamHTBP.vida.item.ItemLoader;

import javax.annotation.Nullable;

public class DeepStoneBrickCornerBlock extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    private static final IntegerProperty STATE = IntegerProperty.create("rotate", 0, 2);


    public DeepStoneBrickCornerBlock() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE));
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(STATE, 0));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, STATE);
        super.fillStateContainer(builder);
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            if (player.inventory.getCurrentItem().getItem() == ItemLoader.WAND_VIDA.get()) {
                Direction stateDirection = state.get(FACING);
                //System.out.println(stateDirection);
                switch (stateDirection) {
                    case EAST:
                        worldIn.setBlockState(pos, state.with(FACING, Direction.SOUTH).with(STATE, 0));
                        break;
                    case SOUTH:
                        worldIn.setBlockState(pos, state.with(FACING, Direction.WEST).with(STATE, 0));
                        break;
                    case WEST:
                        worldIn.setBlockState(pos, state.with(FACING, Direction.NORTH).with(STATE, 0));
                        break;
                    case NORTH:
                        worldIn.setBlockState(pos, state.with(FACING, Direction.EAST).with(STATE, 0));
                        break;
                }
                return ActionResultType.SUCCESS;
            } else {
                return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
            }
        }
        return ActionResultType.SUCCESS;
    }
}
