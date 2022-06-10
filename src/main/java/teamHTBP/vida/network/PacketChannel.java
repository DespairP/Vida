package teamHTBP.vida.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import teamHTBP.vida.Vida;

public class PacketChannel {
    private static final String PROTOCOL_VERSION = "1.1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Vida.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        int id = 0;
        INSTANCE.registerMessage(id++, PacketPrismTable.class, PacketPrismTable::toBytes, PacketPrismTable::new, PacketPrismTable::handler);
        INSTANCE.registerMessage(id++, PacketBottles.class, PacketBottles::toBytes, PacketBottles::new, PacketBottles::handler);
        INSTANCE.registerMessage(id++, PacketMessage.class, PacketMessage::toBytes, PacketMessage::new, PacketMessage::handler);
        INSTANCE.registerMessage(id++, PacketGuidebook.class,PacketGuidebook::toBytes,PacketGuidebook::fromBytes,  PacketGuidebook::handler);
    }
}
