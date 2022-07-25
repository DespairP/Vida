package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
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

public class DeepStoneBrickPillarBlock extends RotatedPillarBlock {
    private static final IntegerProperty STATE = IntegerProperty.create("type", 0, 2);

    public DeepStoneBrickPillarBlock() {
        super(Properties.create(Material.ROCK));
    }

    /**
     * state是柱子的品种
     */
    public DeepStoneBrickPillarBlock(int state) {
        super(Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE));
        this.setDefaultState(this.getStateContainer().getBaseState().with(STATE, state).with(AXIS, Direction.Axis.Y));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            if (player.inventory.getCurrentItem().getItem() == ItemLoader.WAND_VIDA.get()) {
                int stateInt = state.get(STATE);
                Direction.Axis axis = state.get(AXIS);
                stateInt = (stateInt >= 2 ? 0 : stateInt + 1);
                BlockState newState = state.with(STATE, stateInt).with(AXIS, axis);
                worldIn.setBlockState(pos, newState);
                return ActionResultType.SUCCESS;
            }
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }
        return ActionResultType.SUCCESS;
    }


    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(STATE, AXIS);
    }
}
