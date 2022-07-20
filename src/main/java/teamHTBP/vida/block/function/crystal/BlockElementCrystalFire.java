package teamHTBP.vida.block.function.crystal;

import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.blockentity.crystal.TileEntityFireElementCrystal;

public class BlockElementCrystalFire extends BlockElementCrystal<TileEntityFireElementCrystal> {
    public BlockElementCrystalFire() {
        super(TileEntityLoader.TileEntityCrystalFire, 4);
    }
}
