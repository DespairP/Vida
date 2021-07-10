package teamHTBP.vida.gui.GUI;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamHTBP.vida.helper.ElementHelper;
import teamHTBP.vida.TileEntity.TileEntityInjectTable;

public class ContainerInjectTable extends Container {
    protected ItemStack stack = ItemStack.EMPTY;
    protected int element;
    protected TileEntityInjectTable injectTable;
    private World world;
    private BlockPos pos;
    public ContainerInjectTable(int id, ItemStack itemStack,BlockPos pos, World world) {
        super(ContainerTypeLoader.inject.get(), id);
        this.stack = ((TileEntityInjectTable) world.getTileEntity(pos)).getSwordStack();
        this.injectTable = (TileEntityInjectTable) world.getTileEntity(pos);
        this.world = world;
        this.pos = pos;
        getElement(itemStack);
        //this.addSlot(new Slot(new Inventory(itemStack), 0, 104, 72));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        if(world.isAreaLoaded(pos,1)
                && world.getTileEntity(pos) instanceof TileEntityInjectTable
                && this.stack != ItemStack.EMPTY
                && this.stack != null) {
            stack = ((TileEntityInjectTable) world.getTileEntity(pos)).getSwordStack();
            return true;
        }else
            return false;
    }

    protected void getElement(ItemStack stack){
        element = 3;
        ElementHelper.addTip(stack);
    }
}
class swordSlot extends Slot{
    public swordSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    public boolean canTakeStack(PlayerEntity playerIn) {
        return false;
    }
}