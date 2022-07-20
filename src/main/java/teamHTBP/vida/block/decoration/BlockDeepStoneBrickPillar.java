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

import net.minecraft.block.AbstractBlock.Properties;

public class BlockDeepStoneBrickPillar extends RotatedPillarBlock {
    private static final IntegerProperty STATE = IntegerProperty.create("type", 0, 2);

    public BlockDeepStoneBrickPillar() {
        super(Properties.of(Material.STONE));
    }

    /**
     * state是柱子的品种
     */
    public BlockDeepStoneBrickPillar(int state) {
        super(Properties.of(Material.STONE).strength(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE));
        this.registerDefaultState(this.getStateDefinition().any().setValue(STATE, state).setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide) {
            if (player.inventory.getSelected().getItem() == ItemLoader.WAND_VIDA.get()) {
                int stateInt = state.getValue(STATE);
                Direction.Axis axis = state.getValue(AXIS);
                stateInt = (stateInt >= 2 ? 0 : stateInt + 1);
                BlockState newState = state.setValue(STATE, stateInt).setValue(AXIS, axis);
                worldIn.setBlockAndUpdate(pos, newState);
                return ActionResultType.SUCCESS;
            }
            return super.use(state, worldIn, pos, player, handIn, hit);
        }
        return ActionResultType.SUCCESS;
    }


    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(STATE, AXIS);
    }
}
