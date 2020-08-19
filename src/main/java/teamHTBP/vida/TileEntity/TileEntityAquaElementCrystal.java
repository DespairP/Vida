package teamHTBP.vida.TileEntity;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAquaElementCrystal extends TileEntity implements ITickableTileEntity {
    private int element = 0;
    public float sinWave = 0;
    public TileEntityAquaElementCrystal(int element) {
        super(TileEntityLoader.TileEntityCrystalAqua.get());
        this.element = element;
    }


    @Override
    public void tick() {
        if(world.isRemote)
            if(sinWave >2* Math.PI) sinWave = 0; else sinWave+=0.1f;
    }
}
