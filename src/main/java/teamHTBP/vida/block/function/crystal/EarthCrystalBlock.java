package teamHTBP.vida.block.function.crystal;

import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.blockentity.crystal.EarthCrystalBlockEntity;
import teamHTBP.vida.element.EnumElements;

public class EarthCrystalBlock extends ElementCrystalBlock<EarthCrystalBlockEntity> {
    public EarthCrystalBlock() {
        super(TileEntityLoader.TileEntityCrystalEarth, EnumElements.EARTH);
    }
}
