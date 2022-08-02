package teamHTBP.vida.common.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributeHandler {
    @SubscribeEvent
    public static void onEvent(EntityAttributeCreationEvent event) {
        event.put(VidaEntityLoader.ANCIENT_BELIEVER.get(), AncientBeliever.createAttributes().build());
    }
}
