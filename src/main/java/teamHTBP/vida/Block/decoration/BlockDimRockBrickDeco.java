package teamHTBP.vida.Block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockDimRockBrickDeco extends Block {
    public BlockDimRockBrickDeco() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE));
    }
}
