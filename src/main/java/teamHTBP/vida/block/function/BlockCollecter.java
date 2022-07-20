package teamHTBP.vida.block.function;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import teamHTBP.vida.block.base.ModBaseEntityBlock;
import teamHTBP.vida.blockentity.TileEntityCollector;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.item.ItemLoader;
import teamHTBP.vida.particle.ParticleLoader;
import teamHTBP.vida.particle.util.BaseParticleData;

import java.util.Random;

public class BlockCollecter extends ModBaseEntityBlock<TileEntityCollector> {
    public BlockCollecter() {
        super(Properties.of(Material.WOOD).noOcclusion().strength(3.5f), TileEntityLoader.TileEntityCollector);
    }


    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide) {
            TileEntityCollector tileEntityCollector = (TileEntityCollector) worldIn.getBlockEntity(pos);
            if (handIn == InteractionHand.MAIN_HAND && !player.isShiftKeyDown()) {
                ItemStack stack = player.getInventory().getSelected();
                if (stack.getItem() == ItemLoader.ELEMENTCORE_VOID.get()) {
                    if (tileEntityCollector.setCore(new ItemStack(stack.getItem(), 1))) {
                        if (!player.isCreative()) {
                            stack.shrink(1);
                        }
                        worldIn.sendBlockUpdated(pos, state, state, 3);
                        return InteractionResult.SUCCESS;
                    }
                }
            } else if (handIn == InteractionHand.MAIN_HAND && player.isShiftKeyDown()) {
                ItemStack stack = tileEntityCollector.getCore();
                tileEntityCollector.coreItem = ItemStack.EMPTY;
                player.addItem(stack);
                worldIn.sendBlockUpdated(pos, state, state, 3);
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        TileEntityCollector tileEntityCollector = (TileEntityCollector) worldIn.getBlockEntity(pos);
        if (tileEntityCollector != null && !worldIn.isClientSide) {
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityCollector.coreItem));
        }
        super.playerWillDestroy(worldIn, pos, state, player);
    }


    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
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
