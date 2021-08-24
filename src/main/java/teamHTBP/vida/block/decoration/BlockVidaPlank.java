package teamHTBP.vida.block.decoration;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import teamHTBP.vida.item.ItemLoader;

public class BlockVidaPlank extends Block {
    private static final IntegerProperty STATE = IntegerProperty.create("type", 0, 2);

    public BlockVidaPlank() {
        super(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f));
        //this.setDefaultState(this.getStateContainer().getBaseState().with(STATE, 0));
    }

    public BlockVidaPlank(int state) {
        super(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f));
        this.setDefaultState(this.getStateContainer().getBaseState().with(STATE, state));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote)
            if (player.inventory.getCurrentItem().getItem() == ItemLoader.WAND_VIDA.get()) {
                int stateInt = state.get(STATE);
                stateInt = (stateInt >= 2 ? 0 : stateInt + 1);
                BlockState newState = state.with(STATE, stateInt);
                worldIn.setBlockState(pos, newState);
                return ActionResultType.SUCCESS;
            }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }


    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(STATE);
        super.fillStateContainer(builder);
    }
}
