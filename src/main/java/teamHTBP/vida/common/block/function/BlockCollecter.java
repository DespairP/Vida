package teamHTBP.vida.common.block.function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import teamHTBP.vida.common.TileEntity.TileEntityCollector;
import teamHTBP.vida.common.item.ItemLoader;
import teamHTBP.vida.common.particle.ParticleLoader;
import teamHTBP.vida.common.particle.base.BaseParticleData;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockCollecter extends Block {
    public BlockCollecter() {
        super(Properties.create(Material.WOOD).notSolid().hardnessAndResistance(3.5f));
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
        if (!worldIn.isRemote) {
            TileEntityCollector tileEntityCollector = (TileEntityCollector) worldIn.getTileEntity(pos);
            if (handIn == Hand.MAIN_HAND && !player.isSneaking()) {
                ItemStack stack = player.inventory.getCurrentItem();
                if (stack.getItem() == ItemLoader.ELEMENTCORE_VOID.get()) {
                    if (tileEntityCollector.setCore(new ItemStack(stack.getItem(), 1))) {
                        if (!player.isCreative()) {
                            stack.shrink(1);
                        }
                        worldIn.notifyBlockUpdate(pos, state, state, 3);
                        return ActionResultType.SUCCESS;
                    }
                }
            } else if (handIn == Hand.MAIN_HAND && player.isSneaking()) {
                ItemStack stack = tileEntityCollector.getCore();
                tileEntityCollector.coreItem = ItemStack.EMPTY;
                player.addItemStackToInventory(stack);
                worldIn.notifyBlockUpdate(pos, state, state, 3);
                return ActionResultType.SUCCESS;
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        TileEntityCollector tileEntityCollector = (TileEntityCollector) worldIn.getTileEntity(pos);
        if (tileEntityCollector != null && !worldIn.isRemote) {
            worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityCollector.coreItem));
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }


    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        TileEntityCollector entity = (TileEntityCollector) worldIn.getTileEntity(pos);
        if (entity != null) {
            if (entity.getCollection() > 0 && rand.nextDouble() >= 0.1D)
            //生成粒子
            {
                worldIn.addParticle(new BaseParticleData(ParticleLoader.elementFireParticle.get(), 1 ,1,1,1,100), pos.getX() + 0.5f, pos.getY() + 1.0f, pos.getZ() + 0.5f, -0.01F, 0, -0.01F);

            }
        }

    }
}
