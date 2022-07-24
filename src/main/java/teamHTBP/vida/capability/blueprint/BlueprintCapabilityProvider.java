package teamHTBP.vida.capability.blueprint;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import teamHTBP.vida.Vida;
import teamHTBP.vida.capability.VidaCapabilityRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlueprintCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    private IBlueprintCapability blueprintCapability;

    @Nonnull
    @Override
    public LazyOptional getCapability(@Nonnull Capability cap, @Nullable Direction side) {
        Vida.LOGGER.debug("getCapability");
        if (cap == VidaCapabilityRegistry.BLUEPRINT) {
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
    public CompoundTag serializeNBT() {
        return getOrCreateCapability().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        getOrCreateCapability().deserializeNBT(nbt);
    }
}
