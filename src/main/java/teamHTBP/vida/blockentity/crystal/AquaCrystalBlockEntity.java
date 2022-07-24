package teamHTBP.vida.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.element.EnumElements;

public class AquaCrystalBlockEntity extends BaseCrystalBlockEntity {

    public AquaCrystalBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(TileEntityLoader.TileEntityCrystalAqua.get(), pWorldPosition, pBlockState, EnumElements.AQUA);
    }
}
