package teamHTBP.vida.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import teamHTBP.vida.Vida;
import teamHTBP.vida.network.client.BottlesPacket;
import teamHTBP.vida.network.client.PrismTablePacket;
import teamHTBP.vida.network.server.MessagePacket;

import java.util.Optional;

public class VidaPacketManager {
    private static final String PROTOCOL_VERSION = "1.1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Vida.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void sendToServer(Object message) {
        INSTANCE.sendToServer(message);
    }

    public static void sendToPlayer(Object message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static void sendToEntity(Object message, Entity entity) {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), message);
    }

    static int id = 0;

    public static void register() {
        // s -> c
        Optional<NetworkDirection> client = Optional.of(NetworkDirection.PLAY_TO_CLIENT);
        // c -> s
        Optional<NetworkDirection> server = Optional.of(NetworkDirection.PLAY_TO_SERVER);

        INSTANCE.registerMessage(id++,
                PrismTablePacket.class,
                PrismTablePacket::toBytes,
                PrismTablePacket::new,
                PrismTablePacket::handler,
                server);

        INSTANCE.registerMessage(id++,
                BottlesPacket.class,
                BottlesPacket::toBytes,
                BottlesPacket::new,
                BottlesPacket::handler,
                server);


        INSTANCE.registerMessage(id++,
                MessagePacket.class,
                MessagePacket::toBytes,
                MessagePacket::new,
                MessagePacket::handler,
                client);
    }
}
