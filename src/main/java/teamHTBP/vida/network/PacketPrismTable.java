package teamHTBP.vida.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import teamHTBP.vida.blockentity.TileEntityPrismTable;

import java.util.function.Supplier;

public class PacketPrismTable {
    private int mirrorX = 0;
    private int mirrorY = 0;
    private boolean isClick = false;
    private int x = 0;
    private int y = 0;
    private int z = 0;

    public PacketPrismTable(FriendlyByteBuf buffer) {
        mirrorX = buffer.readInt();
        mirrorY = buffer.readInt();
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        isClick = buffer.readBoolean();
    }

    public PacketPrismTable(int mirrorX, int mirrorY, int x, int y, int z) {
        this.mirrorX = mirrorX;
        this.mirrorY = mirrorY;
        this.x = x;
        this.y = y;
        this.z = z;
        isClick = true;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.mirrorX);
        buf.writeInt(this.mirrorY);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeBoolean(this.isClick);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get().getSender() == null) return;

            if (ctx.get().getSender().level.isAreaLoaded(new BlockPos(x, y, z), 1)) {
                BlockEntity tileEntity = ctx.get().getSender().level.getBlockEntity(new BlockPos(x, y, z));
                if (tileEntity instanceof TileEntityPrismTable) {
                    TileEntityPrismTable tileEntityPrismTable = (TileEntityPrismTable) tileEntity;
                    tileEntityPrismTable.array.set(2, this.mirrorX);
                    tileEntityPrismTable.array.set(3, this.mirrorY);
                    tileEntityPrismTable.isClick = true;
                }
            }
        });


        ctx.get().setPacketHandled(true);
    }

}
