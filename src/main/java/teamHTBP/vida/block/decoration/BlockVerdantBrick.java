package teamHTBP.vida.block.decoration;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

@Deprecated
public class BlockVerdantBrick extends Block {
    public BlockVerdantBrick() {
        super(Properties.of(Material.STONE)
                .sound(SoundType.STONE).strength(3.0f, 6.0f));
    }
}
