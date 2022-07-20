package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

import net.minecraft.block.AbstractBlock.Properties;

@Deprecated
public class BlockVerdantBrick extends Block {
    public BlockVerdantBrick() {
        super(Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).strength(3.0f, 6.0f));
    }
}
