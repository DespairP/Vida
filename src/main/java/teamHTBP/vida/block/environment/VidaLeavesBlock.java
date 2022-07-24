package teamHTBP.vida.block.environment;

import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

/***
 * 生命树叶
 * @version 0.0.1
 * */
public class VidaLeavesBlock extends LeavesBlock {
    public VidaLeavesBlock() {
        //noSolid渲染碰撞材质
        super(Properties.of(Material.LEAVES).noOcclusion().strength(0.5f).randomTicks().sound(SoundType.GRASS));
        this.registerDefaultState(this.stateDefinition.any());

    }
}
