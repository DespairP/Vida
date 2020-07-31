package teamHTBP.vida.Block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockDimRockBrick extends Block {
    public BlockDimRockBrick() {
        super(Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f, 2.0f));
    }
}
