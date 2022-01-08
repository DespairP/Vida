package teamHTBP.vida.TileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.LazyOptional;
import teamHTBP.vida.capability.energyCapability.ElementEnergyCapability;
import teamHTBP.vida.capability.energyCapability.IElementEnergyCapability;
import teamHTBP.vida.helper.element.EnumElements;

import javax.annotation.Nullable;

public class TileEntityWoodElementCrystal extends TileEntity implements ITickableTileEntity, IElementCrystal {
    private final EnumElements element;
    private final LazyOptional<IElementEnergyCapability> energyCapability = LazyOptional.of(this::createNewEnergyCap);
    public float sinWave = 0;


    public TileEntityWoodElementCrystal(int element) {
        super(TileEntityLoader.TileEntityCrystalWood.get());
        this.element = EnumElements.values()[element - 1];
    }


    @Override
    public void tick() {
        if (world.isRemote)
            if (sinWave > 2 * Math.PI) sinWave = 0;
            else sinWave += 0.1f;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        energyCapability.ifPresent(T -> T.setEnergy(compound.getInt("energy")));
        super.read(state, compound);
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
        return new SUpdateTileEntityPacket(this.pos, 1, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(world.getBlockState(pos), pkt.getNbtCompound());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        //System.out.println(tag.getInt("energy"));
        energyCapability.ifPresent(T -> T.setEnergy(tag.getInt("energy")));
        super.read(state, tag);
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
