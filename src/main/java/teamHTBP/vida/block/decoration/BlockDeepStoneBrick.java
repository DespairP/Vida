package teamHTBP.vida.block.decoration;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

@Deprecated
public class BlockDeepStoneBrick extends Block {
    public BlockDeepStoneBrick() {
        super(Properties.of(Material.STONE)
                // TODO 用 tag .harvestTool(ToolType.PICKAXE)
                .strength(2.0f, 6.0f));
    }


}
