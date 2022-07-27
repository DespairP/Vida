package teamHTBP.vida.common.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.core.element.EnumElements;

public class AquaCrystalBlockEntity extends BaseCrystalBlockEntity {

    public AquaCrystalBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(VidaBlockEntityLoader.TileEntityCrystalAqua.get(), pWorldPosition, pBlockState, EnumElements.AQUA);
    }
}
