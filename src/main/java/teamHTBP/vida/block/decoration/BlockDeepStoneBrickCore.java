package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import net.minecraft.block.AbstractBlock.Properties;

@Deprecated
public class BlockDeepStoneBrickCore extends Block {
    public BlockDeepStoneBrickCore() {
        super(Properties.of(Material.STONE).strength(2.0f, 6.0f).sound(SoundType.STONE));
    }
}
