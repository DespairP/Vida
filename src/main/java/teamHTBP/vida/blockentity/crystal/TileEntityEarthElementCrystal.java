package teamHTBP.vida.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.helper.elementHelper.EnumElements;

public class TileEntityEarthElementCrystal extends BaseCrystalBlockEntity {

    public TileEntityEarthElementCrystal(BlockPos pWorldPosition, BlockState pBlockState) {
        super(TileEntityLoader.TileEntityCrystalEarth.get(), pWorldPosition, pBlockState, EnumElements.EARTH);
    }
}
