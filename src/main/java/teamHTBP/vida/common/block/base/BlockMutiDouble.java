package teamHTBP.vida.common.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

/**
 * 有双层结构的方块,大多数代码来自于DoublePlantBlock
 * 一般用于普通无转向的方块
 * 如果有转向需要自己重写下
 * 植物DoublePlantBlock就足够了，多层建议参考下BambooBlock
 */
public abstract class BlockMutiDouble extends Block {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    VoxelShape base = Block.makeCuboidShape(1, 0, 1, 15, 16, 15);
    VoxelShape high = Block.makeCuboidShape(1, 0, 1, 15, 9, 15);

    public BlockMutiDouble(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.LOWER));
    }

    /**
     * 源自：DoublePlantBlock
     * 此方法用于在方块放置时，根据所提供的方块隔壁方块的state，更新自己的blockstate
     * [[有待考据]]
     */
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        //得到放置方块的blockstate，传进Property得到是否是上和下的信息
        DoubleBlockHalf doubleblockhalf = stateIn.get(HALF);
        //如果方块的轴不为Y轴 或者 方块朝向和自己的state不符合，进行更新
        if (facing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.getBlock() == this && facingState.get(HALF) != doubleblockhalf) {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        } else {
            return Blocks.AIR.getDefaultState();
        }
    }

    /**
     * 当使用物品（方块）时调用此方法
     * 常用于判定是否适合放置
     *
     * @return blockstate, null不能放置
     */
    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        //小于限制高度，其上方可以放置时发，进行放置
        if (!context.getWorld().isRemote) {
            ServerWorld world = (ServerWorld) context.getWorld();
            return blockpos.getY() < world.getChunkProvider().generator.getMaxBuildHeight() - 1
                    && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context)
                    ? super.getStateForPlacement(context) : null;
        }
        return null;
    }

    /**
     * 当ItemBlock能被放置时，会使用这个方法
     * 所以放置时会使上方的方块进行放置...
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        //放置上面的方块
        worldIn.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), 3);
    }

    /**
     * 是否是有效的位置
     */
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        //如果blockState为Down则判定有效
        if (state.get(HALF) != DoubleBlockHalf.UPPER) {
            return super.isValidPosition(state, worldIn, pos);
        } else {
            //获取下方的方块，然后判定是不是此方块，然后在判定是不是此方块
            BlockState blockstate = worldIn.getBlockState(pos.down());
            if (state.getBlock() != this)
                return super.isValidPosition(state, worldIn, pos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return blockstate.getBlock() == this && blockstate.get(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    /**
     * 生成方块掉落物，当Block.removedByPlayer被调用时，此方块可能已经被设置为空气-Air了
     */
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        //直接调用父类方法
        super.harvestBlock(worldIn, player, pos, Blocks.AIR.getDefaultState(), te, stack);
    }

    /**
     * 在被破坏前调用这个方法，注意和havestBlock的区别，无论玩家的武器能不能[[采集]]此方块都会被调用
     * 被调用的情况首先都是要方块被破坏
     */
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        DoubleBlockHalf doubleblockhalf = state.get(HALF); //获取方块的blockstate
        BlockPos blockpos = doubleblockhalf == DoubleBlockHalf.LOWER ? pos.up() : pos.down();//获取上方位置或者下方位置
        BlockState blockstate = worldIn.getBlockState(blockpos); //获取当前位置的blockstate
        //判断这个位置是否是自己并且能够被破坏，然后进行破坏
        if (blockstate.getBlock() == this && blockstate.get(HALF) != doubleblockhalf) {
            worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
            worldIn.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
            if (!worldIn.isRemote && !player.isCreative()) {
                spawnDrops(state, worldIn, pos, null, player, player.getHeldItemMainhand());
                spawnDrops(blockstate, worldIn, blockpos, null, player, player.getHeldItemMainhand());
            }
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    /**
     * 注册Property，注意重写时要super一下
     */
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF);
    }

    /**
     * 设置碰撞体积
     */
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.get(HALF) == DoubleBlockHalf.UPPER ? this.high : this.base;
    }
}
