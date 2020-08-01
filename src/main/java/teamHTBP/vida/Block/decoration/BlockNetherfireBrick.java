package teamHTBP.vida.Block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockNetherfireBrick extends Block {

    public BlockNetherfireBrick() {
        super(Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0f, 2.0f));
    }
}
