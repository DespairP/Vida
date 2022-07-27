package teamHTBP.vida.common.block.function.crystal;

import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.common.blockentity.crystal.FireElementCrystalBlockEntity;
import teamHTBP.vida.core.element.EnumElements;

public class FireCrystalBlock extends ElementCrystalBlock<FireElementCrystalBlockEntity> {
    public FireCrystalBlock() {
        super(VidaBlockEntityLoader.TileEntityCrystalFire, EnumElements.FIRE);
    }
}
