package teamHTBP.vida.Block.function;


import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

/*元素祭坛
@Version 0.0.1*/
public class BlockElementCoreAltar extends Block {
    public BlockElementCoreAltar(){
        super(Properties.create(Material.IRON).notSolid().hardnessAndResistance(5.0f, 5.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE));

    }
}
