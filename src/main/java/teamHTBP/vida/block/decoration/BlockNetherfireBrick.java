package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

@Deprecated
public class BlockNetherfireBrick extends Block {

    public BlockNetherfireBrick() {
        super(Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(3.0f, 6.0f));
    }
}
