package teamHTBP.vida.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.api.core.element.IElement;
import teamHTBP.vida.common.block.VidaBlockLoader;
import teamHTBP.vida.common.blockentity.base.VidaBaseBlockEntity;
import teamHTBP.vida.common.item.function.ItemElementCore;
import teamHTBP.vida.common.recipe.VidaRecipeTypeLoader;
import teamHTBP.vida.common.recipe.recipe.AltarRecipe;
import teamHTBP.vida.core.element.ElementManager;
import teamHTBP.vida.core.element.EnumElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ElementCoreAltarBlockEntity extends VidaBaseBlockEntity {
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
    float floater = 0.0f;

    public ElementCoreAltarBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(VidaBlockEntityLoader.TileEntityElementCoreAltar.get(), pWorldPosition, pBlockState);
    }

    //读入NBT

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
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
        ListTag nbtlist = compound.getList("extraItemList", 10);
        for (int j = 0; j < nbtlist.size(); j++) {
            CompoundTag nbt = nbtlist.getCompound(j);
            this.extraItem[j] = ItemStack.of(nbt);
        }
        if (compound.contains("coreItem")) {
            this.coreItem = ItemStack.of(compound.getCompound("coreItem"));
        }
    }

    //写入NBT
    @Override
    public void saveAdditional(CompoundTag compound) {
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
        ListTag listNBT = new ListTag();
        for (int j = 0; j < this.extraItem.length; j++) {
            if (this.extraItem[j] != null) {
                listNBT.set(j, this.extraItem[j].serializeNBT());
            }
        }
        compound.put("extraItemList", listNBT);


        super.saveAdditional(compound);
    }

    @Override
    public List<ItemStack> getDrops() {
        List<ItemStack> itemStacks = new ArrayList<>(altarItem);
        itemStacks.addAll(List.of(extraItem));
        itemStacks.add(coreItem);
        return itemStacks;
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

        AltarRecipe recipe = getAltarRecipe(level,this);

        if (recipe != null) {
            this.element = recipe.element;
            return true;
        }

        return false;
    }

    public static AltarRecipe getAltarRecipe(Level level, ElementCoreAltarBlockEntity altar){
        level.getRecipeManager().getAllRecipesFor(VidaRecipeTypeLoader.ALTAR.get());
        return level.getRecipeManager().getAllRecipesFor(VidaRecipeTypeLoader.ALTAR.get()).stream().filter(
                r -> r.matches(altar)
        ).findFirst().orElse(null);
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
                level.setBlockAndUpdate(this.worldPosition.above(), VidaBlockLoader.GOLD_CRYSTAL.get().defaultBlockState());
            } else if (element == EnumElements.WOOD) {
                level.setBlockAndUpdate(this.worldPosition.above(), VidaBlockLoader.WOOD_CRYSTAL.get().defaultBlockState());
            } else if (element == EnumElements.AQUA) {
                level.setBlockAndUpdate(this.worldPosition.above(), VidaBlockLoader.AQUA_CRYSTAL.get().defaultBlockState());
            } else if (element == EnumElements.FIRE) {
                level.setBlockAndUpdate(this.worldPosition.above(), VidaBlockLoader.FIRE_CRYSTAL.get().defaultBlockState());
            } else if (element == EnumElements.EARTH) {
                level.setBlockAndUpdate(this.worldPosition.above(), VidaBlockLoader.EARTH_CRYSTAL.get().defaultBlockState());
            }
        }
    }
}
