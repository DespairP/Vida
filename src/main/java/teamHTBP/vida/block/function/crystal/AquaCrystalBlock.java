package teamHTBP.vida.block.function.crystal;

import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.blockentity.crystal.AquaCrystalBlockEntity;
import teamHTBP.vida.element.EnumElements;

public class AquaCrystalBlock extends ElementCrystalBlock<AquaCrystalBlockEntity> {
    public AquaCrystalBlock() {
        super(TileEntityLoader.TileEntityCrystalAqua, EnumElements.AQUA);
    }
}
