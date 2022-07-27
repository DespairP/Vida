package teamHTBP.vida.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import teamHTBP.vida.client.event.listener.hud.HUDMessageEventHandler;

import java.util.function.Supplier;

public class MessagePacket {
    private String message = "";

    public MessagePacket(FriendlyByteBuf buffer) {
        message = buffer.readUtf().trim();
    }

    public MessagePacket(String message) {
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
