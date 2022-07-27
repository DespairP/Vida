package teamHTBP.vida.common.block.function.crystal;

import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.common.blockentity.crystal.WoodElementCrystalBlockEntity;
import teamHTBP.vida.core.element.EnumElements;

public class WoodCrystalBlock extends ElementCrystalBlock<WoodElementCrystalBlockEntity> {
    public WoodCrystalBlock() {
        super(VidaBlockEntityLoader.TileEntityCrystalWood, EnumElements.WOOD);
    }
}
