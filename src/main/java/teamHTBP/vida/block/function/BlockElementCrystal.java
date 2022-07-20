package teamHTBP.vida.block.function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import net.minecraft.block.AbstractBlock.Properties;

public abstract class BlockElementCrystal extends Block {
    public VoxelShape SHAPE;
    public int element = 0;

    public BlockElementCrystal(int element) {
        super(Properties.of(Material.GLASS).strength(1.0F).noOcclusion().sound(SoundType.GLASS).lightLevel((state) -> 15).noOcclusion());
        this.element = element;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        SHAPE = Block.box(6, 6, 6, 12, 12, 12);
        return SHAPE;
    }


    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}
