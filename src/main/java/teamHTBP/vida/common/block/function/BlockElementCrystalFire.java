package teamHTBP.vida.common.block.function;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import teamHTBP.vida.common.TileEntity.TileEntityFireElementCrystal;

import javax.annotation.Nullable;

public class BlockElementCrystalFire extends BlockElementCrystal {
    public BlockElementCrystalFire() {
        super(4);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityFireElementCrystal(3);
    }
}
