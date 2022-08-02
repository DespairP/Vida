package teamHTBP.vida.common.block.decoration;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import teamHTBP.vida.api.common.block.IDecoBlock;
import teamHTBP.vida.common.item.VidaItemLoader;

import javax.annotation.Nullable;

public class DeepStoneBrickStraightBlock extends Block implements IDecoBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;


    public DeepStoneBrickStraightBlock() {
        super(Properties.of(Material.STONE).strength(2.0f, 6.0f)
                .sound(SoundType.STONE));
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide) {
            if (player.getInventory().getSelected().getItem() == VidaItemLoader.WAND_VIDA.get()) {
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
                return InteractionResult.SUCCESS;
            } else {
                return super.use(state, worldIn, pos, player, handIn, hit);
            }
        }
        return InteractionResult.SUCCESS;
    }


}
