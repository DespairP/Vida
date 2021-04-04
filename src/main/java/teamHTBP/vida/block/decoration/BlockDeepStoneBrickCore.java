package teamHTBP.vida.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

@Deprecated
public class BlockDeepStoneBrickCore extends Block {
    public BlockDeepStoneBrickCore() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 6.0f).sound(SoundType.STONE));
    }
}
