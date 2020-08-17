package teamHTBP.vida.gui.GUI;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamHTBP.vida.TileEntity.SlotNumberArray.OreReactionMachineArray;
import teamHTBP.vida.TileEntity.SlotNumberArray.PrismTableArray;
import teamHTBP.vida.TileEntity.TileEntityOreReationMachine;
import teamHTBP.vida.recipe.OreReactionMachineRecipe;

import javax.annotation.Nullable;

public class ContainerOreReactionMachine extends Container {
    protected TileEntityOreReationMachine machine;
    public ContainerOreReactionMachine(int winId, PlayerInventory inventory, BlockPos pos, World world, OreReactionMachineArray array) {
        super(ContainerTypeLoader.oreReaction.get(), winId);
        TileEntityOreReationMachine oreReationMachine = (TileEntityOreReationMachine) world.getTileEntity(pos);
        this.addSlot(new Slot(oreReationMachine.getSmeltSlot(),0,75,39 - 30));
        this.addSlot(new Slot(oreReationMachine.getSmeltSlot(),1,120,46 - 30));
        this.addSlot(new Slot(oreReationMachine.getSmeltSlot(),2,120,65 - 30));
        this.addSlot(new Slot(oreReationMachine.getSmeltSlot(),3,120,84 - 30));
        this.addSlot(new Slot(oreReationMachine.fuel,0,91,65 - 30));
        this.addSlot(new fobiddenSlot(oreReationMachine.getCompleteSlot(),0,75,90 - 30));
        //this.addSlot(new Slot(oreReationMachine.getSmeltSlot(),1,120,46));
        layoutPlayerInventorySlots(inventory,8,129 - 30);
        this.machine = oreReationMachine;
    }



    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
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

    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index <= 5) {
                if (!this.mergeItemStack(itemstack1, 6, 41, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (this.mergeItemStack(itemstack1, 0, 5, false)) {
                return ItemStack.EMPTY;
            } else if (index >= 6 && index < 33) {
                if (!this.mergeItemStack(itemstack1, 33, 41, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 33 && index < 41) {
                if (!this.mergeItemStack(itemstack1, 6, 41, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 6, 41, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}
