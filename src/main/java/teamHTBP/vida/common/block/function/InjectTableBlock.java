package teamHTBP.vida.common.block.function;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import teamHTBP.vida.common.block.base.VidaBaseEntityBlock;
import teamHTBP.vida.common.blockentity.InjectTableBlockEntity;
import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;

public class InjectTableBlock extends VidaBaseEntityBlock<InjectTableBlockEntity> {
    public InjectTableBlock() {
        super(Properties.of(Material.STONE).strength(3.0f, 3.0f)
                .noOcclusion().sound(SoundType.STONE), VidaBlockEntityLoader.TileEntityInjectTable);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide) {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof InjectTableBlockEntity injectTableBlockEntity) {
                if (!player.isShiftKeyDown() && handIn == InteractionHand.MAIN_HAND) {
                    if (injectTableBlockEntity.setSwordItem(player.getItemInHand(InteractionHand.MAIN_HAND)) && player.getItemInHand(InteractionHand.MAIN_HAND) != ItemStack.EMPTY) {
                        player.getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
                        worldIn.sendBlockUpdated(pos, state, state, 3);
                        return InteractionResult.SUCCESS;
                    } else if (injectTableBlockEntity.hasSwordItem()) {
                        NetworkHooks.openGui((ServerPlayer) player, injectTableBlockEntity, (FriendlyByteBuf packerBuffer) -> {
                            packerBuffer.writeItem(injectTableBlockEntity.getSwordStack());
                            packerBuffer.writeBlockPos(injectTableBlockEntity.getBlockPos());
                        });
                        return InteractionResult.SUCCESS;
                    }
                } else if (player.isShiftKeyDown()) {
                    player.addItem(injectTableBlockEntity.getSwordStackToPlayer());
                    worldIn.sendBlockUpdated(pos, state, state, 3);
                    return InteractionResult.SUCCESS;
                }
            }
        } else {
            return InteractionResult.CONSUME;
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}
