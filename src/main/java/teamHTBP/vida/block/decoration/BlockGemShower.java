package teamHTBP.vida.block.decoration;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ToolType;
import teamHTBP.vida.TileEntity.TileEntityGemShower;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock.Properties;

public class BlockGemShower extends Block {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    VoxelShape base = Block.box(1, 0, 1, 15, 16, 15);
    VoxelShape high = Block.box(1, 0, 1, 15, 9, 15);


    public BlockGemShower() {
        super(Properties.of(Material.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0f, 3.0f).harvestTool(ToolType.AXE));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HALF, DoubleBlockHalf.LOWER));
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.getValue(HALF) == DoubleBlockHalf.UPPER ? this.high : this.base;
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        DoubleBlockHalf doubleblockhalf = stateIn.getValue(HALF);
        if (facing.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
            return facingState.getBlock() == this && facingState.getValue(HALF) != doubleblockhalf ? stateIn.setValue(FACING, facingState.getValue(FACING)) : Blocks.AIR.defaultBlockState();
        } else {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

    public void playerDestroy(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.playerDestroy(worldIn, player, pos, Blocks.AIR.defaultBlockState(), te, stack);
    }

    @Override
    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        BlockPos blockpos = doubleblockhalf == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        TileEntity te = null;
        if (doubleblockhalf == DoubleBlockHalf.LOWER)
            te = worldIn.getBlockEntity(pos);
        else
            te = worldIn.getBlockEntity(pos.below());
        if (te instanceof TileEntityGemShower) {
            TileEntityGemShower tileEntityGemShower = (TileEntityGemShower) te;
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityGemShower.gemItem));
        }
        if (blockstate.getBlock() == this && blockstate.getValue(HALF) != doubleblockhalf) {
            worldIn.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
            worldIn.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            ItemStack itemstack = player.getMainHandItem();
            if (!worldIn.isClientSide && !player.isCreative() && ForgeHooks.canHarvestBlock(state, player, worldIn, pos)) {
                Block.dropResources(state, worldIn, pos, null, player, itemstack);
                Block.dropResources(blockstate, worldIn, blockpos, null, player, itemstack);
            }
        }

        super.playerWillDestroy(worldIn, pos, state, player);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getClickedPos();
        if (blockpos.getY() < 255 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context)) {
            World world = context.getLevel();
            boolean flag = world.hasNeighborSignal(blockpos) || world.hasNeighborSignal(blockpos.above());
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection()).setValue(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        worldIn.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }

    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            return blockstate.isFaceSturdy(worldIn, blockpos, Direction.UP);
        } else {
            return blockstate.getBlock() == this;
        }
    }


    @OnlyIn(Dist.CLIENT)
    public long getSeed(BlockState state, BlockPos pos) {
        return MathHelper.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
    }


    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) return new TileEntityGemShower();
        return null;
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (handIn == Hand.MAIN_HAND && !worldIn.isClientSide) {
            TileEntityGemShower entity = null;
            BlockPos position = pos;
            if (worldIn.getBlockState(pos).getValue(HALF) == DoubleBlockHalf.LOWER)
                entity = (TileEntityGemShower) worldIn.getBlockEntity(pos);
            else {
                entity = (TileEntityGemShower) worldIn.getBlockEntity(pos.below());
                position = pos.below();
            }
            //如果是创造模式不消耗宝石
            if (!player.isShiftKeyDown()) {
                if (entity.setGem(player.inventory.getSelected())) {
                    if (!player.isCreative()) {
                        ItemStack stack = player.inventory.getSelected();
                        stack.setCount(stack.getCount() - 1);
                        worldIn.sendBlockUpdated(position, state, state, 3);
                    } else {
                        worldIn.sendBlockUpdated(position, state, state, 3);
                    }
                }
            } else {
                if (!entity.gemItem.isEmpty()) {
                    player.inventory.add(entity.gemItem);
                    entity.gemItem = ItemStack.EMPTY;
                    worldIn.sendBlockUpdated(position, state, state, 3);
                }
            }
        } else {
            return super.use(state, worldIn, pos, player, handIn, hit);
        }
        return ActionResultType.SUCCESS;
    }


}
