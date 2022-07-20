package teamHTBP.vida.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import teamHTBP.vida.capability.blueprintCapability.IBlueprintCapability;
import teamHTBP.vida.capability.energyCapability.IElementEnergyCapability;

public class VidaCapabilities {
    public static final Capability<IElementEnergyCapability> ELEMENT_ENERGY = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IBlueprintCapability> BLUEPRINT = CapabilityManager.get(new CapabilityToken<>(){});
}
