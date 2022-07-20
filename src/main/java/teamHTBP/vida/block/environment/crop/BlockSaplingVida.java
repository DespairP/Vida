package teamHTBP.vida.block.environment.crop;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import teamHTBP.vida.worldGen.VidaTree;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class BlockSaplingVida extends BushBlock implements IGrowable {
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
    private final VidaTree tree;

    public BlockSaplingVida() {
        super(Properties.of(Material.DECORATION).noCollission().randomTicks().noOcclusion().strength(0.5f).sound(SoundType.GRAVEL));
        this.tree = new VidaTree();

    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return (double) worldIn.random.nextFloat() < 0.65D;
    }

    @Override
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        if (state.getValue(STAGE) == 0) {
            worldIn.setBlock(pos, state.cycle(STAGE), 4);
        } else {
            tree.growTree(worldIn, worldIn.getChunkSource().getGenerator(), pos, state, rand);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }
}
