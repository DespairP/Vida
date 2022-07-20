package teamHTBP.vida.block.decoration;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

@Deprecated
public class BlockDeepStoneBrickCore extends Block {
    public BlockDeepStoneBrickCore() {
        super(Properties.of(Material.STONE).strength(2.0f, 6.0f).sound(SoundType.STONE));
    }
}
