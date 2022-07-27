package teamHTBP.vida.common.block.function.crystal;

import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.common.blockentity.crystal.EarthCrystalBlockEntity;
import teamHTBP.vida.core.element.EnumElements;

public class EarthCrystalBlock extends ElementCrystalBlock<EarthCrystalBlockEntity> {
    public EarthCrystalBlock() {
        super(VidaBlockEntityLoader.TileEntityCrystalEarth, EnumElements.EARTH);
    }
}
