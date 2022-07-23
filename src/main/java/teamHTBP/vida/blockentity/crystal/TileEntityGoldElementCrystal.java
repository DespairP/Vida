package teamHTBP.vida.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.capability.VidaCapabilities;
import teamHTBP.vida.capability.energyCapability.IElementEnergyCapability;
import teamHTBP.vida.helper.elementHelper.EnumElements;

public class TileEntityGoldElementCrystal extends BaseCrystalBlockEntity {
    private int ticks = 0;

    public TileEntityGoldElementCrystal(BlockPos pWorldPosition, BlockState pBlockState) {
        super(TileEntityLoader.TileEntityCrystalGold.get(), pWorldPosition, pBlockState, EnumElements.GOLD);
    }

    @Override
    public void tick() {
        super.tick();
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
}