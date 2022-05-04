package teamHTBP.vida.block.function;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import teamHTBP.vida.TileEntity.TileEntityGoldElementCrystal;
import teamHTBP.vida.helper.blockHelper.BlockElementCrystal;

import javax.annotation.Nullable;

public class BlockElementCrystalGold extends BlockElementCrystal {
    public BlockElementCrystalGold() {
        super(1);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityGoldElementCrystal(1);
    }
}
