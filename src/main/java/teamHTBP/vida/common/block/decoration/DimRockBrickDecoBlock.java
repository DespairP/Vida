package teamHTBP.vida.common.block.decoration;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import teamHTBP.vida.api.common.block.IDecoBlock;

public class DimRockBrickDecoBlock extends Block implements IDecoBlock {
    public DimRockBrickDecoBlock() {
        super(Properties.of(Material.STONE).strength(2.0f, 6.0f)
                .sound(SoundType.STONE));
    }
}
