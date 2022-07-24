package teamHTBP.vida.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import teamHTBP.vida.Vida;

public class PacketManager {
    private static final String PROTOCOL_VERSION = "1.1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Vida.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    static int id = 0;

    public static void register() {
        INSTANCE.registerMessage(id++, PrismTablePacket.class, PrismTablePacket::toBytes, PrismTablePacket::new, PrismTablePacket::handler);
        INSTANCE.registerMessage(id++, BottlesPacket.class, BottlesPacket::toBytes, BottlesPacket::new, BottlesPacket::handler);
        INSTANCE.registerMessage(id++, MessagePacket.class, MessagePacket::toBytes, MessagePacket::new, MessagePacket::handler);
    }
}
