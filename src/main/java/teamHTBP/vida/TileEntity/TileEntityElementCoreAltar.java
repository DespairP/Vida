package teamHTBP.vida.TileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import teamHTBP.vida.block.BlockLoader;
import teamHTBP.vida.helper.elementHelper.ElementHelper;
import teamHTBP.vida.helper.elementHelper.ElementManager;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.helper.elementHelper.IElement;
import teamHTBP.vida.item.function.ItemElementCore;
import teamHTBP.vida.recipe.RecipeManager;
import teamHTBP.vida.recipe.recipe.AltarRecipe;

import javax.annotation.Nullable;
import java.util.Optional;

public class TileEntityElementCoreAltar extends TileEntity implements ITickableTileEntity {
    //最大的仪式进度
    public static int MAX_PROGRESS = 30000;
    //核心物品
    public ItemStack coreItem = ItemStack.EMPTY;
    //祭坛物品（最多4个）
    public NonNullList<ItemStack> altarItem = NonNullList.withSize(4,ItemStack.EMPTY);
    //是否正在仪式
    public boolean isProgressing = false;
    //法杖右键检测是否可以进行仪式
    public boolean isWAND_VIDACilck = false;
    //仪式进度
    public int progress = 0;
    //元素类型
    public IElement element = EnumElements.NONE;
    //渲染方面的东西，悬浮数
    public double floating = 0.0f;
    public float moveup = 0.0f;
    //额外的祭坛物品,最大可以存10个物品
    ItemStack[] extraItem = new ItemStack[10];
    //上方是否有方块
    boolean isBlockOver = false;
    //是否生成元素晶体
    boolean isElementOver = false;
    //是否有多方块结构，TODO
    boolean isMultiComplete = false;
    //元素核心
    ElementHelper helper = new ElementHelper();
    float floater = 0.0f;

    public TileEntityElementCoreAltar() {
        super(TileEntityLoader.TileEntityElementCoreAltar.get());
    }

    //读入NBT

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        isProgressing = compound.getBoolean("isProgressing");
        progress = compound.getInt("progress");
        isBlockOver = compound.getBoolean("isBlockOver");
        isElementOver = compound.getBoolean("isElementOver");
        //isMultiComplete = compound.getBoolean("isMutiComplete");
        isWAND_VIDACilck = compound.getBoolean("isWAND_VIDAClick");
        element = ElementManager.get(compound.getString("element"));
        for (int i = 0; i < 4; i++) {
            if (compound.contains("altarItem" + i)) {
                altarItem.set(i,ItemStack.of(compound.getCompound("altarItem" + i)));
            }
        }
        this.extraItem = new ItemStack[10];
        ListNBT nbtlist = compound.getList("extraItemList", 10);
        for (int j = 0; j < nbtlist.size(); j++) {
            CompoundNBT nbt = nbtlist.getCompound(j);
            this.extraItem[j] = ItemStack.of(nbt);
        }
        if (compound.contains("coreItem")) {
            this.coreItem = ItemStack.of(compound.getCompound("coreItem"));
        }
        super.load(state, compound);
    }

    //写入NBT
    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.putInt("progress", progress);
        compound.putString("element", element.getElementName().toString());
        compound.putBoolean("isProgressing", isProgressing);
        compound.putBoolean("isBlockOver", isBlockOver);
        //compound.putInt("isMutiComplete", isMultiComplete);
        compound.putBoolean("isMultiComplete", isMultiComplete);
        compound.putBoolean("isWAND_VIDAClick", isWAND_VIDACilck);
        for (int i = 0; i < 4; i++) {
            if (altarItem.size() > i) {
                compound.put("altarItem" + i, Optional.ofNullable(altarItem.get(i)).orElse(ItemStack.EMPTY).serializeNBT());
            }
        }
        if (this.coreItem != null) {
            compound.put("coreItem", coreItem.serializeNBT());
        }
        ListNBT listNBT = new ListNBT();
        for (int j = 0; j < this.extraItem.length; j++) {
            if (this.extraItem[j] != null) {
                listNBT.set(j, this.extraItem[j].serializeNBT());
            }
        }
        compound.put("extraItemList", listNBT);


        return super.save(compound);
    }

    //服务端->客户端
    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 1, this.getUpdateTag());
    }

    //区块刚刚被加载时，服务端->客户端
    @Override
    public CompoundNBT getUpdateTag() {
        return save(new CompoundNBT());

    }

    //客户端接收数据包
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(level.getBlockState(worldPosition), pkt.getTag());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        isProgressing = tag.getBoolean("isProgressing");
        progress = tag.getInt("progress");
        isBlockOver = tag.getBoolean("isBlockOver");
        isElementOver = tag.getBoolean("isElementOver");
        //isMultiComplete = tag.getBoolean("isMutiComplete");
        isWAND_VIDACilck = tag.getBoolean("isWAND_VIDAClick");
        element = ElementHelper.read(tag);
        for (int i = 0; i < 4; i++) {
            altarItem.set(i,tag.contains("altarItem" + i) ? ItemStack.of(tag.getCompound("altarItem" + i)) : ItemStack.EMPTY);
        }
        this.extraItem = new ItemStack[10];
        ListNBT nbtlist = tag.getList("extraItemList", 10);
        for (int j = 0; j < nbtlist.size(); j++) {
            CompoundNBT nbt = nbtlist.getCompound(j);
            this.extraItem[j] = ItemStack.of(nbt);
        }
        if (tag.contains("coreItem")) {
            this.coreItem = ItemStack.of(tag.getCompound("coreItem"));
        }

        super.handleUpdateTag(state, tag);
    }

    /**获取祭坛中还有的空余物品栈*/
    public int getEmptyAltarItemStackNum() {
        return altarItem.stream().reduce(0,(prev,itemstack)->prev + (itemstack.isEmpty() ? 1:0),Integer::sum);
    }

    /**
     * 放入祭坛物品
     * @return 是否能放入
     * */
    public boolean setAltarItemStack(ItemStack itemStack) {
        //如果在进行仪式，不能放物品
        if (this.isProgressing) return false;
        for (int i = 0; i < 4; i++) {
            if (this.altarItem.get(i).isEmpty()) {
                this.altarItem.set(i, Optional.ofNullable(itemStack).orElse(ItemStack.EMPTY));
                return true;
            }
        }
        return false;
    }

    public boolean setCoreItemStack(ItemStack itemStack) {
        //如果在进行仪式，不能放物品
        if (this.isProgressing) return false;
        if (itemStack.getItem() instanceof ItemElementCore && (this.coreItem == ItemStack.EMPTY || this.coreItem.isEmpty())) {
            this.coreItem = itemStack;
            return true;
        }
        return false;
    }

    /**
     * 拿回祭坛物品
     * @return 拿回的物品,如果不能拿回返回 {@link ItemStack#EMPTY}
     * */
    public ItemStack getAltarItemStack() {
        //如果在进行仪式，不能拿取物品
        if (this.isProgressing) return ItemStack.EMPTY;
        for (int i = 0; i < 4; i++) {
            if (!this.altarItem.get(i).isEmpty()) {
                return this.altarItem.get(i);
            }
        }
        if (this.coreItem != ItemStack.EMPTY) return this.coreItem;
        return ItemStack.EMPTY;
    }


    //仪式确认，元素和开始
    public boolean progressStart() {
        return this.isProgressing;
    }

    public ItemStack getStack(int i) {
        if (i < 4 && this.altarItem.size() > i) {
            return this.altarItem.get(i);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void tick() {
        //System.out.println(this.altarItem[0] + "  " + this.altarItem[1] +"   " +this.altarItem[2]  +"   " +this.altarItem[3]);
        //System.out.println(this.coreItem);
        if (!level.isClientSide) {
            //如果右键则开始进行
            if (this.isWAND_VIDACilck && !this.isProgressing) {
                //System.out.println("s");
                this.isProgressing = this.judAltarItem();
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
                this.isWAND_VIDACilck = false;
            }

        }
        //渲染
        if (this.isProgressing) {
            if (this.moveup >= 0.8f) {
                this.moveup = 0.8f;
                this.floater = (this.floater > Math.PI * 2 ? 0 : this.floater + 0.1f);
                this.floating = Math.sin(floater) * 0.1;
            } else this.moveup += 0.007;
        }
        if (this.isProgressing && this.progress <= MAX_PROGRESS) {
            this.progress += 15;
        }

        this.isBlockOver = level.getBlockState(this.getBlockPos().above()).getBlock() != Blocks.AIR;

        if (this.isProgressing && this.progress >= MAX_PROGRESS) {
            this.generateCrystal();
        }

        //world.notifyBlockUpdate(pos,getBlockState(),getBlockState(),3);
    }

    /**鉴定是否能开始仪式,如果能开始赋予Element值*/
    public boolean judAltarItem() {
        //检测四个物品是否为空
        for (int i = 0; i < 4; i++) {
            if (altarItem.get(i).isEmpty()) return false;
        }

        AltarRecipe recipe = RecipeManager.getAltarRecipe(level,this);

        if (recipe != null) {
            this.element = recipe.element;
            return true;
        }

        return false;
    }

    //清除掉entity的仪式进行状态
    public void clear() {
        this.progress = 0;
        this.isProgressing = false;
        //清除仪式物品
        this.altarItem.clear();
        //清除额外物品
        this.extraItem = new ItemStack[10];
        //清除核心物品
        this.coreItem = ItemStack.EMPTY;
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    //生成水晶
    public void generateCrystal() {
        assert level != null;
        if (!this.isBlockOver) {
            this.clear();
            if (element == EnumElements.GOLD) {
                level.setBlockAndUpdate(this.worldPosition.above(), BlockLoader.elementCrystalGold.get().defaultBlockState());
            } else if (element == EnumElements.WOOD) {
                level.setBlockAndUpdate(this.worldPosition.above(), BlockLoader.elementCrystalWood.get().defaultBlockState());
            } else if (element == EnumElements.AQUA) {
                level.setBlockAndUpdate(this.worldPosition.above(), BlockLoader.elementCrystalAqua.get().defaultBlockState());
            } else if (element == EnumElements.FIRE) {
                level.setBlockAndUpdate(this.worldPosition.above(), BlockLoader.elementCrystalFire.get().defaultBlockState());
            } else if (element == EnumElements.EARTH) {
                level.setBlockAndUpdate(this.worldPosition.above(), BlockLoader.elementCrystalEarth.get().defaultBlockState());
            }
        }
    }
}
