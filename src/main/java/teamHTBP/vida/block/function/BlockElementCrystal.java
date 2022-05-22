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

public abstract class BlockElementCrystal extends Block {
    public VoxelShape SHAPE;
    public int element = 0;

    public BlockElementCrystal(int element) {
        super(Properties.create(Material.GLASS).hardnessAndResistance(1.0F).notSolid().sound(SoundType.GLASS).setLightLevel((state) -> 15).notSolid());
        this.element = element;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        SHAPE = Block.makeCuboidShape(6, 6, 6, 12, 12, 12);
        return SHAPE;
    }


    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}
