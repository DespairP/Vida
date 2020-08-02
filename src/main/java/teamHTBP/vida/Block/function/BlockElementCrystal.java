package teamHTBP.vida.Block.function;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public abstract class BlockElementCrystal extends Block {
    public BlockElementCrystal() {
        super(Properties.create(Material.GLASS).hardnessAndResistance(3.5F, 5.0F).notSolid().sound(SoundType.GLASS).lightValue(5));
    }
}
