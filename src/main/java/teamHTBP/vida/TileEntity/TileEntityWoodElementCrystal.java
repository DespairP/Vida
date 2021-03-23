package teamHTBP.vida.TileEntity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.LazyOptional;
import teamHTBP.vida.capability.Energy.ElementEnergyCapability;
import teamHTBP.vida.capability.Energy.IElementEnergyCapability;
import teamHTBP.vida.capability.EnumElements;

import javax.annotation.Nullable;

public class TileEntityWoodElementCrystal extends TileEntity implements ITickableTileEntity,IElementCrystal {
    private EnumElements element;
    public float sinWave = 0;
    private LazyOptional<IElementEnergyCapability> energyCapability = LazyOptional.of(this::createNewEnergyCap);


    public TileEntityWoodElementCrystal(int element) {
        super(TileEntityLoader.TileEntityCrystalWood.get());
        this.element = EnumElements.values()[element - 1];
    }


    @Override
    public void tick() {
        if(world.isRemote)
            if(sinWave >2* Math.PI) sinWave = 0; else sinWave+=0.1f;
    }

    @Override
    public void read(CompoundNBT compound) {
        energyCapability.ifPresent(T -> T.setEnergy(compound.getInt("energy")));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        energyCapability.ifPresent(h -> compound.putInt("energy", h.getEnergyStored()));
        //System.out.println(compound.getInt("energy"));
        return super.write(compound);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos,1,this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net,pkt);
        handleUpdateTag(pkt.getNbtCompound());
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        //System.out.println(tag.getInt("energy"));
        energyCapability.ifPresent(T -> T.setEnergy(tag.getInt("energy")));
        super.read(tag);
    }

    @Override
    public EnumElements getElement() {
        return energyCapability.orElseGet(this::createNewEnergyCap).getElement();
    }

    @Override
    public int getEnergyStored() {
        return energyCapability.orElseGet(this::createNewEnergyCap).getEnergyStored();
    }

    @Override
    public int getMaxEnergy() {
        return energyCapability.orElseGet(this::createNewEnergyCap).getMaxEnergyStored();
    }

    private ElementEnergyCapability createNewEnergyCap() {
        return new ElementEnergyCapability(10000, 200, 200, 0, element);
    }
}
