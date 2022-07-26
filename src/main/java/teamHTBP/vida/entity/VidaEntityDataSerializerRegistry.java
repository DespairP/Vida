package teamHTBP.vida.entity;

import com.mojang.math.Vector3f;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VidaEntityDataSerializerRegistry {
    public static final EntityDataSerializer<Vector3f> VECTOR3F = new EntityDataSerializer<>() {
        @Override
        public void write(FriendlyByteBuf pBuffer, Vector3f pValue) {
            pBuffer.writeFloat(pValue.x());
            pBuffer.writeFloat(pValue.y());
            pBuffer.writeFloat(pValue.z());
        }

        @Override
        public @NotNull Vector3f read(FriendlyByteBuf pBuffer) {
            return new Vector3f(
                    pBuffer.readFloat(),
                    pBuffer.readFloat(),
                    pBuffer.readFloat()
            );
        }

        @Override
        public @NotNull Vector3f copy(Vector3f pValue) {
            return pValue.copy();
        }
    };

    @SubscribeEvent
    public static void onEvent(FMLCommonSetupEvent event) {
        EntityDataSerializers.registerSerializer(VECTOR3F);
    }
}
