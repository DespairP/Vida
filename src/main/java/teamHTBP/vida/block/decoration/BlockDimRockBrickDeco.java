package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

import net.minecraft.block.AbstractBlock.Properties;

public class BlockDimRockBrickDeco extends Block {
    public BlockDimRockBrickDeco() {
        super(Properties.of(Material.STONE).strength(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE));
    }
}
