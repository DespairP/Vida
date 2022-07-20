package teamHTBP.vida.block.function;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import teamHTBP.vida.block.base.ModBaseEntityBlock;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.blockentity.TileEntityOreReationMachine;

public class BlockOreReactionMachine extends ModBaseEntityBlock<TileEntityOreReationMachine> {
    public BlockOreReactionMachine() {
        super(Properties.of(Material.STONE).noOcclusion().strength(4.0f, 4.0f),
                TileEntityLoader.TileEntityOreReationMachine);
    }

    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        TileEntityOreReationMachine tileEntityOreReationMachine = (TileEntityOreReationMachine) worldIn.getBlockEntity(pos);
        if (tileEntityOreReationMachine != null) {
            for (int i = 0; i < 4; i++)
                worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityOreReationMachine.getSmeltSlotInv().getItem(i)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityOreReationMachine.getFuelInv().getItem(0)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityOreReationMachine.getCompleteSlot().getItem(0)));

        }
        super.playerWillDestroy(worldIn, pos, state, player);
    }


    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND) {
            TileEntityOreReationMachine tileEntityOreReationMachine = (TileEntityOreReationMachine) worldIn.getBlockEntity(pos);
            NetworkHooks.openGui((ServerPlayer) player, tileEntityOreReationMachine, (FriendlyByteBuf packerBuffer) -> {
                packerBuffer.writeBlockPos(tileEntityOreReationMachine.getBlockPos());
            });
        }
        return InteractionResult.SUCCESS;
    }
}
