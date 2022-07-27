package teamHTBP.vida.common.block.function.crystal;

import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.common.blockentity.crystal.GoldElementCrystalBlockEntity;
import teamHTBP.vida.core.element.EnumElements;

public class GoldCrystalBlock extends ElementCrystalBlock<GoldElementCrystalBlockEntity> {
    public GoldCrystalBlock() {
        super(VidaBlockEntityLoader.TileEntityCrystalGold, EnumElements.GOLD);
    }
}
