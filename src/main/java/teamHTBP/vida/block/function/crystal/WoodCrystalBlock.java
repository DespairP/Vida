package teamHTBP.vida.block.function.crystal;

import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.blockentity.crystal.WoodElementCrystalBlockEntity;
import teamHTBP.vida.element.EnumElements;

public class WoodCrystalBlock extends ElementCrystalBlock<WoodElementCrystalBlockEntity> {
    public WoodCrystalBlock() {
        super(TileEntityLoader.TileEntityCrystalWood, EnumElements.WOOD);
    }
}
