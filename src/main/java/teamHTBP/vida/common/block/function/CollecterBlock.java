package teamHTBP.vida.common.block.function;

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
import teamHTBP.vida.common.block.base.VidaBaseEntityBlock;
import teamHTBP.vida.common.blockentity.CollectorBlockEntity;
import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.common.item.VidaItemLoader;
import teamHTBP.vida.common.particle.type.VidaParticleTypeLoader;
import teamHTBP.vida.common.particle.option.base.BaseParticleOptions;

import java.util.Random;

public class CollecterBlock extends VidaBaseEntityBlock<CollectorBlockEntity> {
    public CollecterBlock() {
        super(Properties.of(Material.WOOD).noOcclusion().strength(3.5f), VidaBlockEntityLoader.TileEntityCollector);
    }


    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide) {
            CollectorBlockEntity collectorBlockEntity = (CollectorBlockEntity) worldIn.getBlockEntity(pos);
            if (handIn == InteractionHand.MAIN_HAND && !player.isShiftKeyDown()) {
                ItemStack stack = player.getInventory().getSelected();
                if (stack.getItem() == VidaItemLoader.ELEMENTCORE_VOID.get()) {
                    if (collectorBlockEntity.setCore(new ItemStack(stack.getItem(), 1))) {
                        if (!player.isCreative()) {
                            stack.shrink(1);
                        }
                        worldIn.sendBlockUpdated(pos, state, state, 3);
                        return InteractionResult.SUCCESS;
                    }
                }
            } else if (handIn == InteractionHand.MAIN_HAND && player.isShiftKeyDown()) {
                ItemStack stack = collectorBlockEntity.getCore();
                collectorBlockEntity.coreItem = ItemStack.EMPTY;
                player.addItem(stack);
                worldIn.sendBlockUpdated(pos, state, state, 3);
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        CollectorBlockEntity collectorBlockEntity = (CollectorBlockEntity) worldIn.getBlockEntity(pos);
        if (collectorBlockEntity != null && !worldIn.isClientSide) {
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), collectorBlockEntity.coreItem));
        }
        super.playerWillDestroy(worldIn, pos, state, player);
    }


    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        CollectorBlockEntity entity = (CollectorBlockEntity) worldIn.getBlockEntity(pos);
        if (entity != null) {
            if (entity.getCollection() > 0 && rand.nextDouble() >= 0.1D)
            //生成粒子
            {
                worldIn.addParticle(new BaseParticleOptions(VidaParticleTypeLoader.elementFireParticle.get(), 1 ,1,1,1,100), pos.getX() + 0.5f, pos.getY() + 1.0f, pos.getZ() + 0.5f, -0.01F, 0, -0.01F);

            }
        }

    }
}
