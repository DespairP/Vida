package teamHTBP.vida.Block.function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import teamHTBP.vida.TileEntity.TileEntityPurfiedCauldron;

import javax.annotation.Nullable;

public class BlockPurfiedCauldron extends Block {
    protected static VoxelShape SHAPE = Block.makeCuboidShape(-2.0D, -0.0D, -2.0D, 18.0D, 16.0D, 18.0D);



    public BlockPurfiedCauldron() {
        super(Block.Properties.create(Material.IRON).notSolid().sound(SoundType.STONE));

    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityPurfiedCauldron();
    }

}
