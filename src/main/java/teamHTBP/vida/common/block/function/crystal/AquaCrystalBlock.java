package teamHTBP.vida.common.block.function.crystal;

import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.common.blockentity.crystal.AquaCrystalBlockEntity;
import teamHTBP.vida.core.element.EnumElements;

public class AquaCrystalBlock extends ElementCrystalBlock<AquaCrystalBlockEntity> {
    public AquaCrystalBlock() {
        super(VidaBlockEntityLoader.TileEntityCrystalAqua, EnumElements.AQUA);
    }
}
