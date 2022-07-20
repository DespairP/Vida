package teamHTBP.vida.block.environment;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class BlockOreElement extends Block {
    public BlockOreElement() {
        super(Block.Properties.of(Material.STONE).strength(3.0F, 3.0F));

    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? MathHelper.nextInt(RANDOM, 5, 7) : 0;
    }
}
