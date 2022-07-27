package teamHTBP.vida.common.capability;

import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.capability.blueprint.BlueprintCapabilityProvider;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber
public class CapabilityCommonListener {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof Player player) {
            if (!player.getCapability(VidaCapabilityLoader.BLUEPRINT).isPresent()) {
                // The player does not already have this capability so we need to add the capability provider here
                event.addCapability(new ResourceLocation(Vida.MOD_ID, "blueprint"),
                        new BlueprintCapabilityProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        cloneCapability(event, VidaCapabilityLoader.BLUEPRINT);
    }

    static <T extends Tag, C extends INBTSerializable<T>> void cloneCapability(PlayerEvent.Clone event, Capability<C> capability) {
        Player player = event.getPlayer();
        if (event.isWasDeath()) {
            if (player.level instanceof ServerLevel serverLevel) {
                if (serverLevel.getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
                    event.getOriginal().reviveCaps();

                    event.getOriginal().getCapability(capability).ifPresent(oldStore ->
                            event.getPlayer().getCapability(capability).ifPresent(newStore ->
                                    newStore.deserializeNBT(oldStore.serializeNBT())));
                }
            }
        }
    }
}
