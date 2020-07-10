package teamHTBP.vida.Block;

import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLeavesVida extends LeavesBlock {
    public BlockLeavesVida() {
        super(Properties.create(Material.LEAVES).notSolid().sound(SoundType.GROUND));
        this.setDefaultState(this.stateContainer.getBaseState());
    }
}
