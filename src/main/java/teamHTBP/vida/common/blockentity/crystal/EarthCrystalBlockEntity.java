package teamHTBP.vida.common.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.core.element.EnumElements;

public class EarthCrystalBlockEntity extends BaseCrystalBlockEntity {

    public EarthCrystalBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(VidaBlockEntityLoader.TileEntityCrystalEarth.get(), pWorldPosition, pBlockState, EnumElements.EARTH);
    }
}
