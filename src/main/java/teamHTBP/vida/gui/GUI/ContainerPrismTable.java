package teamHTBP.vida.gui.GUI;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamHTBP.vida.TileEntity.SlotNumberArray.PrismTableArray;
import teamHTBP.vida.TileEntity.TileEntityPrismTable;

import javax.annotation.Nullable;

public class ContainerPrismTable extends Container {
    private PrismTableArray array ;
    public TileEntityPrismTable tileEntityPrismTable;

    public ContainerPrismTable(int winId, PlayerInventory inventory, BlockPos pos, World world, PrismTableArray array) {
        super(ContainerTypeLoader.prismTable.get(), winId);
        this.array = array;
        this.tileEntityPrismTable = (TileEntityPrismTable) world.getTileEntity(pos);
        this.addSlot(new Slot(tileEntityPrismTable.getSlot(),0,10,16));
        this.addSlot(new Slot(tileEntityPrismTable.getSlot(),1,28,16));
        this.addSlot(new fobiddenSlot(tileEntityPrismTable.getSlot(),2,44,59));
        layoutPlayerInventorySlots(inventory,8,84);
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


}
class fobiddenSlot extends Slot{

    public fobiddenSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    public boolean isItemValid(ItemStack stack) {
        return false;
    }
}