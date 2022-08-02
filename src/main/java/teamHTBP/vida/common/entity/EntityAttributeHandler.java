package teamHTBP.vida.common.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributeHandler {
    @SubscribeEvent
    public static void onEvent(EntityAttributeCreationEvent event) {
        event.put(EntityLoader.ANCIENT_BELIEVER.get(), AncientBeliever.createAttributes().create());
    }
}
