package teamHTBP.vida.block.environment;

import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/***
 * 生命树叶
 * @version 0.0.1
 * */
public class BlockLeavesVida extends LeavesBlock {
    public BlockLeavesVida() {
        //noSolid渲染碰撞材质
        super(Properties.create(Material.LEAVES).notSolid().hardnessAndResistance(0.5f).tickRandomly().sound(SoundType.PLANT));
        this.setDefaultState(this.stateContainer.getBaseState());

    }
}
