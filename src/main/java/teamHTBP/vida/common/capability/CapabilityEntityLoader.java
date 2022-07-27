package teamHTBP.vida.common.capability;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.capability.blueprint.BlueprintCapabilityProvider;
import teamHTBP.vida.common.capability.blueprint.IBlueprintCapability;

@Mod.EventBusSubscriber()
public class CapabilityEntityLoader {
    @SubscribeEvent
    public static void attachPlayerCapability(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof Player) {
            event.addCapability(new ResourceLocation(Vida.MOD_ID, "blueprints"), new BlueprintCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void restoreCapability(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            LazyOptional<IBlueprintCapability> old_Cap = event.getOriginal().getCapability(VidaCapabilityLoader.BLUEPRINT);
            event.getPlayer().getCapability(VidaCapabilityLoader.BLUEPRINT).ifPresent((new_Cap) -> {
                old_Cap.ifPresent((old) -> {
                    new_Cap.deserializeNBT(old.serializeNBT());
                });
            });
        }
    }
}
