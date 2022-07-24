package teamHTBP.vida.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import teamHTBP.vida.blockentity.TileEntityInjectTable;
import teamHTBP.vida.menu.base.VidaBaseMenu;
import teamHTBP.vida.element.ElementHelper;

public class InjectTableMenu extends VidaBaseMenu {
    private final Level world;
    private final BlockPos pos;
    public ItemStack stack;
    protected int element;
    public TileEntityInjectTable injectTable;

    public InjectTableMenu(int id, Inventory inventory, ItemStack itemStack, BlockPos pos, Level world) {
        super(TypeLoaderMenu.inject.get(), id, inventory);
        this.stack = ((TileEntityInjectTable) world.getBlockEntity(pos)).getSwordStack();
        this.injectTable = (TileEntityInjectTable) world.getBlockEntity(pos);
        this.world = world;
        this.pos = pos;
        getElement(itemStack);
        //this.addSlot(new Slot(new Inventory(itemStack), 0, 104, 72));
    }

    @Override
    public boolean stillValid(Player playerIn) {
        if (world.isAreaLoaded(pos, 1)
                && world.getBlockEntity(pos) instanceof TileEntityInjectTable
                && this.stack != ItemStack.EMPTY
                && this.stack != null) {
            stack = ((TileEntityInjectTable) world.getBlockEntity(pos)).getSwordStack();
            return true;
        } else
            return false;
    }

    protected void getElement(ItemStack stack) {
        element = 3;
        ElementHelper.addTip(stack);
    }

    static class SwordSlot extends Slot {
        public SwordSlot(Container inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPickup(Player playerIn) {
            return false;
        }
    }
}