package teamHTBP.vida.block.decoration;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

@Deprecated
public class BlockFertileSoilBrickDeco extends Block {
    public BlockFertileSoilBrickDeco() {
        super(Properties.of(Material.STONE)
                // todo tag .harvestTool(ToolType.PICKAXE)
                .sound(SoundType.STONE).strength(2.0f, 6.0f));
    }
}
