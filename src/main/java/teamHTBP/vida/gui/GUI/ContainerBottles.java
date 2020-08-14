package teamHTBP.vida.gui.GUI;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import teamHTBP.vida.Item.armor.ItemArmorElementLegginsWithBottles;

import javax.annotation.Nullable;

public class ContainerBottles extends Container {
    ItemStack stack = ItemStack.EMPTY;
    Boolean isStack3Lock = true;
    public ContainerBottles(int id, PlayerInventory inv, ItemStack stack) {
        super(ContainerTypeLoader.bottles.get(), id);
        //getNBT
        ItemStack bottle1 = ItemStack.EMPTY,bottle2 = ItemStack.EMPTY,bottle3 = ItemStack.EMPTY;
        CompoundNBT nbt = stack.getOrCreateTag();
        if(nbt.getCompound("bottle1") != null) {
            bottle1 = ItemStack.read(nbt.getCompound("bottle1"));
        }
        if(nbt.getCompound("bottle2") != null) {
            bottle2 = ItemStack.read(nbt.getCompound("bottle2"));
        }
        if(nbt.getCompound("bottle3") != null) {
            bottle3 = ItemStack.read(nbt.getCompound("bottle3"));
        }



        this.addSlot( new potionSlot(new Inventory(bottle1),0,59,11));
        this.addSlot(new  potionSlot(new Inventory(bottle2), 0, 80, 11));
        if(((ItemArmorElementLegginsWithBottles)stack.getItem()).element == 2)
        {
            this.addSlot(new potionSlot(new Inventory(bottle3), 0, 101, 11));
            this.isStack3Lock = false;
        }
        else
            this.addSlot(new fobiddenSlot(new Inventory(bottle3), 0, 101, 11));

        this.stack = stack;
        layoutPlayerInventorySlots(inv, 8, 59);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        if(stack == playerIn.inventory.getCurrentItem())
        return true;
        else
        return false;
    }

    /**-------以下代码均来自neutrino教程-------**/
    private int addSlotRange(IInventory inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }


    /**-------以下代码均来自neutrino教程-------**/
    private int addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    /**-------以下代码均来自neutrino教程-------**/
    private void layoutPlayerInventorySlots(IInventory inventory, int leftCol, int topRow) {
        // Player inventory
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }

    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        ItemStack stack = playerIn.inventory.getCurrentItem();
        if(stack != ItemStack.EMPTY && stack != null && !stack.isEmpty() && stack.getItem() instanceof ItemArmorElementLegginsWithBottles){
        stack.getTag().put("bottle1", this.getSlot(0).getStack().serializeNBT());
        stack.getTag().putInt("bottle1Num", 0);
        stack.getTag().put("bottle2", this.getSlot(1).getStack().serializeNBT());
        stack.getTag().putInt("bottle2Num", 0);
        stack.getTag().put("bottle3", this.getSlot(2).getStack().serializeNBT());
        stack.getTag().putInt("bottle3Num", 0);}
        else{
            //playerIn.addItemStackToInventory(this.getSlot(0).getStack());
            //playerIn.addItemStackToInventory(this.getSlot(1).getStack());
            //playerIn.addItemStackToInventory(this.getSlot(2).getStack());
        }
    }

    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        Slot slot = this.inventorySlots.get(index);
        if(index >= 3){
            if(slot.getHasStack() && slot.getStack().getItem() instanceof PotionItem){
                for(int i = 0 ; i < 3;i++){
                    Slot slotPotion = this.inventorySlots.get(i);
                    if(slotPotion.getHasStack()) continue;
                    ItemStack stack =slotPotion.getStack();
                    stack = slot.getStack().copy();
                }
            }
        }
        return  ItemStack.EMPTY;
    }
}
class potionSlot extends Slot{
    public potionSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    public boolean isItemValid(ItemStack stack) {
        if(stack.getItem() instanceof PotionItem) return true;
        return false;
    }
}