package teamHTBP.vida.block.function.crystal;

import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.blockentity.crystal.TileEntityWoodElementCrystal;

public class BlockElementCrystalWood extends BlockElementCrystal<TileEntityWoodElementCrystal> {
    public BlockElementCrystalWood() {
        super(TileEntityLoader.TileEntityCrystalWood, 2);
    }
}
