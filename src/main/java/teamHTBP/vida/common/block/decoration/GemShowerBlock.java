package teamHTBP.vida.common.block.decoration;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.api.common.block.IDecoBlock;
import teamHTBP.vida.common.block.base.VidaBaseEntityBlock;
import teamHTBP.vida.common.blockentity.GemShowerBlockEntity;
import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;

import javax.annotation.Nullable;

public class GemShowerBlock extends VidaBaseEntityBlock<GemShowerBlockEntity> implements IDecoBlock {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    VoxelShape base = Block.box(1, 0, 1, 15, 16, 15);
    VoxelShape high = Block.box(1, 0, 1, 15, 9, 15);


    public GemShowerBlock() {
        super(Properties.of(Material.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0f, 3.0f),
                VidaBlockEntityLoader.TileEntityGemShower
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HALF, DoubleBlockHalf.LOWER));
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return state.getValue(HALF) == DoubleBlockHalf.UPPER ? this.high : this.base;
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        DoubleBlockHalf doubleblockhalf = stateIn.getValue(HALF);
        if (facing.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
            return facingState.getBlock() == this && facingState.getValue(HALF) != doubleblockhalf ? stateIn.setValue(FACING, facingState.getValue(FACING)) : Blocks.AIR.defaultBlockState();
        } else {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

    @Override
    public void playerDestroy(Level worldIn, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
        super.playerDestroy(worldIn, player, pos, Blocks.AIR.defaultBlockState(), te, stack);
    }

    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        BlockPos blockpos = doubleblockhalf == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        BlockEntity te = null;
        if (doubleblockhalf == DoubleBlockHalf.LOWER)
            te = worldIn.getBlockEntity(pos);
        else
            te = worldIn.getBlockEntity(pos.below());
        if (te instanceof GemShowerBlockEntity) {
            GemShowerBlockEntity gemShowerBlockEntity = (GemShowerBlockEntity) te;
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), gemShowerBlockEntity.gemItem));
        }
        if (blockstate.getBlock() == this && blockstate.getValue(HALF) != doubleblockhalf) {
            worldIn.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
            worldIn.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            ItemStack itemstack = player.getMainHandItem();
            if (!worldIn.isClientSide && !player.isCreative() && state.canHarvestBlock(worldIn, pos, player)) {
                Block.dropResources(state, worldIn, pos, null, player, itemstack);
                Block.dropResources(blockstate, worldIn, blockpos, null, player, itemstack);
            }
        }

        super.playerWillDestroy(worldIn, pos, state, player);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        if (blockpos.getY() < 255 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context)) {
            Level world = context.getLevel();
            boolean flag = world.hasNeighborSignal(blockpos) || world.hasNeighborSignal(blockpos.above());
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection()).setValue(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    @Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        worldIn.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            return blockstate.isFaceSturdy(worldIn, blockpos, Direction.UP);
        } else {
            return blockstate.getBlock() == this;
        }
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public long getSeed(BlockState state, BlockPos pos) {
        return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (handIn == InteractionHand.MAIN_HAND && !worldIn.isClientSide) {
            GemShowerBlockEntity entity = null;
            BlockPos position = pos;
            if (worldIn.getBlockState(pos).getValue(HALF) == DoubleBlockHalf.LOWER)
                entity = (GemShowerBlockEntity) worldIn.getBlockEntity(pos);
            else {
                entity = (GemShowerBlockEntity) worldIn.getBlockEntity(pos.below());
                position = pos.below();
            }
            //如果是创造模式不消耗宝石
            if (!player.isShiftKeyDown()) {
                if (entity.setGem(player.getInventory().getSelected())) {
                    if (!player.isCreative()) {
                        ItemStack stack = player.getInventory().getSelected();
                        stack.setCount(stack.getCount() - 1);
                        worldIn.sendBlockUpdated(position, state, state, 3);
                    } else {
                        worldIn.sendBlockUpdated(position, state, state, 3);
                    }
                }
            } else {
                if (!entity.gemItem.isEmpty()) {
                    player.getInventory().add(entity.gemItem);
                    entity.gemItem = ItemStack.EMPTY;
                    worldIn.sendBlockUpdated(position, state, state, 3);
                }
            }
        } else {
            return super.use(state, worldIn, pos, player, handIn, hit);
        }
        return InteractionResult.SUCCESS;
    }


    @org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState state) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            return new GemShowerBlockEntity(pPos, state);
        }
        return null;
    }
}
