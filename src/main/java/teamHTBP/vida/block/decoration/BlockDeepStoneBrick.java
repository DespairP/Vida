package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

@Deprecated
public class BlockDeepStoneBrick extends Block {
    public BlockDeepStoneBrick() {
        super(Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2.0f, 6.0f));
    }


}
