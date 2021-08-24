package teamHTBP.vida.capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.Vida;
import teamHTBP.vida.capability.blueprintCapability.BlueprintCapabilityProvider;
import teamHTBP.vida.capability.blueprintCapability.IBlueprintCapability;

@Mod.EventBusSubscriber()
public class CapabilityEntityLoader {
    @SubscribeEvent
    public static void attachPlayerCapability(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof PlayerEntity) {
            event.addCapability(new ResourceLocation(Vida.modId, "blueprints"), new BlueprintCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void restoreCapability(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            LazyOptional<IBlueprintCapability> old_Cap = event.getOriginal().getCapability(VidaCapabilities.blueprint_Capability);
            event.getPlayer().getCapability(VidaCapabilities.blueprint_Capability).ifPresent((new_Cap) -> {
                old_Cap.ifPresent((old) -> {
                    new_Cap.deserializeNBT(old.serializeNBT());
                });
            });
        }
    }
}
