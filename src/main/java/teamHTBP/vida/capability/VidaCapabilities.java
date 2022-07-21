package teamHTBP.vida.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.capability.blueprintCapability.IBlueprintCapability;
import teamHTBP.vida.capability.energyCapability.IElementEnergyCapability;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VidaCapabilities {
    public static final Capability<IElementEnergyCapability> ELEMENT_ENERGY = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IBlueprintCapability> BLUEPRINT = CapabilityManager.get(new CapabilityToken<>(){});

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IElementEnergyCapability.class);
        event.register(IBlueprintCapability.class);
    }
}
