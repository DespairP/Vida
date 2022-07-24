package teamHTBP.vida.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.element.EnumElements;

public class WoodElementCrystalBlockEntity extends BaseCrystalBlockEntity {
    public WoodElementCrystalBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(TileEntityLoader.TileEntityCrystalWood.get(), pWorldPosition, pBlockState, EnumElements.WOOD);
    }
}
