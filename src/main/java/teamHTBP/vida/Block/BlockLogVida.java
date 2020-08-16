package teamHTBP.vida.Block;

import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;

/**
 * 生命原木
 * RotatedPillarBlock就是一个能根据玩家放置角度设置BlockState的类
 * @Version 0.0.1
 * **/
public class BlockLogVida extends RotatedPillarBlock {
    public BlockLogVida() {
        super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
    }


}
