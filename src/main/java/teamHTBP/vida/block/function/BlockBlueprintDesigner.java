package teamHTBP.vida.block.function;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import teamHTBP.vida.helper.blockHelper.BlockSingleWithY;

public class BlockBlueprintDesigner extends BlockSingleWithY {
    public BlockBlueprintDesigner() {
        super(Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(4.0f).noOcclusion());
    }


}
