package teamHTBP.vida.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import teamHTBP.vida.capability.blueprintCapability.IBlueprintCapability;
import teamHTBP.vida.capability.energyCapability.IElementEnergyCapability;

public class VidaCapabilities {
    @CapabilityInject(IElementEnergyCapability.class)
    public static Capability<IElementEnergyCapability> elementEnergy_Capability;
    @CapabilityInject(IBlueprintCapability.class)
    public static Capability<IBlueprintCapability> blueprint_Capability;
}
