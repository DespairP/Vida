package teamHTBP.vida.common.block.function;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import teamHTBP.vida.common.TileEntity.TileEntityAquaElementCrystal;

import javax.annotation.Nullable;

public class BlockElementCrystalAqua extends BlockElementCrystal {
    public BlockElementCrystalAqua() {
        super(3);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityAquaElementCrystal(3);
    }
}
