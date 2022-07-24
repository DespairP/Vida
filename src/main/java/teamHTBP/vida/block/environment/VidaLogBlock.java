package teamHTBP.vida.block.environment;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.material.Material;

/**
 * 生命原木
 * RotatedPillarBlock就是一个能根据玩家放置角度设置BlockState的类
 *
 * @Version 0.0.1
 **/
public class VidaLogBlock extends RotatedPillarBlock {
    public VidaLogBlock() {
        super(Properties.of(Material.WOOD).strength(2.0f));
    }
}
