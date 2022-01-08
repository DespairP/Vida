package teamHTBP.vida.TileEntity;

import teamHTBP.vida.helper.element.EnumElements;

public interface IElementCrystal {
    EnumElements getElement();

    int getEnergyStored();

    int getMaxEnergy();
}
