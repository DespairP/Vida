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

public class BlockLogVida extends RotatedPillarBlock {
    public BlockLogVida() {
        super(Properties.create(Material.WOOD));
    }


}
