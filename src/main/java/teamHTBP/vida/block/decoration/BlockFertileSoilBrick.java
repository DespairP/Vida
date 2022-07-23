package teamHTBP.vida.block.decoration;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

@Deprecated
public class BlockFertileSoilBrick extends Block {
    public BlockFertileSoilBrick() {
        super(Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0f, 6.0f));
    }
}
