package teamHTBP.vida.block.environment;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockDeepStone extends Block {
    public BlockDeepStone() {
        super(Block.Properties.of(Material.STONE).strength(1.5f, 6.0f).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE));
    }
}
