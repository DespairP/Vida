package teamHTBP.vida.common.blockentity.crystal;

import teamHTBP.vida.api.core.element.IElementHolder;

public interface IElementCrystal extends IElementHolder {

    /**
     * 获取当前能量
     * @return 当前能量
     */
    int getEnergyStored();

    /**
     * 获取最大能量
     * @return 最大能量
     */
    int getMaxEnergy();
}
