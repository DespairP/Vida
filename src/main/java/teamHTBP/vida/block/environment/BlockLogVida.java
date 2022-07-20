package teamHTBP.vida.block.environment;

import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;

import net.minecraft.block.AbstractBlock.Properties;

/**
 * 生命原木
 * RotatedPillarBlock就是一个能根据玩家放置角度设置BlockState的类
 *
 * @Version 0.0.1
 **/
public class BlockLogVida extends RotatedPillarBlock {
    public BlockLogVida() {
        super(Properties.of(Material.WOOD).strength(2.0f));
    }
}
