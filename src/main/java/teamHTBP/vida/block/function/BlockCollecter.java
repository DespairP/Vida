package teamHTBP.vida.block.function;

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
import teamHTBP.vida.TileEntity.TileEntityCollector;
import teamHTBP.vida.item.ItemLoader;
import teamHTBP.vida.particle.ParticleLoader;
import teamHTBP.vida.particle.util.BaseParticleData;

import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class BlockCollecter extends Block {
    public BlockCollecter() {
        super(Properties.of(Material.WOOD).noOcclusion().strength(3.5f));
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
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide) {
            TileEntityCollector tileEntityCollector = (TileEntityCollector) worldIn.getBlockEntity(pos);
            if (handIn == Hand.MAIN_HAND && !player.isShiftKeyDown()) {
                ItemStack stack = player.inventory.getSelected();
                if (stack.getItem() == ItemLoader.ELEMENTCORE_VOID.get()) {
                    if (tileEntityCollector.setCore(new ItemStack(stack.getItem(), 1))) {
                        if (!player.isCreative()) {
                            stack.shrink(1);
                        }
                        worldIn.sendBlockUpdated(pos, state, state, 3);
                        return ActionResultType.SUCCESS;
                    }
                }
            } else if (handIn == Hand.MAIN_HAND && player.isShiftKeyDown()) {
                ItemStack stack = tileEntityCollector.getCore();
                tileEntityCollector.coreItem = ItemStack.EMPTY;
                player.addItem(stack);
                worldIn.sendBlockUpdated(pos, state, state, 3);
                return ActionResultType.SUCCESS;
            }
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        TileEntityCollector tileEntityCollector = (TileEntityCollector) worldIn.getBlockEntity(pos);
        if (tileEntityCollector != null && !worldIn.isClientSide) {
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityCollector.coreItem));
        }
        super.playerWillDestroy(worldIn, pos, state, player);
    }


    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        TileEntityCollector entity = (TileEntityCollector) worldIn.getBlockEntity(pos);
        if (entity != null) {
            if (entity.getCollection() > 0 && rand.nextDouble() >= 0.1D)
            //生成粒子
            {
                worldIn.addParticle(new BaseParticleData(ParticleLoader.elementFireParticle.get(), 1 ,1,1,1,100), pos.getX() + 0.5f, pos.getY() + 1.0f, pos.getZ() + 0.5f, -0.01F, 0, -0.01F);

            }
        }

    }
}
