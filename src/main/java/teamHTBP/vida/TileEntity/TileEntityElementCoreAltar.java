package teamHTBP.vida.TileEntity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import teamHTBP.vida.Item.ItemLoader;

import javax.annotation.Nullable;

public class TileEntityElementCoreAltar extends TileEntity implements ITickableTileEntity {
    //核心物品
    public ItemStack coreItem = ItemStack.EMPTY;
    //祭坛物品（最多4个）
    public ItemStack altarItem[] = {ItemStack.EMPTY,ItemStack.EMPTY, ItemStack.EMPTY,ItemStack.EMPTY};
    //额外的祭坛物品,最大可以存10个物品
    ItemStack extraItem[] = new ItemStack[10];
    //是否正在仪式
    boolean isProgressing = false;
    //上方是否有方块
    boolean isBlockOver = false;
    //是否生成元素晶体
    boolean isElementOver = false;
    //是否有多方块结构，TODO
    boolean isMultiComplete = false;
    //仪式进度
    int progress = 0;
    //最大的仪式进度
    public static int MAX_PROGRESS = 30000;
    //元素类型
    int element = 0;



    //渲染方面的东西，悬浮数
    float floating = 0.0f;


    public TileEntityElementCoreAltar() {
        super(TileEntityLoader.TileEntityElementCoreAltar.get());
    }

    //读入NBT
    public void read(CompoundNBT compound) {
        isProgressing = compound.getBoolean("isProgressing");
        progress = compound.getInt("progress");
        isBlockOver = compound.getBoolean("isBlockOver");
        isElementOver = compound.getBoolean("isElementOver");
        isMultiComplete = compound.getBoolean("isMutiComplete");
        element = compound.getInt("element");
        for(int i = 0;i < 4;i++){
            if(compound.contains("altarItem" + i)){
                altarItem[i] =ItemStack.read( compound.getCompound("altarItem" + i));
            }
        }
        this.extraItem = new ItemStack[10];
        ListNBT nbtlist = compound.getList("extraItemList", 10);
        if(nbtlist != null){
            for(int j = 0; j< nbtlist.size();j++){
               CompoundNBT nbt = nbtlist.getCompound(j);
               if(nbt != null)   this.extraItem[j] = ItemStack.read(nbt);
           }
        }
        if(compound.contains("coreItem")){
            this.coreItem =ItemStack.read( compound.getCompound("coreItem") );
        }
        super.read(compound);
    }

    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("progress", progress);
        compound.putInt("element", element);
        compound.putBoolean("isProgressing", isProgressing);
        compound.putBoolean("isBlockOver", isBlockOver);
        //compound.putInt("isMutiComplete", isMultiComplete);
        compound.putBoolean("isMultiComplete", isMultiComplete);
        for(int i = 0; i < 4 ; i ++){
            if(this.altarItem[i] != ItemStack.EMPTY){
                compound.put("altarItem"+i,this.altarItem[i].serializeNBT());
            }
        }
        if(this.coreItem != ItemStack.EMPTY){
            compound.put("coreItem", coreItem.serializeNBT());
        }
        ListNBT listNBT = new ListNBT();
        for(int j = 0;j < this.extraItem.length;j++){
            if(this.extraItem[j] != null && this.extraItem[j] != ItemStack.EMPTY){
                listNBT.set(j, this.extraItem[j].serializeNBT());
            }
        }
        compound.put("extraItemList",listNBT);


        return super.write(compound);
    }

    //服务端->客户端
    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos,1,this.getUpdateTag());
    }

    //区块刚刚被加载时，服务端->客户端
    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());

    }

    //客户端接收数据包
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net,pkt);
        handleUpdateTag(pkt.getNbtCompound());
    }

    //获取祭坛中还有的空余物品栈
    public int getEmptyAltarItemStackNum(){
        int empty = 0;
        for(int i = 0; i < 4 ;i ++){
            if(this.altarItem[i] != ItemStack.EMPTY) empty++;
        }
        return empty;
    }

    public boolean setAltarItemStack(ItemStack itemStack){
        for(int i=0; i < 4;i ++){
            if(this.altarItem[i] == ItemStack.EMPTY || this.altarItem[i].isEmpty()) { this.altarItem[i] = itemStack; return true;}
        }
        return false;
    }

    public boolean setCoreItemStack(ItemStack itemStack){
        if(itemStack.getItem().getItem() == ItemLoader.goldElementCore.get() && (this.coreItem == ItemStack.EMPTY||this.coreItem.isEmpty())){
           this.coreItem = itemStack;
           return true;
        }
        return false;
    }

    public ItemStack getAltarItemStack(){
        for(int i = 0; i < 4 ;i++){
        if(this.altarItem[i] != ItemStack.EMPTY && !this.altarItem[i].isEmpty()) { return this.altarItem[i];}
        }
        if(this.coreItem != ItemStack.EMPTY) return this.coreItem;
        return ItemStack.EMPTY;
    }


    //仪式确认，元素和开始
    public boolean progressStart(){
        return false;
    }


    @Override
    public void tick() {
      // System.out.println(this.altarItem[0] + "  " + this.altarItem[1] +"   " +this.altarItem[2]  +"   " +this.altarItem[3]);
      //System.out.println(this.coreItem);
    }
}
