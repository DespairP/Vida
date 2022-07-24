package teamHTBP.vida.block.deco;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class DecoStairBlock extends StairBlock implements IDecoBlock {

    public DecoStairBlock(Supplier<BlockState> state, BlockBehaviour.Properties properties) {
        super(state, properties);
    }
}
