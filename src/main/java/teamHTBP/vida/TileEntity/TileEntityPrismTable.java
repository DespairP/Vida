package teamHTBP.vida.TileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import teamHTBP.vida.TileEntity.SlotNumberArray.PrismTableArray;
import teamHTBP.vida.gui.GUI.ContainerPrismTable;

import javax.annotation.Nullable;

public class TileEntityPrismTable extends TileEntity implements INamedContainerProvider {
    //Slot的inventory
    private Inventory slot = new Inventory(3);
    //prismTable所需要的数值
    public PrismTableArray array = new PrismTableArray();
    //是否有镜子
    public boolean isMirror = false;
    

    public TileEntityPrismTable() {
        super(TileEntityLoader.TileEntityPrismTable.get());
    }


    @Override
    public void read(CompoundNBT compound) {
        this.slot.addItem(ItemStack.read(compound.getCompound("item")));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ItemStack itemStack = this.slot.getStackInSlot(0).copy();
        compound.put("item", itemStack.serializeNBT());
        return super.write(compound);
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
        return new ContainerPrismTable(id,p_createMenu_2_,this.pos,this.world,this.array);
    }
}
