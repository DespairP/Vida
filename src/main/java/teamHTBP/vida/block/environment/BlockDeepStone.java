package teamHTBP.vida.block.environment;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class BlockDeepStone extends Block {
    public BlockDeepStone() {
        super(Block.Properties.of(Material.STONE).strength(1.5f, 6.0f).sound(SoundType.STONE)
                // todo tag .harvestTool(ToolType.PICKAXE)
        );
    }
}
