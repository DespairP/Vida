package teamHTBP.vida.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.helper.elementHelper.EnumElements;

public class TileEntityAquaElementCrystal extends BaseCrystalBlockEntity {

    public TileEntityAquaElementCrystal(BlockPos pWorldPosition, BlockState pBlockState) {
        super(TileEntityLoader.TileEntityCrystalAqua.get(), pWorldPosition, pBlockState, EnumElements.AQUA);
    }
}
