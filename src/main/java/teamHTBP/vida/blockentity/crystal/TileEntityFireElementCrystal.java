package teamHTBP.vida.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.helper.elementHelper.EnumElements;

public class TileEntityFireElementCrystal extends BaseCrystalBlockEntity {
    public TileEntityFireElementCrystal(BlockPos pWorldPosition, BlockState pBlockState) {
        super(TileEntityLoader.TileEntityCrystalFire.get(), pWorldPosition, pBlockState, EnumElements.FIRE);
    }
}
