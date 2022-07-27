package teamHTBP.vida.common.block.environment;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class ElementOreBlock extends Block {
    public ElementOreBlock() {
        super(Block.Properties.of(Material.STONE).strength(3.0F, 3.0F));

    }

    @Override
    public int getExpDrop(BlockState state, LevelReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? Mth.nextInt(RANDOM, 5, 7) : 0;
    }
}
