package teamHTBP.vida.TileEntity;

import teamHTBP.vida.core.element.EnumElements;

public interface IElementCrystal {
    EnumElements getElement();

    int getEnergyStored();

    int getMaxEnergy();
}
