package teamHTBP.vida.TileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import teamHTBP.vida.Capability.ElementHelper;

import javax.annotation.Nullable;

public class TileEntityPurfiedCauldron extends TileEntity implements ITickableTileEntity {
    //提炼值
    public int container = 0;
    //满提炼值
    public final int MAX_CONTAINER = 30000;
    //正在提炼的缓冲值
    public int containing = 0;
    //提炼的物品
    public ItemStack meltItem = ItemStack.EMPTY;
    //是否有水
    public boolean isWater = false;
    //是否底下有火
    public boolean isFire = false;
    //加速熔炼速度,speed * 10 =actualSpeed
    public int speed = 1;
    //锅内的元素
    public int element = 0;
    //
    ElementHelper helper = new ElementHelper();


    //五元素数值
    public final int ELEMENT_GOLD = 1;
    public final int ELEMENT_WOOD = 2;
    public final int ELEMENT_AQUA = 3;
    public final int ELEMENT_FIRE = 4;
    public final int ELEMENT_EARTH = 5;

    public TileEntityPurfiedCauldron() {
        super(TileEntityLoader.TileEntityPurfiedCauldron.get());
    }

    /**设置提炼物品
     * @return 是否有其他提炼的物品正在提炼
     * 只有在有水燃烧和火在锅中的情况下才接收物品
     * **/
    public boolean setMeltItem(ItemStack meltItem){
        if(this.meltItem.isEmpty()&&this.isWater&&this.isFire && (element==0 || element == this.getContainingElement(meltItem))){
            this.meltItem = meltItem;
            return true;
        }
        return false;
    }

    public void read(CompoundNBT compound) {
        containing = compound.getInt("containing");
        container = compound.getInt("container");
        isWater = compound.getBoolean("isWater");
        isFire = compound.getBoolean("isFire");
        element = compound.getInt("element");
        if(compound.contains("meltItem"))
        meltItem = ItemStack.read(compound.getCompound("meltItem"));

        super.read(compound);
    }

    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("containing", containing);
        compound.putInt("container", container);
        compound.putBoolean("isWater", isWater);
        compound.putBoolean("isFire", isFire);
        compound.putInt("element", element);
        if(!meltItem.isEmpty())
        compound.put("meltItem",meltItem.serializeNBT());
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

    //客户端处理收到的数据包
    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        isFire = tag.getBoolean("isFire");
        isWater = tag.getBoolean("isWater");
        container = tag.getInt("container");
        containing = tag.getInt("containing");
        element = tag.getInt("element");
        if(tag.contains("meltItem"))
            meltItem = ItemStack.read(tag.getCompound("meltItem"));
        else
            meltItem = ItemStack.EMPTY;
    }



    //内部逻辑实现
    @Override
    public void tick() {
        if(!world.isRemote){

            //如果缓冲值>0,先消耗缓冲值
            if(this.isWater && this.isFire){
            if(this.containing > 0){
                consumeContaining();
                if(containing == 0){
                    meltItem = ItemStack.EMPTY;
                }
            }else{
                getContaining();
            }
            //如果已满出，生成微光
            generateFaintLight();
            world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 3);
        }
            if(containing == 0){
                meltItem = ItemStack.EMPTY;
            }
        }
    }



    //消耗containing值
    public void consumeContaining(){
        if (containing < speed * 10){
            container += containing;
            containing = 0;
        }else{
            container += speed * 10;
            containing -= speed * 10;
        }
    }

    public void getContaining(){
        if(meltItem != ItemStack.EMPTY){
            //如果元素为空的话，先设置元素
            if(element == 0){
               element = getContainingElement(meltItem);
            }
            containing = getContainingNum(meltItem);
        }
    }


    //获得当前Containing
    public int getContainingNum(ItemStack itemStack){
        return helper.getContainingNum(itemStack);
    }

    //获得投入物品的元素
    public int getContainingElement(ItemStack itemStack){
        return helper.getContainingElement(itemStack);
    }

    //生成微光
    public void generateFaintLight(){
        if (container>MAX_CONTAINER - 1){
            clear();
        }
    }

    //清空
    public void clear(){
        this.container = 0;
        this.containing = 0;
        this.element = 0;
        this.isWater = false;
        if(world.getBlockState(pos.down()).getBlock() == Blocks.FIRE || world.getBlockState(pos.down()).getBlock() == Blocks.LAVA )
        this.isFire = true;
        else
            this.isFire = false;
        this.meltItem = ItemStack.EMPTY;
        markDirty();
    }
}
