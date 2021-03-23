package teamHTBP.vida.TileEntity;

import teamHTBP.vida.capability.EnumElements;

public interface IElementCrystal {
    public abstract EnumElements getElement();
    public abstract int getEnergyStored();
    public abstract int getMaxEnergy();
}
