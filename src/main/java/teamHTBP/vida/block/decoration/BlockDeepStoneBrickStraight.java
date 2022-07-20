package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
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

import net.minecraft.block.AbstractBlock.Properties;

public class BlockDeepStoneBrickStraight extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;


    public BlockDeepStoneBrickStraight() {
        super(Properties.of(Material.STONE).strength(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getClickedPos();
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide) {
            if (player.inventory.getSelected().getItem() == ItemLoader.WAND_VIDA.get()) {
                Direction stateDirection = state.getValue(FACING);
                System.out.println(stateDirection);
                switch (stateDirection) {
                    case EAST:
                        worldIn.setBlockAndUpdate(pos, state.setValue(FACING, Direction.SOUTH));
                        break;
                    case SOUTH:
                        worldIn.setBlockAndUpdate(pos, state.setValue(FACING, Direction.WEST));
                        break;
                    case WEST:
                        worldIn.setBlockAndUpdate(pos, state.setValue(FACING, Direction.NORTH));
                        break;
                    case NORTH:
                        worldIn.setBlockAndUpdate(pos, state.setValue(FACING, Direction.EAST));
                        break;
                }
                return ActionResultType.SUCCESS;
            } else {
                return super.use(state, worldIn, pos, player, handIn, hit);
            }
        }
        return ActionResultType.SUCCESS;
    }


}
