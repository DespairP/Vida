package teamHTBP.vida.block.function;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import teamHTBP.vida.TileEntity.TileEntityPurfiedCauldron;
import teamHTBP.vida.particle.CubeParticleData;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockPurfiedCauldron extends Block {
    private static VoxelShape SHAPE;

    static {
        VoxelShape aPillar = Block.makeCuboidShape(0.25, 2, 0.25, 2.25, 12, 2.25);//↖
        VoxelShape aBase = Block.makeCuboidShape(0, 0, 0, 3, 3, 3);//↖基底
        VoxelShape upBase = Block.makeCuboidShape(2, 1.75, 1, 14, 10.75, 2);
        VoxelShape bPillar = Block.makeCuboidShape(13.75, 2, 0.25,15.75,12,2.25);//↗
        VoxelShape bBase = Block.makeCuboidShape(13, 0, 0, 16, 3, 3);//↗基底
        VoxelShape rightBase = Block.makeCuboidShape(14,1.75,2,15,10.75,14);
        VoxelShape cPillar = Block.makeCuboidShape(13.75, 2,13.75,15.75,12,15.75);//↘
        VoxelShape cBase = Block.makeCuboidShape(13,0,13,16,3,16);//↘基底
        VoxelShape downBase = Block.makeCuboidShape(2,1.75,14,14,10.75,15);
        VoxelShape dPillar = Block.makeCuboidShape(0.25,2,13.75,2.25,12,15.75);//↙
        VoxelShape dBase = Block.makeCuboidShape(0,0,13,3,3,16);//↙基底
        VoxelShape leftBase = Block.makeCuboidShape(1,1.75,2,2,10.75,14);
        VoxelShape base = Block.makeCuboidShape(1,0.75,1,15,1.75,15);
        SHAPE = VoxelShapes.or(aPillar,aBase,upBase,bPillar,bBase,rightBase,cPillar,cBase,downBase,dPillar,dBase,leftBase,base);
    }

    public BlockPurfiedCauldron() {
        super(Block.Properties.create(Material.IRON).tickRandomly().notSolid().sound(SoundType.STONE).hardnessAndResistance(4.0f,4.0f));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean hasTileEntity(BlockState state) { return true; }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) { return new TileEntityPurfiedCauldron(); }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(handIn == Hand.MAIN_HAND ) {
            TileEntityPurfiedCauldron entity = (TileEntityPurfiedCauldron) worldIn.getTileEntity(pos);
            //如果是创造模式不消耗水桶
            //如果是就消耗水桶
            if ( player.inventory.getCurrentItem().getItem() == Items.WATER_BUCKET && !entity.isWater){
               if(((PlayerEntity)player).isCreative()){
                 entity.isWater = true;
                 worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                 return ActionResultType.SUCCESS;
               }else {
                   player.inventory.getCurrentItem().setCount(0);
                   player.inventory.addItemStackToInventory(new ItemStack(
                           Items.BUCKET, 1));
                   entity.isWater = true;
                   worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                   worldIn.notifyBlockUpdate(pos, state, state, Constants.BlockFlags.BLOCK_UPDATE);
                   return ActionResultType.SUCCESS;
               }
            }

        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if(worldIn.getBlockState(pos.down()).getBlock() == Blocks.FIRE|| worldIn.getBlockState(pos.down()).getBlock() == Blocks.LAVA && !worldIn.isRemote){
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
                if(!entity.meltItem.isEmpty() && rand.nextDouble()>=0.65)
               //生成粒子
                {
                   double speedX =rand.nextBoolean()? 0 + rand.nextFloat()/1000.0F : 0 - rand.nextFloat()/1000.0F;
                   double speedY =-0.025+rand.nextFloat()/1000f;
                   double speedZ =rand.nextBoolean()? 0  + rand.nextFloat()/1000.0f :0  - rand.nextFloat()/1000.0f;
                   double posX = rand.nextBoolean()? pos.getX() + 0.5f + rand.nextFloat()/4: pos.getX() + 0.5f - rand.nextFloat()/4;
                   double posY = rand.nextBoolean()? pos.getY() + 1.2F + rand.nextFloat()/4: pos.getY() + 1.2F - rand.nextFloat()/4;
                   double posZ = rand.nextBoolean()? pos.getZ() + 0.5f  + rand.nextFloat()/4:pos.getZ() + 0.5f - rand.nextFloat()/4;
                   float r = 1;
                   float g = 1;
                   float b = 1;
                   switch (entity.element){
                       case 1: r = 255; g = 255; b = 200;break;
                       case 2: r = 73 ; g = 175; b = 92;break;
                       case 3: r = 73 ; g = 203; b = 255;break;
                       case 4: r = 255; g = 30 ; b = 43 ;break;
                       case 5: r = 186; g = 184; b = 111;break;
                   }
                worldIn.addParticle(new CubeParticleData( speedX, speedY,speedZ , r, g, b ,0.02f), posX, posY, posZ, 0, -0.03, 0);

                }
            }

    }


    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if(!worldIn.isRemote){
            if(entityIn instanceof ItemEntity){
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
