package teamHTBP.vida.TileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import teamHTBP.vida.TileEntity.SlotNumberArray.PrismTableArray;
import teamHTBP.vida.gui.GUI.ContainerPrismTable;
import teamHTBP.vida.helper.elementHelper.ElementHelper;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.helper.elementHelper.IElement;
import teamHTBP.vida.item.ItemLoader;
import teamHTBP.vida.item.function.ItemEnergyElementFragment;

import javax.annotation.Nullable;
import java.util.Random;

public class TileEntityPrismTable extends TileEntity implements INamedContainerProvider, ITickableTileEntity {
    //Slot的inventory
    private final Inventory slot = new Inventory(3);
    //prismTable所需要的数值
    public PrismTableArray array = new PrismTableArray();
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


    public TileEntityPrismTable() {
        super(TileEntityLoader.TileEntityPrismTable.get());
    }


    @Override
    public void read(BlockState state, CompoundNBT compound) {
        this.slot.setInventorySlotContents(0, ItemStack.read(compound.getCompound("item1")));
        this.slot.setInventorySlotContents(1, ItemStack.read(compound.getCompound("item2")));
        this.slot.setInventorySlotContents(2, ItemStack.read(compound.getCompound("item3")));
        array.set(0, compound.getInt("fireX"));
        array.set(1, compound.getInt("fireY"));
        array.set(2, compound.getInt("mirrorX"));
        array.set(3, compound.getInt("mirrorY"));
        isGem = compound.getBoolean("isGem");
        isMirror = compound.getBoolean("isMirror");
        isFire = compound.getBoolean("isFire");
        super.read(state, compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ItemStack itemStack1 = this.slot.getStackInSlot(0).copy();
        ItemStack itemStack2 = this.slot.getStackInSlot(1).copy();
        ItemStack itemStack3 = this.slot.getStackInSlot(2).copy();
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
        //this.slot.addItem(ItemStack.read(tag.getCompound("item")));
        array.set(0, tag.getInt("fireX"));
        array.set(1, tag.getInt("fireY"));
        array.set(2, tag.getInt("mirrorX"));
        array.set(3, tag.getInt("mirrorY"));
        isGem = tag.getBoolean("isGem");
        isMirror = tag.getBoolean("isMirror");
        isFire = tag.getBoolean("isFire");
        super.read(state, tag);
    }

    public Inventory getSlot() {
        return slot;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("PrismTable");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new ContainerPrismTable(id, p_createMenu_2_, this.pos, this.world, this.array);
    }

    @Override
    public void tick() {
        // world.notifyBlockUpdate(pos, getBlockState(),getBlockState(),3);
        this.isMirror = this.slot.getStackInSlot(1).getItem() == ItemLoader.prism.get();
        if (this.slot.getStackInSlot(0) == ItemStack.EMPTY && !(this.slot.getStackInSlot(0).getItem() instanceof ItemEnergyElementFragment)) {
            //如果没有任何东西的话
            this.isGem = false;
            this.array.set(0, 0);
            this.array.set(1, 0);
        }
        if (!world.isRemote) {
            //如果没有宝石在里面就开始检测
            if (!this.isGem) {
                gemInside();
                world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 3);
                this.markDirty();
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
        world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 3);
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
                ItemStack itemStack = this.slot.getStackInSlot(0);
                IElement element = ElementHelper.getContainingElement(itemStack);

                if (this.slot.getStackInSlot(2) == ItemStack.EMPTY || this.slot.getStackInSlot(2).isEmpty()) {
                    this.slot.setInventorySlotContents(2, this.getItemGemFromElement(element));
                    int count = this.slot.getStackInSlot(0).getCount();
                    this.slot.getStackInSlot(0).setCount(count - 1);
                    clearPrismTable();
                } else if (this.slot.getStackInSlot(2).getItem() == this.getItemGemFromElement(element).getItem() && this.slot.getStackInSlot(2).getCount() < 64) {
                    int count = this.slot.getStackInSlot(2).getCount();
                    this.slot.getStackInSlot(2).setCount(count + 1);
                    count = this.slot.getStackInSlot(0).getCount();
                    this.slot.getStackInSlot(0).setCount(count - 1);
                    clearPrismTable();
                }


            }
        }
    }

    //检测是否有宝石在里面，如果有生成火焰
    public void gemInside() {
        //检测是否格子0中有宝石
        Item item = null; //宝石的item
        ItemStack itemStack = this.slot.getStackInSlot(0); //先获取itemStak
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
                    return new ItemStack(ItemLoader.ARTIFICIAL_ELEMENTGEM_GOLD.get(), 1);
                case WOOD:
                    return new ItemStack(ItemLoader.ARTIFICIAL_ELEMENTGEM_WOOD.get(), 1);
                case AQUA:
                    return new ItemStack(ItemLoader.ARTIFICIAL_ELEMENTGEM_AQUA.get(), 1);
                case FIRE:
                    return new ItemStack(ItemLoader.ARTIFICIAL_ELEMENTGEM_FIRE.get(), 1);
                case EARTH:
                    return new ItemStack(ItemLoader.ARTIFICIAL_ELEMENTGEM_EARTH.get(), 1);
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
