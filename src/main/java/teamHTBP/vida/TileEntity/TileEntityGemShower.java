package teamHTBP.vida.TileEntity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import teamHTBP.vida.Item.ItemLoader;

import javax.annotation.Nullable;

public class TileEntityGemShower extends TileEntity implements ITickableTileEntity {
    //展示的宝石
    public ItemStack gemItem = ItemStack.EMPTY;


    public TileEntityGemShower() {
        super(TileEntityLoader.TileEntityGemShower.get());
    }

    public void read(CompoundNBT compound) { ;
        if(compound.contains("gemItem"))
            gemItem = ItemStack.read(compound.getCompound("meltItem"));
        super.read(compound);
    }

    public CompoundNBT write(CompoundNBT compound) {
        if(!gemItem.isEmpty())
            compound.put("gemItem", gemItem.serializeNBT());
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
        if(tag.contains("gemItem"))
            gemItem = ItemStack.read(tag.getCompound("gemItem"));
        else
            gemItem = ItemStack.EMPTY;
    }

    public boolean setGem(ItemStack itemStack){
        if(!gemItem.isEmpty())
            return false;
        else {
            Item putGemItem =itemStack.getItem();
            if(putGemItem == ItemLoader.fireElementGem.get() || putGemItem == ItemLoader.goldElementGem.get() ||
               putGemItem == ItemLoader.woodElementGem.get() || putGemItem == ItemLoader.aquaElementGem.get()||
            putGemItem == ItemLoader.earthElementGem.get())
                gemItem = new ItemStack(itemStack.getItem(), 1);
            return true;
        }
    }

    @Override
    public void tick(){

    }

}
