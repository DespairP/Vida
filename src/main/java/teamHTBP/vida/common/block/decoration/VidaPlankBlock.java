package teamHTBP.vida.common.block.decoration;


import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import teamHTBP.vida.api.common.block.IDecoBlock;
import teamHTBP.vida.common.item.VidaItemLoader;

public class VidaPlankBlock extends Block implements IDecoBlock {
    private static final IntegerProperty STATE = IntegerProperty.create("type", 0, 2);

    public VidaPlankBlock() {
        super(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0f, 3.0f));
        //this.setDefaultState(this.getStateContainer().getBaseState().with(STATE, 0));
    }

    public VidaPlankBlock(int state) {
        super(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0f, 3.0f));
        this.registerDefaultState(this.getStateDefinition().any().setValue(STATE, state));
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide) {
            if (player.getInventory().getSelected().getItem() == VidaItemLoader.WAND_VIDA.get()) {
                int stateInt = state.getValue(STATE);
                stateInt = (stateInt >= 2 ? 0 : stateInt + 1);
                BlockState newState = state.setValue(STATE, stateInt);
                worldIn.setBlockAndUpdate(pos, newState);
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STATE);
        super.createBlockStateDefinition(builder);
    }
}
