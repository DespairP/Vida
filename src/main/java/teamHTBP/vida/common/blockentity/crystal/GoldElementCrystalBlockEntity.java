package teamHTBP.vida.common.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.common.capability.VidaCapabilityLoader;
import teamHTBP.vida.common.capability.energy.IElementEnergyCapability;
import teamHTBP.vida.core.element.EnumElements;

public class GoldElementCrystalBlockEntity extends BaseCrystalBlockEntity {
    private int ticks = 0;

    public GoldElementCrystalBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(VidaBlockEntityLoader.TileEntityCrystalGold.get(), pWorldPosition, pBlockState, EnumElements.GOLD);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide) {
            LazyOptional<IElementEnergyCapability> cap = this.getCapability(VidaCapabilityLoader.ELEMENT_ENERGY);
        }
        ticks += 1;
        ticks %= 31;
        if (ticks == 30 && this.getEnergyStored() < this.getMaxEnergy()) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            this.setChanged();
        }
    }
}