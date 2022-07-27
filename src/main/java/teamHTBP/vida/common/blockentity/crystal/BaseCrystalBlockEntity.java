package teamHTBP.vida.common.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import teamHTBP.vida.common.blockentity.base.VidaBaseBlockEntity;
import teamHTBP.vida.common.capability.VidaCapabilityLoader;
import teamHTBP.vida.common.capability.energy.ElementEnergyCapability;
import teamHTBP.vida.common.capability.energy.IElementEnergyCapability;
import teamHTBP.vida.api.core.element.IElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author DustW
 */
public abstract class BaseCrystalBlockEntity extends VidaBaseBlockEntity implements IElementCrystal {
    protected final IElement element;
    protected final LazyOptional<IElementEnergyCapability> energyCapability = LazyOptional.of(this::createNewEnergyCap);
    public float sinWave = 0;

    public BaseCrystalBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState, IElement element) {
        super(pType, pWorldPosition, pBlockState);
        this.element = element;
    }

    @Override
    public void tick() {
        assert level != null;

        if (level.isClientSide) {
            if (sinWave > 2 * Math.PI) {
                sinWave = 0;
            } else {
                sinWave += 0.1f;
            }
        }
    }

    @Override
    public List<ItemStack> getDrops() {
        return List.of();
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        energyCapability.ifPresent(t -> t.setEnergy(compound.getInt("energy")));
    }

    @Override
    protected void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        energyCapability.ifPresent(h -> compound.putInt("energy", h.getEnergyStored()));
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return VidaCapabilityLoader.ELEMENT_ENERGY.orEmpty(cap, energyCapability);
    }

    @Override
    public IElement getElement() {
        return element;
    }

    @Override
    public int getEnergyStored() {
        return energyCapability.orElseGet(this::createNewEnergyCap).getEnergyStored();
    }

    @Override
    public int getMaxEnergy() {
        return energyCapability.orElseGet(this::createNewEnergyCap).getMaxEnergyStored();
    }

    private @NotNull ElementEnergyCapability createNewEnergyCap() {
        return new ElementEnergyCapability(10000, 200, 200, 0, element);
    }
}
