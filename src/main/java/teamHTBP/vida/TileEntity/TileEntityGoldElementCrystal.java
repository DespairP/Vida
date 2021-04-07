package teamHTBP.vida.TileEntity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import teamHTBP.vida.capability.VidaCapabilities;
import teamHTBP.vida.capability.Energy.ElementEnergyCapability;
import teamHTBP.vida.capability.Energy.IElementEnergyCapability;
import teamHTBP.vida.capability.EnumElements;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityGoldElementCrystal extends TileEntity implements ITickableTileEntity,IElementCrystal {
    private EnumElements element = EnumElements.GOLD;
    public float sinWave = 0;
    private int ticks = 0;
    public LazyOptional<IElementEnergyCapability> energyCapability = LazyOptional.of(this::createNewEnergyCap);
    public TileEntityGoldElementCrystal(int element) {
        super(TileEntityLoader.TileEntityCrystalGold.get());
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
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
         if(cap == VidaCapabilities.elementEnergy_Capability){
             return energyCapability.cast();
         }else {
             return LazyOptional.empty();
         }
    }

    public IElementEnergyCapability createNewEnergyCap(){
       return new ElementEnergyCapability(10000, 200, 200, 0 , element);
    }

    @Override
    public void tick() {
        if(world.isRemote)
            if(sinWave >2* Math.PI) sinWave = 0; else sinWave+=0.1f;
        if(!world.isRemote) {
            LazyOptional<IElementEnergyCapability> cap = this.getCapability(VidaCapabilities.elementEnergy_Capability);
        }
        ticks += 1;
        ticks %= 31;
        if(ticks == 30 && this.getEnergyStored() < this.getMaxEnergy()){
        world.notifyBlockUpdate(pos,getBlockState(),getBlockState(),3);
        this.markDirty();
        }
    }

    @Override
    public EnumElements getElement() {
        return EnumElements.GOLD;
    }

    @Override
    public int getEnergyStored() {
        //Function<IElementEnergyCapability,Integer> function = U -> U.getEnergyStored();
        IElementEnergyCapability capability = energyCapability.orElseGet(this::createNewEnergyCap);
        return capability.getEnergyStored();
    }

    @Override
    public int getMaxEnergy() {
        IElementEnergyCapability capability = energyCapability.orElseGet(this::createNewEnergyCap);
        return capability.getMaxEnergyStored();
    }
}