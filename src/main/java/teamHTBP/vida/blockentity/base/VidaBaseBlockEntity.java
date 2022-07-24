package teamHTBP.vida.blockentity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import org.jetbrains.annotations.Nullable;
import teamHTBP.vida.blockentity.ITickableTileEntity;

import java.util.List;

/**
 * @author DustW
 **/
public abstract class VidaBaseBlockEntity extends BlockEntity implements ITickableTileEntity {
    public VidaBaseBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compound = new CompoundTag();
        this.saveAdditional(compound);
        return compound;
    }

    public void sync(Level level) {
        if (!level.isClientSide) {
            ClientboundBlockEntityDataPacket p = ClientboundBlockEntityDataPacket.create(this);
            ((ServerLevel)this.level).getChunkSource().chunkMap.getPlayers(new ChunkPos(getBlockPos()), false)
                    .forEach(k -> k.connection.send(p));
        }
    }

    public abstract List<ItemStack> getDrops();

    protected final void energyOutputTick(EnergyStorage selfStorage) {
        if (getLevel() != null && !getLevel().isClientSide) {
            for (Direction value : Direction.values()) {
                BlockEntity blockEntity = getLevel().getBlockEntity(getBlockPos().offset(value.getNormal()));

                if (blockEntity != null) {
                    blockEntity.getCapability(CapabilityEnergy.ENERGY, value.getOpposite()).ifPresent(energy -> {
                        int max = energy.receiveEnergy(Integer.MAX_VALUE, true);
                        energy.receiveEnergy(selfStorage.extractEnergy(max, false), false);
                    });
                }
            }
        }
    }
}
