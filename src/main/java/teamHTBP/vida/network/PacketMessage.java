package teamHTBP.vida.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import teamHTBP.vida.event.client.HUDMessageEventHandler;

import java.util.function.Supplier;

public class PacketMessage {
    private String message = "";

    public PacketMessage(FriendlyByteBuf buffer) {
        message = buffer.readUtf().trim();
    }

    public PacketMessage(String message) {
        this.message = message;
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeUtf(message);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            HUDMessageEventHandler.setMessage(message);
            ctx.get().setPacketHandled(true);
        });
    }
}
