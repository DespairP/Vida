package teamHTBP.vida.common.block.environment.crop;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import teamHTBP.vida.common.world.generation.VidaTreeGrower;

import java.util.Random;

public class VidaSaplingBlock extends BushBlock implements BonemealableBlock {
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
    private final VidaTreeGrower tree;

    public VidaSaplingBlock() {
        super(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().randomTicks().noOcclusion().strength(0.5f).sound(SoundType.GRAVEL));
        tree = new VidaTreeGrower();
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
        return (double) worldIn.random.nextFloat() < 0.65D;
    }

    @Override
    public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {
        if (state.getValue(STAGE) == 0) {
            worldIn.setBlock(pos, state.cycle(STAGE), 4);
        } else {
            tree.growTree(worldIn, worldIn.getChunkSource().getGenerator(), pos, state, rand);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }
}
