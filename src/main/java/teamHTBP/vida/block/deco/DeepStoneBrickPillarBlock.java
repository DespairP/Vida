package teamHTBP.vida.block.deco;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import teamHTBP.vida.item.VidaItemRegistry;

public class DeepStoneBrickPillarBlock extends RotatedPillarBlock implements IDecoBlock {
    private static final IntegerProperty STATE = IntegerProperty.create("type", 0, 2);

    public DeepStoneBrickPillarBlock() {
        super(Properties.of(Material.STONE));
    }

    /**
     * state是柱子的品种
     */
    public DeepStoneBrickPillarBlock(int state) {
        super(Properties.of(Material.STONE).strength(2.0f, 6.0f)
                .sound(SoundType.STONE));
        this.registerDefaultState(this.getStateDefinition().any().setValue(STATE, state).setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide) {
            if (player.getInventory().getSelected().getItem() == VidaItemRegistry.WAND_VIDA.get()) {
                int stateInt = state.getValue(STATE);
                Direction.Axis axis = state.getValue(AXIS);
                stateInt = (stateInt >= 2 ? 0 : stateInt + 1);
                BlockState newState = state.setValue(STATE, stateInt).setValue(AXIS, axis);
                worldIn.setBlockAndUpdate(pos, newState);
                return InteractionResult.SUCCESS;
            }
            return super.use(state, worldIn, pos, player, handIn, hit);
        }
        return InteractionResult.SUCCESS;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STATE, AXIS);
    }
}
