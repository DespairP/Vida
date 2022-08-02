package teamHTBP.vida.common.block.function;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import teamHTBP.vida.common.TileEntity.TileEntityWoodElementCrystal;

import javax.annotation.Nullable;

public class BlockElementCrystalWood extends BlockElementCrystal {
    public BlockElementCrystalWood() {
        super(2);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityWoodElementCrystal(2);
    }
}