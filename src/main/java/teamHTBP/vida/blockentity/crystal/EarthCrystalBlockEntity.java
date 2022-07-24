package teamHTBP.vida.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.element.EnumElements;

public class EarthCrystalBlockEntity extends BaseCrystalBlockEntity {

    public EarthCrystalBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(TileEntityLoader.TileEntityCrystalEarth.get(), pWorldPosition, pBlockState, EnumElements.EARTH);
    }
}
