package teamHTBP.vida.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.capability.VidaCapabilityRegistry;
import teamHTBP.vida.capability.energy.IElementEnergyCapability;
import teamHTBP.vida.element.EnumElements;

public class GoldElementCrystalBlockEntity extends BaseCrystalBlockEntity {
    private int ticks = 0;

    public GoldElementCrystalBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(TileEntityLoader.TileEntityCrystalGold.get(), pWorldPosition, pBlockState, EnumElements.GOLD);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide) {
            LazyOptional<IElementEnergyCapability> cap = this.getCapability(VidaCapabilityRegistry.ELEMENT_ENERGY);
        }
        ticks += 1;
        ticks %= 31;
        if (ticks == 30 && this.getEnergyStored() < this.getMaxEnergy()) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            this.setChanged();
        }
    }
}