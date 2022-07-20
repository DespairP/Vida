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
        super(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0f, 3.0f));
        //this.setDefaultState(this.getStateContainer().getBaseState().with(STATE, 0));
    }

    public BlockVidaPlank(int state) {
        super(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0f, 3.0f));
        this.registerDefaultState(this.getStateDefinition().any().setValue(STATE, state));
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide)
            if (player.inventory.getSelected().getItem() == ItemLoader.WAND_VIDA.get()) {
                int stateInt = state.getValue(STATE);
                stateInt = (stateInt >= 2 ? 0 : stateInt + 1);
                BlockState newState = state.setValue(STATE, stateInt);
                worldIn.setBlockAndUpdate(pos, newState);
                return ActionResultType.SUCCESS;
            }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }


    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(STATE);
        super.createBlockStateDefinition(builder);
    }
}
