package teamHTBP.vida.blockentity.crystal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.blockentity.TileEntityLoader;
import teamHTBP.vida.helper.elementHelper.EnumElements;

public class TileEntityWoodElementCrystal extends BaseCrystalBlockEntity {
    public TileEntityWoodElementCrystal(BlockPos pWorldPosition, BlockState pBlockState) {
        super(TileEntityLoader.TileEntityCrystalWood.get(), pWorldPosition, pBlockState, EnumElements.WOOD);
    }
}
