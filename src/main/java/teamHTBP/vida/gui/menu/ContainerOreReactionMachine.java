package teamHTBP.vida.gui.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import teamHTBP.vida.blockentity.SlotNumberArray.OreReactionMachineArray;
import teamHTBP.vida.blockentity.TileEntityOreReationMachine;
import teamHTBP.vida.gui.menu.base.ModBaseMenu;

public class ContainerOreReactionMachine extends ModBaseMenu {
    public TileEntityOreReationMachine machine;

    public ContainerOreReactionMachine(int winId, Inventory inventory, BlockPos pos, Level world, OreReactionMachineArray array) {
        super(ContainerTypeLoader.oreReaction.get(), winId, inventory);
        TileEntityOreReationMachine oreReationMachine = (TileEntityOreReationMachine) world.getBlockEntity(pos);
        this.addSlot(new Slot(oreReationMachine.getSmeltSlot(), 0, 75, 39 - 30));
        this.addSlot(new Slot(oreReationMachine.getSmeltSlot(), 1, 120, 46 - 30));
        this.addSlot(new Slot(oreReationMachine.getSmeltSlot(), 2, 120, 65 - 30));
        this.addSlot(new Slot(oreReationMachine.getSmeltSlot(), 3, 120, 84 - 30));
        this.addSlot(new Slot(oreReationMachine.fuel, 0, 91, 65 - 30));
        this.addSlot(new ContainerPrismTable.FobiddenSlot(oreReationMachine.getCompleteSlot(), 0, 75, 90 - 30));
        //this.addSlot(new Slot(oreReationMachine.getSmeltSlot(),1,120,46));
        layoutPlayerInventorySlots(inventory, 8, 129 - 30);
        this.machine = oreReationMachine;
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
            if (index <= 5) {
                if (!this.moveItemStackTo(itemstack1, 6, 41, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (this.moveItemStackTo(itemstack1, 0, 5, false)) {
                return ItemStack.EMPTY;
            } else if (index >= 6 && index < 33) {
                if (!this.moveItemStackTo(itemstack1, 33, 41, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 33 && index < 41) {
                if (!this.moveItemStackTo(itemstack1, 6, 41, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 6, 41, false)) {
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
}
