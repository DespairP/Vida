package teamHTBP.vida.Network;

import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import teamHTBP.vida.TileEntity.TileEntityPrismTable;

import java.util.function.Supplier;

public class PacketPrismTable {
    private int mirrorX;
    private int mirrorY;
    private boolean isClick;
    private int x;
    private int y;
    private int z;
    public PacketPrismTable(PacketBuffer buffer) {
        mirrorX = buffer.readInt();
        mirrorY = buffer.readInt();
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        isClick = buffer.readBoolean();
    }

    public PacketPrismTable(int mirrorX,int mirrorY,int x,int y,int z) {
        this.mirrorX = mirrorX;
        this.mirrorY = mirrorY;
        this.x = x;
        this.y = y;
        this.z = z;
        isClick = true;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeInt(this.mirrorX);
        buf.writeInt(this.mirrorY);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeBoolean(this.isClick);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(()->{
            TileEntity tileEntity = ctx.get().getSender().world.getTileEntity(new BlockPos(x,y,z));
            if(tileEntity instanceof TileEntityPrismTable){
                TileEntityPrismTable tileEntityPrismTable = (TileEntityPrismTable)tileEntity;
                tileEntityPrismTable.array.set(2, this.mirrorX);
                tileEntityPrismTable.array.set(3, this.mirrorY);
                tileEntityPrismTable.isClick = true;
            }
        });
    }

}
