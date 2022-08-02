package teamHTBP.vida.client;

import com.mojang.blaze3d.vertex.BufferBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.SortedMap;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(Dist.CLIENT)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VidaRenderManager {

    private static final SortedMap<RenderType, BufferBuilder> FIXED_BUFFERS = Util.make(new Object2ObjectLinkedOpenHashMap<>(), map -> {
        put(map, VidaRenderTypes.getOutlineSolid());
    });

    private static final MultiBufferSource.BufferSource BUFFER_SOURCE =
            MultiBufferSource.immediateWithBuffers(FIXED_BUFFERS, new BufferBuilder(256));

    public static MultiBufferSource.BufferSource source() {
        return BUFFER_SOURCE;
    }

    private static void put(Object2ObjectLinkedOpenHashMap<RenderType, BufferBuilder> map, RenderType type) {
        map.put(type, new BufferBuilder(type.bufferSize()));
    }

    @SubscribeEvent
    public static void onRenderWorld(RenderLevelLastEvent event) {
        source().endBatch();
    }
}
