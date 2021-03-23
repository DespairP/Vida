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
import net.minecraftforge.common.ToolType;
import teamHTBP.vida.TileEntity.TileEntityGemShower;

import javax.annotation.Nullable;

public class BlockGemShower extends Block {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    VoxelShape base = Block.makeCuboidShape(1,0,1, 15, 16, 15);;
    VoxelShape high = Block.makeCuboidShape(1, 0, 1, 15, 9, 15);


    public BlockGemShower() {
        super(Properties.create(Material.WOOD).sound(SoundType.WOOD).notSolid().hardnessAndResistance(2.0f, 3.0f).harvestTool(ToolType.AXE));
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(HALF, DoubleBlockHalf.LOWER));
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
       return state.get(HALF) == DoubleBlockHalf.UPPER?this.high:this.base;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        DoubleBlockHalf doubleblockhalf = stateIn.get(HALF);
        if (facing.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
            return facingState.getBlock() == this && facingState.get(HALF) != doubleblockhalf ? stateIn.with(FACING, facingState.get(FACING)) : Blocks.AIR.getDefaultState();
        } else {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, Blocks.AIR.getDefaultState(), te, stack);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        DoubleBlockHalf doubleblockhalf = state.get(HALF);
        BlockPos blockpos = doubleblockhalf == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        TileEntity te = null;
        if(doubleblockhalf == DoubleBlockHalf.LOWER)
        te = worldIn.getTileEntity(pos);
        else
        te = worldIn.getTileEntity(pos.down());
        if(te instanceof TileEntityGemShower){
            TileEntityGemShower tileEntityGemShower = (TileEntityGemShower)te;
            worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityGemShower.gemItem));
        }
        if (blockstate.getBlock() == this && blockstate.get(HALF) != doubleblockhalf) {
            worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
            worldIn.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
            ItemStack itemstack = player.getHeldItemMainhand();
            if (!worldIn.isRemote && !player.isCreative() && player.canHarvestBlock(blockstate)) {
                Block.spawnDrops(state, worldIn, pos, (TileEntity)null, player, itemstack);
                Block.spawnDrops(blockstate, worldIn, blockpos, (TileEntity)null, player, itemstack);
            }
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        if (blockpos.getY() < 255 && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context)) {
            World world = context.getWorld();
            boolean flag = world.isBlockPowered(blockpos) || world.isBlockPowered(blockpos.up());
            return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        worldIn.setBlockState(pos.up(), state.with(HALF, DoubleBlockHalf.UPPER), 3);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        if (state.get(HALF) == DoubleBlockHalf.LOWER) {
            return blockstate.isSolidSide(worldIn, blockpos, Direction.UP);
        } else {
            return blockstate.getBlock() == this;
        }
    }


    @OnlyIn(Dist.CLIENT)
    public long getPositionRandom(BlockState state, BlockPos pos) {
        return MathHelper.getCoordinateRandom(pos.getX(), pos.down(state.get(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
    }


    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
           if(state.get(HALF) == DoubleBlockHalf.LOWER) return new TileEntityGemShower();
        return null;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(handIn == Hand.MAIN_HAND && !worldIn.isRemote) {
            TileEntityGemShower entity = null;
            BlockPos position = pos;
            if(worldIn.getBlockState(pos).get(HALF) == DoubleBlockHalf.LOWER)
                entity = (TileEntityGemShower) worldIn.getTileEntity(pos);
            else
            {entity = (TileEntityGemShower) worldIn.getTileEntity(pos.down());
               position = pos.down();}
            //如果是创造模式不消耗宝石
            if(!player.isSneaking()){
            if ( entity.setGem(player.inventory.getCurrentItem()) ) {
                if (!((PlayerEntity) player).isCreative()) {
                    ItemStack stack = player.inventory.getCurrentItem();
                    stack.setCount(stack.getCount() - 1);
                    worldIn.notifyBlockUpdate(position, state, state, 3);
                }else{
                    worldIn.notifyBlockUpdate(position, state, state, 3);
                }}}else {
                if(!entity.gemItem.isEmpty()){
                    player.inventory.addItemStackToInventory(entity.gemItem);
                    entity.gemItem = ItemStack.EMPTY;
                    worldIn.notifyBlockUpdate(position, state, state, 3);
                } } }else{
                return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
            }
        return ActionResultType.SUCCESS;
    }


}
