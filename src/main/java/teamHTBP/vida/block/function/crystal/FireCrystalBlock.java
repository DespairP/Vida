package teamHTBP.vida.block.function.crystal;

import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.blockentity.crystal.FireElementCrystalBlockEntity;
import teamHTBP.vida.element.EnumElements;

public class FireCrystalBlock extends ElementCrystalBlock<FireElementCrystalBlockEntity> {
    public FireCrystalBlock() {
        super(TileEntityLoader.TileEntityCrystalFire, EnumElements.FIRE);
    }
}
