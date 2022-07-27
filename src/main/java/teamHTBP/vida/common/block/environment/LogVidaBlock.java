package teamHTBP.vida.common.block.environment;

import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;

/**
 * 生命原木
 * RotatedPillarBlock就是一个能根据玩家放置角度设置BlockState的类
 *
 * @Version 0.0.1
 **/
public class LogVidaBlock extends RotatedPillarBlock {
    public LogVidaBlock() {
        super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
    }
}
