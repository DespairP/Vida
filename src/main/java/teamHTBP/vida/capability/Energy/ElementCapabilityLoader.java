package teamHTBP.vida.capability.Energy;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class ElementCapabilityLoader {
    @CapabilityInject(IElementEnergyCapability.class)
    public static Capability<IElementEnergyCapability> elementEnergy_Capability = null;
}
