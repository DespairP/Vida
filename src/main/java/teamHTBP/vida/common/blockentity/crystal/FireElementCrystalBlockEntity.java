package teamHTBP.vida.common.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.core.element.EnumElements;

public class FireElementCrystalBlockEntity extends BaseCrystalBlockEntity {
    public FireElementCrystalBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(VidaBlockEntityLoader.TileEntityCrystalFire.get(), pWorldPosition, pBlockState, EnumElements.FIRE);
    }
}
