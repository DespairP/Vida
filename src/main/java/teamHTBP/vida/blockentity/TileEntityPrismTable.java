package teamHTBP.vida.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.blockentity.data.PrismTableData;
import teamHTBP.vida.blockentity.base.VidaBaseMenuBlockEntity;
import teamHTBP.vida.menu.PrismTableMenu;
import teamHTBP.vida.element.ElementHelper;
import teamHTBP.vida.element.EnumElements;
import teamHTBP.vida.element.IElement;
import teamHTBP.vida.item.VidaItemRegistry;
import teamHTBP.vida.item.function.ItemEnergyElementFragment;
import teamHTBP.vida.utils.ContainerHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class TileEntityPrismTable extends VidaBaseMenuBlockEntity {
    //Slot的inventory
    private final SimpleContainer slot = new SimpleContainer(3);
    //prismTable所需要的数值
    public PrismTableData array = new PrismTableData();
    //是否有镜子
    public boolean isMirror = false;
    //是否已经生成火焰
    public boolean isFire = false;
    //是否已经有宝石在里面
    public boolean isGem = false;
    //是否被click
    public boolean isClick = false;
    //随机
    Random rand = new Random();

    public TileEntityPrismTable(BlockPos pWorldPosition, BlockState pBlockState) {
        super(TileEntityLoader.TileEntityPrismTable.get(), pWorldPosition, pBlockState);
    }

    @Override
    public List<ItemStack> getDrops() {
        return ContainerHelper.getItems(slot);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.slot.setItem(0, ItemStack.of(compound.getCompound("item1")));
        this.slot.setItem(1, ItemStack.of(compound.getCompound("item2")));
        this.slot.setItem(2, ItemStack.of(compound.getCompound("item3")));
        array.set(0, compound.getInt("fireX"));
        array.set(1, compound.getInt("fireY"));
        array.set(2, compound.getInt("mirrorX"));
        array.set(3, compound.getInt("mirrorY"));
        isGem = compound.getBoolean("isGem");
        isMirror = compound.getBoolean("isMirror");
        isFire = compound.getBoolean("isFire");
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        ItemStack itemStack1 = this.slot.getItem(0).copy();
        ItemStack itemStack2 = this.slot.getItem(1).copy();
        ItemStack itemStack3 = this.slot.getItem(2).copy();
        compound.put("item1", itemStack1.serializeNBT());
        compound.put("item2", itemStack2.serializeNBT());
        compound.put("item3", itemStack3.serializeNBT());
        compound.putInt("fireX", array.get(0));
        compound.putInt("fireY", array.get(1));
        compound.putInt("mirrorX", array.get(2));
        compound.putInt("mirrorY", array.get(3));
        compound.putBoolean("isGem", isGem);
        compound.putBoolean("isMirror", isMirror);
        compound.putBoolean("isFire", isFire);
        super.saveAdditional(compound);
    }

    public SimpleContainer getSlot() {
        return slot;
    }

    @Override
    public TextComponent getDisplayName() {
        return new TextComponent("PrismTable");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory p_createMenu_2_, Player p_createMenu_3_) {
        return new PrismTableMenu(id, p_createMenu_2_, this.worldPosition, this.level, this.array);
    }

    @Override
    public void tick() {
        // world.notifyBlockUpdate(pos, getBlockState(),getBlockState(),3);
        this.isMirror = this.slot.getItem(1).getItem() == VidaItemRegistry.prism.get();
        if (this.slot.getItem(0) == ItemStack.EMPTY && !(this.slot.getItem(0).getItem() instanceof ItemEnergyElementFragment)) {
            //如果没有任何东西的话
            this.isGem = false;
            this.array.set(0, 0);
            this.array.set(1, 0);
        }
        if (!level.isClientSide) {
            //如果没有宝石在里面就开始检测
            if (!this.isGem) {
                gemInside();
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
                this.setChanged();
            }
            if (this.isClick && this.isGem) {
                // System.out.println(this.array.get(0));
                gemPolish();
                this.isClick = false;
            }
        }
        this.scanFire();
        //System.out.println(this.isFire);
        //System.out.println(array.get(0) + " " + array.get(1));
    }

    public void clearPrismTable() {
        this.isGem = false;
        this.array.set(0, 0);
        this.array.set(1, 0);
        this.array.set(2, 0);
        this.array.set(3, 0);
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    //磨制人工水晶
    public void gemPolish() {
        if (this.isMirror) {
            int fireX = this.array.get(0);
            int fireY = this.array.get(1);
            int mirrorX = this.array.get(2);
            int mirrorY = this.array.get(3);
            int offset1 = Math.abs(mirrorX + 3 - fireX - 6);
            int offset2 = Math.abs(mirrorY + 3 - fireY - 17);
            // System.out.println(offset1 + " "  +offset2);
            if (offset1 < 10 && offset2 < 10) {
                ElementHelper elementHelper = new ElementHelper();
                ItemStack itemStack = this.slot.getItem(0);
                IElement element = ElementHelper.getContainingElement(itemStack);

                if (this.slot.getItem(2) == ItemStack.EMPTY || this.slot.getItem(2).isEmpty()) {
                    this.slot.setItem(2, this.getItemGemFromElement(element));
                    int count = this.slot.getItem(0).getCount();
                    this.slot.getItem(0).setCount(count - 1);
                    clearPrismTable();
                } else if (this.slot.getItem(2).getItem() == this.getItemGemFromElement(element).getItem() && this.slot.getItem(2).getCount() < 64) {
                    int count = this.slot.getItem(2).getCount();
                    this.slot.getItem(2).setCount(count + 1);
                    count = this.slot.getItem(0).getCount();
                    this.slot.getItem(0).setCount(count - 1);
                    clearPrismTable();
                }


            }
        }
    }

    //检测是否有宝石在里面，如果有生成火焰
    public void gemInside() {
        //检测是否格子0中有宝石
        Item item = null; //宝石的item
        ItemStack itemStack = this.slot.getItem(0); //先获取itemStak
        if (itemStack != null && !itemStack.isEmpty()) {
            item = itemStack.getItem(); //获得item
        }
        if (item instanceof ItemEnergyElementFragment) {
            this.array.set(0, rand.nextInt(80) + 80);
            this.array.set(1, rand.nextInt(25) + 16);
            this.isGem = true;
        }
    }

    //从放入的宝石得到会polish的宝石
    public ItemStack getItemGemFromElement(IElement element) {
        if (element instanceof EnumElements) {
            switch ((EnumElements) element) {
                case GOLD:
                    return new ItemStack(VidaItemRegistry.ARTIFICIAL_ELEMENTGEM_GOLD.get(), 1);
                case WOOD:
                    return new ItemStack(VidaItemRegistry.ARTIFICIAL_ELEMENTGEM_WOOD.get(), 1);
                case AQUA:
                    return new ItemStack(VidaItemRegistry.ARTIFICIAL_ELEMENTGEM_AQUA.get(), 1);
                case FIRE:
                    return new ItemStack(VidaItemRegistry.ARTIFICIAL_ELEMENTGEM_FIRE.get(), 1);
                case EARTH:
                    return new ItemStack(VidaItemRegistry.ARTIFICIAL_ELEMENTGEM_EARTH.get(), 1);
            }
        }
        return ItemStack.EMPTY;
    }

    //检测火是否生成在正确的位置
    public void scanFire() {
        if (this.isFire) {
            if (this.array.get(0) == 0 && this.array.get(1) == 0) {
                this.isFire = false;
            }
        }
    }
}
