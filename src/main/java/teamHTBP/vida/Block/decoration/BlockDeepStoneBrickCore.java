package teamHTBP.vida.Block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockDeepStoneBrickCore extends Block {
    public BlockDeepStoneBrickCore() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).sound(SoundType.STONE));
    }
}
