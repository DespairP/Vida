package teamHTBP.vida.Network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import teamHTBP.vida.block.blockHUDRenderEvent.MessageHUDEventLoader;
import teamHTBP.vida.gui.HUD.MessageHUD;

import java.util.function.Supplier;

public class PacketMessage {
    private String message = "";

    public PacketMessage(PacketBuffer buffer){
        message = buffer.readString().trim();
    }

    public PacketMessage(String message){
        this.message = message;
    }

    public void toBytes(PacketBuffer buffer){
        buffer.writeString(message);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(()->{
            MessageHUDEventLoader.setMessage(message);
            ctx.get().setPacketHandled(true);
        });
    }
}
