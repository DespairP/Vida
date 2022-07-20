package teamHTBP.vida.block.function.crystal;

import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.blockentity.crystal.TileEntityAquaElementCrystal;

public class BlockElementCrystalAqua extends BlockElementCrystal<TileEntityAquaElementCrystal> {
    public BlockElementCrystalAqua() {
        super(TileEntityLoader.TileEntityCrystalAqua, 3);
    }
}
