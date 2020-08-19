package teamHTBP.vida.TileEntity;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityElementCrystal extends TileEntity implements ITickableTileEntity {
    private int element = 0;
    public float sinWave = 0;
    public TileEntityElementCrystal(int element) {
        super(TileEntityLoader.TileEntityCrystalGold.get());
        this.element = element;
    }


    @Override
    public void tick() {
        if(world.isRemote)
             if(sinWave >2* Math.PI) sinWave = 0; else sinWave+=0.1f;
    }
}
