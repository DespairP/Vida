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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
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
import teamHTBP.vida.particle.CubeParticleData;
import teamHTBP.vida.particle.CubeParticleType;
import teamHTBP.vida.particle.ParticleLoader;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockPurfiedCauldron extends Block {
    protected static VoxelShape SHAPE = Block.makeCuboidShape(-2.0D, 0.0D, -2.0D, 18.0D, 2.0D, 18.0D);


    public BlockPurfiedCauldron() {
        super(Block.Properties.create(Material.IRON).tickRandomly().notSolid().sound(SoundType.STONE));
        //TODO 锅的体积
        //TODO 没有元素投入物品会消失

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
                   player.playSound(SoundEvents.BLOCK_BAMBOO_FALL,1.0F,1F);
                   worldIn.notifyBlockUpdate(pos, state, state, Constants.BlockFlags.BLOCK_UPDATE);
               }
            }

        }

        return ActionResultType.SUCCESS;
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
