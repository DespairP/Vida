package teamHTBP.vida.gui.GUI;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ContainerBluePrint extends Container {
    public final int[][] slotPositions = {{83, 41}, {108, 41}, {71, 64}, {96, 64}, {121, 64}, {83, 87}, {108, 87}};

    public ContainerBluePrint(int id, PlayerInventory inventory, ItemStack stack) {
        super(ContainerTypeLoader.bluePrints.get(), id);

        CompoundNBT nbt = stack.getOrCreateTag();
        int index = 0;
        for (int i = 0; i < slotPositions.length; i++) {
            addSlot(new BluePrintSlot(new Inventory(ItemStack.of(nbt.getCompound("bluePrintSlot" + i))), 0, slotPositions[i][0] - 28, slotPositions[i][1] - 56));
        }

        layoutPlayerInventorySlots(inventory, 24 - 28, 129 - 56);
    }

    /**
     * -------以下代码均来自neutrino教程-------
     **/
    private int addSlotRange(IInventory inventory, int index, int x, int y, int amount, int dx) {
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
    private int addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    /**
     * -------以下代码均来自neutrino教程-------
     **/
    private void layoutPlayerInventorySlots(IInventory inventory, int leftCol, int topRow) {
        // Player inventory
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public void removed(PlayerEntity playerIn) {

    }
}

class BluePrintSlot extends Slot {
    public BluePrintSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
