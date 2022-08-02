package teamHTBP.vida.common.block.decoration;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.api.common.block.IDecoBlock;

import java.util.function.Supplier;

public class DecoStairBlock extends StairBlock implements IDecoBlock {

    public DecoStairBlock(Supplier<BlockState> state, BlockBehaviour.Properties properties) {
        super(state, properties);
    }
}
