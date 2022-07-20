package teamHTBP.vida.network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import teamHTBP.vida.event.client.HUDMessageEventHandler;

import java.util.function.Supplier;

public class PacketMessage {
    private String message = "";

    public PacketMessage(PacketBuffer buffer) {
        message = buffer.readUtf().trim();
    }

    public PacketMessage(String message) {
        this.message = message;
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeUtf(message);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            HUDMessageEventHandler.setMessage(message);
            ctx.get().setPacketHandled(true);
        });
    }
}
