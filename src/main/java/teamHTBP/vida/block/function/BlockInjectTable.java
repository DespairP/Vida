package teamHTBP.vida.block.function;

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
import teamHTBP.vida.block.base.ModBaseEntityBlock;
import teamHTBP.vida.blockentity.TileEntityInjectTable;
import teamHTBP.vida.blockentity.TileEntityLoader;

public class BlockInjectTable extends ModBaseEntityBlock<TileEntityInjectTable> {
    public BlockInjectTable() {
        super(Properties.of(Material.STONE).strength(3.0f, 3.0f)
                // todo tag .harvestTool(ToolType.PICKAXE).harvestLevel(1)
                .noOcclusion().sound(SoundType.STONE), TileEntityLoader.TileEntityInjectTable);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide) {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof TileEntityInjectTable tileEntityInjectTable) {
                if (!player.isShiftKeyDown() && handIn == InteractionHand.MAIN_HAND) {
                    if (tileEntityInjectTable.setSwordItem(player.getItemInHand(InteractionHand.MAIN_HAND)) && player.getItemInHand(InteractionHand.MAIN_HAND) != ItemStack.EMPTY) {
                        player.getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
                        worldIn.sendBlockUpdated(pos, state, state, 3);
                        return InteractionResult.SUCCESS;
                    } else if (tileEntityInjectTable.hasSwordItem()) {
                        NetworkHooks.openGui((ServerPlayer) player, tileEntityInjectTable, (FriendlyByteBuf packerBuffer) -> {
                            packerBuffer.writeItem(tileEntityInjectTable.getSwordStack());
                            packerBuffer.writeBlockPos(tileEntityInjectTable.getBlockPos());
                        });
                        return InteractionResult.SUCCESS;
                    }
                } else if (player.isShiftKeyDown()) {
                    player.addItem(tileEntityInjectTable.getSwordStackToPlayer());
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
