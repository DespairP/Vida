package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

import net.minecraft.block.AbstractBlock.Properties;

@Deprecated
public class BlockFertileSoilBrickDeco extends Block {
    public BlockFertileSoilBrickDeco() {
        super(Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).strength(2.0f, 6.0f));
    }
}
