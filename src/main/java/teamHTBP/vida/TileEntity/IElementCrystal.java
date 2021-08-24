package teamHTBP.vida.TileEntity;

import teamHTBP.vida.element.EnumElements;

public interface IElementCrystal {
    EnumElements getElement();

    int getEnergyStored();

    int getMaxEnergy();
}
