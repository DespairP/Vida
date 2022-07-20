package teamHTBP.vida.block.decoration;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class BlockDimRockBrickDeco extends Block {
    public BlockDimRockBrickDeco() {
        super(Properties.of(Material.STONE).strength(2.0f, 6.0f)
                // todo tag.harvestTool(ToolType.PICKAXE)
                .sound(SoundType.STONE));
    }
}
