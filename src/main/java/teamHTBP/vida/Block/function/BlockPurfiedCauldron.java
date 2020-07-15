package teamHTBP.vida.Block.function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.settings.ParticleStatus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.DebugPacketSender;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Constants;
import teamHTBP.vida.TileEntity.TileEntityPurfiedCauldron;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockPurfiedCauldron extends Block {
    protected static VoxelShape SHAPE = Block.makeCuboidShape(-2.0D, 0.0D, -2.0D, 18.0D, 2.0D, 18.0D);


    public BlockPurfiedCauldron() {
        super(Block.Properties.create(Material.IRON).tickRandomly().notSolid().sound(SoundType.STONE));

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


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(handIn == Hand.MAIN_HAND) {
            TileEntityPurfiedCauldron entity = (TileEntityPurfiedCauldron) worldIn.getTileEntity(pos);
            //如果是创造模式不消耗水桶
            //如果是就消耗水桶
            if ( player.inventory.getCurrentItem().getItem() == Items.WATER_BUCKET && !entity.isWater){
               if(((PlayerEntity)player).isCreative()){
                 entity.isWater = true;
               }else {
                   player.inventory.getCurrentItem().setCount(0);
                   player.inventory.addItemStackToInventory(new ItemStack(
                           Items.BUCKET, 1));
                   entity.isWater = true;
                   worldIn.notifyBlockUpdate(pos, state, state, Constants.BlockFlags.BLOCK_UPDATE);
               }
            }

        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if(worldIn.getBlockState(pos.down()).getBlock() == Blocks.FIRE && !worldIn.isRemote){
            TileEntityPurfiedCauldron entity = (TileEntityPurfiedCauldron) worldIn.getTileEntity(pos);
            entity.isFire = true;
            worldIn.notifyBlockUpdate(pos, state, state, Constants.BlockFlags.BLOCK_UPDATE);
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos,isMoving);
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
            TileEntityPurfiedCauldron entity = (TileEntityPurfiedCauldron) worldIn.getTileEntity(pos);
            if(entity!=null){
               //生成粒子
            }

    }


    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if(!worldIn.isRemote){
            if(entityIn instanceof ItemEntity){
                System.out.println("s");
                TileEntityPurfiedCauldron entity = (TileEntityPurfiedCauldron) worldIn.getTileEntity(pos);
                ItemStack itemStack = ((ItemEntity)entityIn).getItem();
                if(entity.setMeltItem(itemStack)){
                    entityIn.remove();
                    worldIn.notifyBlockUpdate(pos, state, state, Constants.BlockFlags.BLOCK_UPDATE);


                }
            }
        }
        super.onEntityCollision(state, worldIn, pos, entityIn);
    }
}
