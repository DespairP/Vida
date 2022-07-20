package teamHTBP.vida.block.environment;

import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import net.minecraft.block.AbstractBlock.Properties;

/***
 * 生命树叶
 * @version 0.0.1
 * */
public class BlockLeavesVida extends LeavesBlock {
    public BlockLeavesVida() {
        //noSolid渲染碰撞材质
        super(Properties.of(Material.LEAVES).noOcclusion().strength(0.5f).randomTicks().sound(SoundType.GRASS));
        this.registerDefaultState(this.stateDefinition.any());

    }
}
