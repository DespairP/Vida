package teamHTBP.vida.block.function;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import teamHTBP.vida.TileEntity.TileEntityEarthElementCrystal;

import javax.annotation.Nullable;

public class BlockElementCrystalEarth extends BlockElementCrystal {
    public BlockElementCrystalEarth() {
        super(5);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityEarthElementCrystal(5);
    }
}
