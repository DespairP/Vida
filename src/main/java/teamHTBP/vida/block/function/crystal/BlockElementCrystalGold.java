package teamHTBP.vida.block.function.crystal;

import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.blockentity.crystal.TileEntityGoldElementCrystal;

public class BlockElementCrystalGold extends BlockElementCrystal<TileEntityGoldElementCrystal> {
    public BlockElementCrystalGold() {
        super(TileEntityLoader.TileEntityCrystalGold, 1);
    }
}
