package teamHTBP.vida.menu;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import teamHTBP.vida.menu.base.ModBaseMenu;
import teamHTBP.vida.item.armor.ItemArmorElementLegginsWithBottles;

public class ContainerBottles extends ModBaseMenu {
    ItemStack stack;
    public Boolean isStack3Lock = true;

    public ContainerBottles(int id, Inventory inv, ItemStack stack) {
        super(ContainerTypeLoader.bottles.get(), id, inv);
        //getNBT
        ItemStack bottle1 = ItemStack.EMPTY, bottle2 = ItemStack.EMPTY, bottle3 = ItemStack.EMPTY;
        CompoundTag nbt = stack.getOrCreateTag();
        if (nbt.contains("bottle1")) {
            bottle1 = ItemStack.of(nbt.getCompound("bottle1"));
        }
        if (nbt.contains("bottle2")) {
            bottle2 = ItemStack.of(nbt.getCompound("bottle2"));
        }
        if (nbt.contains("bottle3")) {
            bottle3 = ItemStack.of(nbt.getCompound("bottle3"));
        }


        this.addSlot(new PotionSlot(new SimpleContainer(bottle1), 0, 59, 11));
        this.addSlot(new PotionSlot(new SimpleContainer(bottle2), 0, 80, 11));
        if (((ItemArmorElementLegginsWithBottles) stack.getItem()).element == 2) {
            this.addSlot(new PotionSlot(new SimpleContainer(bottle3), 0, 101, 11));
            this.isStack3Lock = false;
        } else {
            this.addSlot(new ContainerPrismTable.FobiddenSlot(new SimpleContainer(bottle3), 0, 101, 11));
        }

        this.stack = stack;
        layoutPlayerInventorySlots(inv, 8, 59);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stack == inventory.getSelected();
    }

    /**
     * -------以下代码均来自neutrino教程-------
     **/
    private int addSlotRange(Container inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }


    /**
     * -------以下代码均来自neutrino教程-------
     **/
    private int addSlotBox(Container inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    /**
     * -------以下代码均来自neutrino教程-------
     **/
    private void layoutPlayerInventorySlots(Container inventory, int leftCol, int topRow) {
        // Player inventory
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }

    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        ItemStack stack = inventory.getSelected();
        if (stack != ItemStack.EMPTY && stack != null && !stack.isEmpty() && stack.getItem() instanceof ItemArmorElementLegginsWithBottles) {
            stack.getTag().put("bottle1", this.getSlot(0).getItem().serializeNBT());
            stack.getTag().putInt("bottle1Num", 0);
            stack.getTag().put("bottle2", this.getSlot(1).getItem().serializeNBT());
            stack.getTag().putInt("bottle2Num", 0);
            stack.getTag().put("bottle3", this.getSlot(2).getItem().serializeNBT());
            stack.getTag().putInt("bottle3Num", 0);
        } else {
            //playerIn.addItemStackToInventory(this.getSlot(0).getStack());
            //playerIn.addItemStackToInventory(this.getSlot(1).getStack());
            //playerIn.addItemStackToInventory(this.getSlot(2).getStack());
        }
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot slot = this.slots.get(index);
        if (index >= 3) {
            if (slot.hasItem() && slot.getItem().getItem() instanceof PotionItem) {
                for (int i = 0; i < 3; i++) {
                    Slot slotPotion = this.slots.get(i);
                    if (slotPotion.hasItem()) continue;
                    ItemStack stack = slotPotion.getItem();
                    stack = slot.getItem().copy();
                }
            }
        }
        return ItemStack.EMPTY;
    }

    static class PotionSlot extends Slot {
        public PotionSlot(Container inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        public boolean mayPlace(ItemStack stack) {
            return stack.getItem() instanceof PotionItem;
        }
    }
}