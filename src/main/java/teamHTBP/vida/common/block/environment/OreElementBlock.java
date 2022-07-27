package teamHTBP.vida.common.block.environment;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class OreElementBlock extends Block {
    public OreElementBlock() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F));

    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? MathHelper.nextInt(RANDOM, 5, 7) : 0;
    }
}
