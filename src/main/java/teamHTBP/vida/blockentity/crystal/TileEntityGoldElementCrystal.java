package teamHTBP.vida.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.blockentity.base.ModBaseBlockEntity;
import teamHTBP.vida.capability.VidaCapabilities;
import teamHTBP.vida.capability.energyCapability.ElementEnergyCapability;
import teamHTBP.vida.capability.energyCapability.IElementEnergyCapability;
import teamHTBP.vida.helper.elementHelper.EnumElements;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TileEntityGoldElementCrystal extends ModBaseBlockEntity implements IElementCrystal {
    private final EnumElements element = EnumElements.GOLD;
    public float sinWave = 0;
    public LazyOptional<IElementEnergyCapability> energyCapability = LazyOptional.of(this::createNewEnergyCap);
    private int ticks = 0;

    public TileEntityGoldElementCrystal(BlockPos pWorldPosition, BlockState pBlockState) {
        super(TileEntityLoader.TileEntityCrystalGold.get(), pWorldPosition, pBlockState);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        energyCapability.ifPresent(T -> T.setEnergy(pTag.getInt("energy")));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        energyCapability.ifPresent(h -> pTag.putInt("energy", h.getEnergyStored()));
    }

    @Override
    public List<ItemStack> getDrops() {
        return List.of();
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == VidaCapabilities.ELEMENT_ENERGY) {
            return energyCapability.cast();
        } else {
            return LazyOptional.empty();
        }
    }

    public IElementEnergyCapability createNewEnergyCap() {
        return new ElementEnergyCapability(10000, 200, 200, 0, element);
    }

    @Override
    public void tick() {
        if (level.isClientSide)
            if (sinWave > 2 * Math.PI) sinWave = 0;
            else sinWave += 0.1f;
        if (!level.isClientSide) {
            LazyOptional<IElementEnergyCapability> cap = this.getCapability(VidaCapabilities.ELEMENT_ENERGY);
        }
        ticks += 1;
        ticks %= 31;
        if (ticks == 30 && this.getEnergyStored() < this.getMaxEnergy()) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            this.setChanged();
        }
    }

    @Override
    public EnumElements getElement() {
        return EnumElements.GOLD;
    }

    @Override
    public int getEnergyStored() {
        //Function<IElementEnergyCapability,Integer> function = U -> U.getEnergyStored();
        IElementEnergyCapability capability = energyCapability.orElseGet(this::createNewEnergyCap);
        return capability.getEnergyStored();
    }

    @Override
    public int getMaxEnergy() {
        IElementEnergyCapability capability = energyCapability.orElseGet(this::createNewEnergyCap);
        return capability.getMaxEnergyStored();
    }
}