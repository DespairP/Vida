package teamHTBP.vida.TileEntity;

import teamHTBP.vida.helper.elementHelper.EnumElements;

public interface IElementCrystal {
    EnumElements getElement();

    int getEnergyStored();

    int getMaxEnergy();
}
