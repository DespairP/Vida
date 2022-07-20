package teamHTBP.vida.block.function;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import teamHTBP.vida.helper.blockHelper.BlockSingleWithY;

public class BlockBlueprintDesigner extends BlockSingleWithY {
    public BlockBlueprintDesigner() {
        super(Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(4.0f).noOcclusion());
    }


}
