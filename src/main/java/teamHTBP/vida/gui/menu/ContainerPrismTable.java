package teamHTBP.vida.gui.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import teamHTBP.vida.blockentity.SlotNumberArray.PrismTableArray;
import teamHTBP.vida.blockentity.TileEntityPrismTable;
import teamHTBP.vida.gui.menu.base.ModBaseMenu;

public class ContainerPrismTable extends ModBaseMenu {
    private final PrismTableArray array;
    public TileEntityPrismTable tileEntityPrismTable;

    public ContainerPrismTable(int winId, Inventory inventory, BlockPos pos, Level world, PrismTableArray array) {
        super(ContainerTypeLoader.prismTable.get(), winId, inventory);
        this.array = array;
        this.tileEntityPrismTable = (TileEntityPrismTable) world.getBlockEntity(pos);
        this.addSlot(new Slot(tileEntityPrismTable.getSlot(), 0, 10, 16));
        this.addSlot(new Slot(tileEntityPrismTable.getSlot(), 1, 28, 16));
        this.addSlot(new FobiddenSlot(tileEntityPrismTable.getSlot(), 2, 44, 59));
        layoutPlayerInventorySlots(inventory, 8, 84);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return true;
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

    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 0 || index == 1 || index == 2) {
                if (!this.moveItemStackTo(itemstack1, 3, 38, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (this.moveItemStackTo(itemstack1, 0, 2, false)) {
                return ItemStack.EMPTY;
            } else if (index >= 3 && index < 30) {
                if (!this.moveItemStackTo(itemstack1, 30, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 30 && index < 39) {
                if (!this.moveItemStackTo(itemstack1, 3, 29, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 3, 38, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

    static class FobiddenSlot extends Slot {

        public FobiddenSlot(Container inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }
}