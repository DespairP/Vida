package teamHTBP.vida.block.function;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import teamHTBP.vida.TileEntity.TileEntityAquaElementCrystal;
import teamHTBP.vida.helper.blockTools.BlockElementCrystal;

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
