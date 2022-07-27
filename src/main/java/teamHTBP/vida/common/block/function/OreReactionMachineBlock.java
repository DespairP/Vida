package teamHTBP.vida.common.block.function;

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
import teamHTBP.vida.common.block.base.VidaBaseEntityBlock;
import teamHTBP.vida.common.blockentity.OreReationMachineBlockEntity;
import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;

public class OreReactionMachineBlock extends VidaBaseEntityBlock<OreReationMachineBlockEntity> {
    public OreReactionMachineBlock() {
        super(Properties.of(Material.STONE).noOcclusion().strength(4.0f, 4.0f),
                VidaBlockEntityLoader.TileEntityOreReationMachine);
    }

    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        OreReationMachineBlockEntity oreReationMachineBlockEntity = (OreReationMachineBlockEntity) worldIn.getBlockEntity(pos);
        if (oreReationMachineBlockEntity != null) {
            for (int i = 0; i < 4; i++)
                worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), oreReationMachineBlockEntity.getSmeltSlotInv().getItem(i)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), oreReationMachineBlockEntity.getFuelInv().getItem(0)));
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), oreReationMachineBlockEntity.getCompleteSlot().getItem(0)));

        }
        super.playerWillDestroy(worldIn, pos, state, player);
    }


    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND) {
            OreReationMachineBlockEntity oreReationMachineBlockEntity = (OreReationMachineBlockEntity) worldIn.getBlockEntity(pos);
            NetworkHooks.openGui((ServerPlayer) player, oreReationMachineBlockEntity, (FriendlyByteBuf packerBuffer) -> {
                packerBuffer.writeBlockPos(oreReationMachineBlockEntity.getBlockPos());
            });
        }
        return InteractionResult.SUCCESS;
    }
}
