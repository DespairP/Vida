package teamHTBP.vida.Block.function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import teamHTBP.vida.Item.ItemElementCoreVoid;
import teamHTBP.vida.Item.ItemLoader;
import teamHTBP.vida.TileEntity.TileEntityCollector;
import teamHTBP.vida.TileEntity.TileEntityOreReationMachine;
import teamHTBP.vida.TileEntity.TileEntityPurfiedCauldron;
import teamHTBP.vida.particle.CubeParticleData;
import teamHTBP.vida.particle.ElementFireParticleData;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockCollecter extends Block {
    public BlockCollecter() {
        super(Properties.create(Material.WOOD).notSolid().hardnessAndResistance(3.0f,3.0f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityCollector();
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            TileEntityCollector tileEntityCollector = (TileEntityCollector) worldIn.getTileEntity(pos);
            if(handIn == Hand.MAIN_HAND && !player.isSneaking()){
               ItemStack stack = player.inventory.getCurrentItem();
               if(stack.getItem() == ItemLoader.voidElementCore.get())
               {
                  if(tileEntityCollector.setCore(new ItemStack(stack.getItem(),1))){
                      if(!player.isCreative()){
                          stack.shrink(1);
                      }
                      worldIn.notifyBlockUpdate(pos,state,state,3);
                      return ActionResultType.SUCCESS;
                  }
               }
            }else if(handIn == Hand.MAIN_HAND && player.isSneaking()){
                ItemStack stack =  tileEntityCollector.getCore();
                player.addItemStackToInventory(stack);
                worldIn.notifyBlockUpdate(pos,state,state,3);
                return ActionResultType.SUCCESS;
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }


    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        TileEntityCollector entity = (TileEntityCollector) worldIn.getTileEntity(pos);
        if(entity!=null){
            if(entity.isCollect && rand.nextDouble()>=0.1D)
            //生成粒子
            {
                worldIn.addParticle(new ElementFireParticleData(-0.01F, 0, -0.01F), pos.getX() + 0.5f, pos.getY() + 1.0f, pos.getZ() + 0.5f, 0, 0, 0);

            }
        }

    }
}
