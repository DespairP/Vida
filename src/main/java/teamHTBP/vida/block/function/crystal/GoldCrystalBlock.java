package teamHTBP.vida.block.function.crystal;

import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.blockentity.crystal.GoldElementCrystalBlockEntity;
import teamHTBP.vida.element.EnumElements;

public class GoldCrystalBlock extends ElementCrystalBlock<GoldElementCrystalBlockEntity> {
    public GoldCrystalBlock() {
        super(TileEntityLoader.TileEntityCrystalGold, EnumElements.GOLD);
    }
}
