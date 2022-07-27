package teamHTBP.vida.common.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.common.capability.blueprint.IBlueprintCapability;
import teamHTBP.vida.common.capability.energy.IElementEnergyCapability;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VidaCapabilityLoader {
    public static final Capability<IElementEnergyCapability> ELEMENT_ENERGY = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IBlueprintCapability> BLUEPRINT = CapabilityManager.get(new CapabilityToken<>(){});

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IElementEnergyCapability.class);
        event.register(IBlueprintCapability.class);
    }
}
