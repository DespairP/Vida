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
import teamHTBP.vida.helper.elementHelper.EnumElements;

import javax.annotation.Nullable;

public class TileEntityEarthElementCrystal extends TileEntity implements ITickableTileEntity, IElementCrystal {
    private final EnumElements element;
    private final LazyOptional<IElementEnergyCapability> energyCapability = LazyOptional.of(this::createNewEnergyCap);
    public float sinWave = 0;


    public TileEntityEarthElementCrystal(int element) {
        super(TileEntityLoader.TileEntityCrystalEarth.get());
        this.element = EnumElements.values()[element - 1];
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        energyCapability.ifPresent(T -> T.setEnergy(nbt.getInt("energy")));
        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        energyCapability.ifPresent(h -> compound.putInt("energy", h.getEnergyStored()));
        //System.out.println(compound.getInt("energy"));
        return super.save(compound);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 1, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(level.getBlockState(worldPosition), pkt.getTag());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        //System.out.println(tag.getInt("energy"));
        energyCapability.ifPresent(T -> T.setEnergy(tag.getInt("energy")));
        super.handleUpdateTag(state, tag);
    }

    @Override
    public void tick() {
        if (level.isClientSide)
            if (sinWave > 2 * Math.PI) sinWave = 0;
            else sinWave += 0.1f;
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
