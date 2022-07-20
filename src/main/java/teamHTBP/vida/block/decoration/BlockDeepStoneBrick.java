package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

import net.minecraft.block.AbstractBlock.Properties;

@Deprecated
public class BlockDeepStoneBrick extends Block {
    public BlockDeepStoneBrick() {
        super(Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).strength(2.0f, 6.0f));
    }


}
