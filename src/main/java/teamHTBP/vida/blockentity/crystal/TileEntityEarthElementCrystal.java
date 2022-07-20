package teamHTBP.vida.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.blockentity.base.ModBaseBlockEntity;
import teamHTBP.vida.capability.energyCapability.ElementEnergyCapability;
import teamHTBP.vida.capability.energyCapability.IElementEnergyCapability;
import teamHTBP.vida.helper.elementHelper.EnumElements;

import java.util.List;

public class TileEntityEarthElementCrystal extends ModBaseBlockEntity implements IElementCrystal {
    private final EnumElements element;
    private final LazyOptional<IElementEnergyCapability> energyCapability = LazyOptional.of(this::createNewEnergyCap);
    public float sinWave = 0;

    public TileEntityEarthElementCrystal(BlockPos pWorldPosition, BlockState pBlockState, int element) {
        super(TileEntityLoader.TileEntityCrystalEarth.get(), pWorldPosition, pBlockState);
        this.element = EnumElements.values()[element - 1];
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
    public void tick() {
        if (level.isClientSide)
            if (sinWave > 2 * Math.PI) sinWave = 0;
            else sinWave += 0.1f;
    }

    @Override
    public EnumElements getElement() {
        return energyCapability.orElseGet(this::createNewEnergyCap).getElement();
    }

    @Override
    public int getEnergyStored() {
        return energyCapability.orElseGet(this::createNewEnergyCap).getEnergyStored();
    }

    @Override
    public int getMaxEnergy() {
        return energyCapability.orElseGet(this::createNewEnergyCap).getMaxEnergyStored();
    }

    private ElementEnergyCapability createNewEnergyCap() {
        return new ElementEnergyCapability(10000, 200, 200, 0, element);
    }

    @Override
    public List<ItemStack> getDrops() {
        return List.of();
    }
}
