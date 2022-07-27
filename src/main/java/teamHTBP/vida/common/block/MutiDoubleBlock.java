package teamHTBP.vida.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

/**
 * 有双层结构的方块,大多数代码来自于DoublePlantBlock
 * 一般用于普通无转向的方块
 * 如果有转向需要自己重写下
 * 植物DoublePlantBlock就足够了，多层建议参考下BambooBlock
 */
public abstract class MutiDoubleBlock extends Block {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    VoxelShape base = Block.box(1, 0, 1, 15, 16, 15);
    VoxelShape high = Block.box(1, 0, 1, 15, 9, 15);

    public MutiDoubleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER));
    }

    /**
     * 源自：DoublePlantBlock
     * 此方法用于在方块放置时，根据所提供的方块隔壁方块的state，更新自己的blockstate
     * [[有待考据]]
     */
    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        //得到放置方块的blockstate，传进Property得到是否是上和下的信息
        DoubleBlockHalf doubleblockhalf = stateIn.getValue(HALF);
        //如果方块的轴不为Y轴 或者 方块朝向和自己的state不符合，进行更新
        if (facing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.getBlock() == this && facingState.getValue(HALF) != doubleblockhalf) {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        } else {
            return Blocks.AIR.defaultBlockState();
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
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        //小于限制高度，其上方可以放置时发，进行放置
        if (!context.getLevel().isClientSide) {
            ServerLevel world = (ServerLevel) context.getLevel();
            return blockpos.getY() < world.getChunkSource().getGenerator().getGenDepth() - 1
                    && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context)
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
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        //放置上面的方块
        worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }

    /**
     * 是否是有效的位置
     */
    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        //如果blockState为Down则判定有效
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
            return super.canSurvive(state, worldIn, pos);
        } else {
            //获取下方的方块，然后判定是不是此方块，然后在判定是不是此方块
            BlockState blockstate = worldIn.getBlockState(pos.below());
            if (state.getBlock() != this)
                return super.canSurvive(state, worldIn, pos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return blockstate.getBlock() == this && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    /**
     * 生成方块掉落物，当Block.removedByPlayer被调用时，此方块可能已经被设置为空气-Air了
     */
    @Override
    public void playerDestroy(Level worldIn, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
        //直接调用父类方法
        super.playerDestroy(worldIn, player, pos, Blocks.AIR.defaultBlockState(), te, stack);
    }

    /**
     * 在被破坏前调用这个方法，注意和havestBlock的区别，无论玩家的武器能不能[[采集]]此方块都会被调用
     * 被调用的情况首先都是要方块被破坏
     */
    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF); //获取方块的blockstate
        BlockPos blockpos = doubleblockhalf == DoubleBlockHalf.LOWER ? pos.above() : pos.below();//获取上方位置或者下方位置
        BlockState blockstate = worldIn.getBlockState(blockpos); //获取当前位置的blockstate
        //判断这个位置是否是自己并且能够被破坏，然后进行破坏
        if (blockstate.getBlock() == this && blockstate.getValue(HALF) != doubleblockhalf) {
            worldIn.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
            worldIn.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            if (!worldIn.isClientSide && !player.isCreative()) {
                dropResources(state, worldIn, pos, null, player, player.getMainHandItem());
                dropResources(blockstate, worldIn, blockpos, null, player, player.getMainHandItem());
            }
        }

        super.playerWillDestroy(worldIn, pos, state, player);
    }

    /**
     * 注册Property，注意重写时要super一下
     */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF);
    }

    /**
     * 设置碰撞体积
     */
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return state.getValue(HALF) == DoubleBlockHalf.UPPER ? this.high : this.base;
    }
}
