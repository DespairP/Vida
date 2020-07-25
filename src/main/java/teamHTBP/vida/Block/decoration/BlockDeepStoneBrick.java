package teamHTBP.vida.Block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockDeepStoneBrick extends Block {
    public BlockDeepStoneBrick() {
        super(Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3.0f, 3.0f));
    }


}
