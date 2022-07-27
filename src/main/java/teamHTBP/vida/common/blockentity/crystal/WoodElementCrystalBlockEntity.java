package teamHTBP.vida.common.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.core.element.EnumElements;

public class WoodElementCrystalBlockEntity extends BaseCrystalBlockEntity {
    public WoodElementCrystalBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(VidaBlockEntityLoader.TileEntityCrystalWood.get(), pWorldPosition, pBlockState, EnumElements.WOOD);
    }
}
