package teamHTBP.vida.capability.blueprintCapability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import teamHTBP.vida.Vida;
import teamHTBP.vida.capability.VidaCapabilities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlueprintCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private IBlueprintCapability blueprintCapability;

    @Nonnull
    @Override
    public LazyOptional getCapability(@Nonnull Capability cap, @Nullable Direction side) {
        Vida.LOGGER.debug("getCapability");
        if (cap == VidaCapabilities.blueprint_Capability) {
            return LazyOptional.of(() -> getOrCreateCapability()).cast();
        }
        return LazyOptional.empty();
    }

    @Nonnull
    public IBlueprintCapability getOrCreateCapability() {
        if (this.blueprintCapability == null)
            this.blueprintCapability = new BlueprintCapability();
        return blueprintCapability;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return getOrCreateCapability().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        getOrCreateCapability().deserializeNBT(nbt);
    }
}
