package teamHTBP.vida.block.function.crystal;

import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.blockentity.crystal.TileEntityEarthElementCrystal;

public class BlockElementCrystalEarth extends BlockElementCrystal<TileEntityEarthElementCrystal> {
    public BlockElementCrystalEarth() {
        super(TileEntityLoader.TileEntityCrystalEarth, 5);
    }
}
