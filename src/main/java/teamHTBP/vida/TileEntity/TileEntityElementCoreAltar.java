package teamHTBP.vida.TileEntity;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import teamHTBP.vida.Block.BlockLoader;
import teamHTBP.vida.Capability.ElementHelper;
import teamHTBP.vida.Item.ItemElementCore;
import teamHTBP.vida.Item.ItemLoader;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;

public class TileEntityElementCoreAltar extends TileEntity implements ITickableTileEntity {
    //核心物品
    public ItemStack coreItem = ItemStack.EMPTY;
    //祭坛物品（最多4个）
    public ItemStack altarItem[] = {ItemStack.EMPTY,ItemStack.EMPTY, ItemStack.EMPTY,ItemStack.EMPTY};
    //额外的祭坛物品,最大可以存10个物品
    ItemStack extraItem[] = new ItemStack[10];
    //是否正在仪式
    public boolean isProgressing = false;
    //上方是否有方块
    boolean isBlockOver = false;
    //是否生成元素晶体
    boolean isElementOver = false;
    //是否有多方块结构，TODO
    boolean isMultiComplete = false;
    //法杖右键检测是否可以进行仪式
    public boolean isVidaWandCilck = false;
    //仪式进度
    public int progress = 0;
    //最大的仪式进度
    public static int MAX_PROGRESS = 30000;
    //元素类型
    public int element = 0;
    //元素核心
    ElementHelper helper = new ElementHelper();


    //渲染方面的东西，悬浮数
   public double floating = 0.0f;
    float floater = 0.0f;
    public float moveup = 0.0f;

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
        isVidaWandCilck = compound.getBoolean("isVidaWandClick");
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

    //写入NBT
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("progress", progress);
        compound.putInt("element", element);
        compound.putBoolean("isProgressing", isProgressing);
        compound.putBoolean("isBlockOver", isBlockOver);
        //compound.putInt("isMutiComplete", isMultiComplete);
        compound.putBoolean("isMultiComplete", isMultiComplete);
        compound.putBoolean("isVidaWandClick", isVidaWandCilck);
        for(int i = 0; i < 4 ; i ++){
            if(this.altarItem[i] != null){
                compound.put("altarItem"+i,this.altarItem[i].serializeNBT());
            }
        }
        if(this.coreItem != null){
            compound.put("coreItem", coreItem.serializeNBT());
        }
        ListNBT listNBT = new ListNBT();
        for(int j = 0;j < this.extraItem.length;j++){
            if(this.extraItem[j] != null){
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

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        isProgressing = tag.getBoolean("isProgressing");
        progress = tag.getInt("progress");
        isBlockOver = tag.getBoolean("isBlockOver");
        isElementOver = tag.getBoolean("isElementOver");
        isMultiComplete = tag.getBoolean("isMutiComplete");
        isVidaWandCilck = tag.getBoolean("isVidaWandClick");
        element = tag.getInt("element");
        for(int i = 0;i < 4;i++){
            if(tag.contains("altarItem" + i)){
                altarItem[i] =ItemStack.read( tag.getCompound("altarItem" + i));
            }else{
                altarItem[i] =ItemStack.EMPTY;
            }
        }
        this.extraItem = new ItemStack[10];
        ListNBT nbtlist = tag.getList("extraItemList", 10);
        if(nbtlist != null){
            for(int j = 0; j< nbtlist.size();j++){
                CompoundNBT nbt = nbtlist.getCompound(j);
                if(nbt != null)   this.extraItem[j] = ItemStack.read(nbt);
            }
        }
        if(tag.contains("coreItem")){
            this.coreItem =ItemStack.read( tag.getCompound("coreItem") );
        }
        super.handleUpdateTag(tag);

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
        //如果在进行仪式，不能放物品
        if(this.isProgressing) return false;
        for(int i = 0; i < 4;i ++){
            if(this.altarItem[i] == ItemStack.EMPTY || this.altarItem[i].isEmpty()) { this.altarItem[i] = itemStack; return true;}
        }
        return false;
    }

    public boolean setCoreItemStack(ItemStack itemStack){
        //如果在进行仪式，不能放物品
        if(this.isProgressing) return false;
        if(itemStack.getItem().getItem() instanceof ItemElementCore && (this.coreItem == ItemStack.EMPTY||this.coreItem.isEmpty())){
           this.coreItem = itemStack;
           return true;
        }
        return false;
    }

    public ItemStack getAltarItemStack(){
        //如果在进行仪式，不能拿取物品
        if(this.isProgressing == true) return ItemStack.EMPTY;
        for(int i = 0; i < 4 ;i++){
        if(this.altarItem[i] != ItemStack.EMPTY && !this.altarItem[i].isEmpty()) {
            return this.altarItem[i];
        }
        }
        if(this.coreItem != ItemStack.EMPTY) return this.coreItem;
        return ItemStack.EMPTY;
    }


    //仪式确认，元素和开始
    public boolean progressStart(){
        return this.isProgressing;
    }

    public ItemStack getStack(int i){
        if(i<4) return this.altarItem[i];
        return ItemStack.EMPTY;
    }

    @Override
    public void tick() {
       //System.out.println(this.altarItem[0] + "  " + this.altarItem[1] +"   " +this.altarItem[2]  +"   " +this.altarItem[3]);
      //System.out.println(this.coreItem);
        if(!world.isRemote){
            //如果右键则开始进行
              if(this.isVidaWandCilck && !this.isProgressing){
                  //System.out.println("s");
                  this.isProgressing = this.judAltarItem();
                  world.notifyBlockUpdate(pos, getBlockState(),getBlockState(), 3);
                  this.isVidaWandCilck = false;
             }

        }
        //渲染
        if(this.isProgressing){
            if(this.moveup >= 0.8f){ this.moveup=0.8f;
            this.floater = (this.floater > Math.PI * 2?0:this.floater+0.1f);
            this.floating = Math.sin(floater) * 0.1;
            }
            else this.moveup += 0.007;
        }
        if(this.isProgressing && this.progress<=MAX_PROGRESS){
            this.progress += 15;
        }
        if(world.getBlockState(this.getPos().up()).getBlock() == Blocks.AIR){
            this.isBlockOver = false;
        }else{
            this.isBlockOver = true;
        }
        if(this.isProgressing && this.progress>=this.MAX_PROGRESS){
            this.generateCrystal();
        }

        //world.notifyBlockUpdate(pos,getBlockState(),getBlockState(),3);
    }

    public boolean judAltarItem(){
        //检测四个物品是否为空
        for(int i=0;i < 4;i++){
            if(this.altarItem[i] == ItemStack.EMPTY || this.altarItem[i].isEmpty()) return false;
        }
        List<Item> itemList = new LinkedList<Item>();
        for(int j=0;j < 4;j++){
        itemList.add(this.altarItem[j].getItem());
        }
        int element = helper.getContainingElement(this.getAltarItemStack());
        System.out.println(this.getAltarItemStack());
        if(ElementHelper.beganAltarProgress(this.coreItem,itemList, element)){
            this.element = element;
            return true;
        }
        return false;
    }

    //清除掉entity的仪式进行状态
    public void clear(){
        this.progress = 0;
        this.isProgressing = false;
        //清除仪式物品
        for(int i = 0;i<4;i++)
            this.altarItem[i] = ItemStack.EMPTY;
        //清除额外物品
        this.extraItem = new ItemStack[10];
        //清除核心物品
        this.coreItem = ItemStack.EMPTY;
        world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 3);
    }

    //生成水晶
    public void generateCrystal(){
        if(!this.isBlockOver){
            this.clear();
            switch (this.element) {
                case 1:
                     world.setBlockState(this.pos.up(), BlockLoader.elementCrystalGold.get().getDefaultState());
                     break;
                case 2:
                     world.setBlockState(this.pos.up(), BlockLoader.elementCrystalWood.get().getDefaultState());
                     break;
                case 3:
                    world.setBlockState(this.pos.up(), BlockLoader.elementCrystalAqua.get().getDefaultState());
                    break;
                case 4:
                    world.setBlockState(this.pos.up(), BlockLoader.elementCrystalFire.get().getDefaultState());
                    break;
                case 5:
                    world.setBlockState(this.pos.up(), BlockLoader.elementCrystalEarth.get().getDefaultState());
            }
        }

    }
}
