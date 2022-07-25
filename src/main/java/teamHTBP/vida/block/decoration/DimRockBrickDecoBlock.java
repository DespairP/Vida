package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class DimRockBrickDecoBlock extends Block {
    public DimRockBrickDecoBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE));
    }
}
