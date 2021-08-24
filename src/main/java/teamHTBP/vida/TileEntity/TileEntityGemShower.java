package teamHTBP.vida.TileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import teamHTBP.vida.item.ItemLoader;

import javax.annotation.Nullable;

public class TileEntityGemShower extends TileEntity implements ITickableTileEntity {
    //展示的宝石
    public ItemStack gemItem = ItemStack.EMPTY;


    public TileEntityGemShower() {
        super(TileEntityLoader.TileEntityGemShower.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        if (compound.contains("gemItem"))
            gemItem = ItemStack.read(compound.getCompound("gemItem"));
        super.read(state, compound);
    }

    public CompoundNBT write(CompoundNBT compound) {
        if (!gemItem.isEmpty())
            compound.put("gemItem", gemItem.serializeNBT());
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
        if (tag.contains("gemItem"))
            gemItem = ItemStack.read(tag.getCompound("gemItem"));
        else
            gemItem = ItemStack.EMPTY;
    }


    public boolean setGem(ItemStack itemStack) {
        if (!gemItem.isEmpty())
            return false;
        else {
            Item putGemItem = itemStack.getItem();
            if (putGemItem == ItemLoader.ELEMENTGEM_FIRE.get() || putGemItem == ItemLoader.ELEMENTGEM_GOLD.get() ||
                    putGemItem == ItemLoader.ELEMENTGEM_WOOD.get() || putGemItem == ItemLoader.ELEMENTGEM_AQUA.get() ||
                    putGemItem == ItemLoader.ELEMENTGEM_EARTH.get())
                gemItem = new ItemStack(itemStack.getItem(), 1);
            return true;
        }
    }

    @Override
    public void tick() {

    }

}
