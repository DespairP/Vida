package teamHTBP.vida.Block.function;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.TileEntity.TileEntityPurfiedCauldron;

import javax.annotation.Nullable;

/*元素祭坛
@Version 0.0.1*/
public class BlockElementCoreAltar extends Block {
    public BlockElementCoreAltar(){
        super(Properties.create(Material.IRON).notSolid().hardnessAndResistance(5.0f, 5.0f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE));

    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityElementCoreAltar();
    }



}
