package teamHTBP.vida.TileEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import teamHTBP.vida.Capability.ElementHelper;
import teamHTBP.vida.Item.ItemElementCore;
import teamHTBP.vida.Item.ItemElementCoreVoid;
import teamHTBP.vida.Item.ItemLoader;

import javax.annotation.Nullable;

public class TileEntityCollector extends TileEntity implements ITickableTileEntity {
    //收集的元素
    protected int element = 0;
    //收集的元素值
    protected int collection = 0;
    //是否在收集
    public boolean isCollect = false;
    //最大收集值
    public final int MAX_COLLECTION = 50000;
    //收集的元素的元素核心
    public ItemStack coreItem = ItemStack.EMPTY;

    public TileEntityCollector() {
        super(TileEntityLoader.TileEntityCollector.get());
    }

    public void read(CompoundNBT compound) { ;
        if(compound.contains("coreItem"))
            coreItem = ItemStack.read(compound.getCompound("coreItem"));
        isCollect = compound.getBoolean("isCollect");
        collection = compound.getInt("collection");
        element = compound.getInt("element");
        super.read(compound);
    }

    public CompoundNBT write(CompoundNBT compound) {
        if(coreItem != ItemStack.EMPTY || !coreItem.isEmpty())
            compound.put("coreItem", coreItem.serializeNBT());
        compound.putInt("collection", collection);
        compound.putBoolean("isCollect", isCollect);
        compound.putInt("element", element);
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
            coreItem = ItemStack.read(tag.getCompound("gemItem"));
        else
            coreItem = ItemStack.EMPTY;

        isCollect = tag.getBoolean("isCollect");
        collection = tag.getInt("collection");
        element = tag.getInt("element");
        super.handleUpdateTag(tag);
    }


    public boolean setCore(ItemStack itemStack){
        if(itemStack.getItem() == ItemLoader.voidElementCore.get()){
           if(this.coreItem == ItemStack.EMPTY || this.coreItem.isEmpty()){
               this.coreItem = itemStack;
               return true;
           }else
            return false;
        }else
            return false;

    }

    public ItemStack getCore(){
        if(this.coreItem == ItemStack.EMPTY && this.coreItem.isEmpty()) return ItemStack.EMPTY;
        else{ return this.coreItem;}
    }

    //是否放入的是空的ElementCore
    public boolean hasEmptyElementCore(){
        try{
        return this.coreItem != ItemStack.EMPTY && this.coreItem.getItem() == ItemLoader.voidElementCore.get();
        }catch (Exception ex){
            return false;
        }
    }

    public void resetCollect(){
        this.collection = 0;
        switch(this.element){
            case 1:
                this.coreItem = new ItemStack(ItemLoader.goldElementCore.get(),1);break;
            case 2:
                this.coreItem = new ItemStack(ItemLoader.woodElementCore.get(),1);break;
            case 3:
                this.coreItem = new ItemStack(ItemLoader.aquaElementCore.get(),1);break;
            case 4:
                this.coreItem = new ItemStack(ItemLoader.fireElementCore.get(),1);break;
            case 5:
                this.coreItem = new ItemStack(ItemLoader.earthElementCore.get(),1);break;
                default:
                    this.coreItem = new ItemStack(ItemLoader.earthElementCore.get(),1);break;
        }
        this.element = 0;


    }

    @Override
    public void tick() {
        boolean flag = false;
        if(world.isRemote){
            if(!isCollect && hasEmptyElementCore()){
                this.isCollect = true;
                this.element = ElementHelper.getBiomeElement(world.getBiome(pos));
                flag = true;
            }else if(isCollect && element > 0){
                this.collection += 1;
                flag = true;
            }

            if(this.collection >= this.MAX_COLLECTION){
                resetCollect();
            }

            if((this.coreItem == ItemStack.EMPTY || this.coreItem.isEmpty()) && this.collection > 0){
                this.element = 0;
                this.isCollect = false;
                this.collection = 0;
                flag = true;
            }
        }

        if(flag){
            world.notifyBlockUpdate(pos,getBlockState(),getBlockState(),3);
            this.markDirty();
        }
    }
}
