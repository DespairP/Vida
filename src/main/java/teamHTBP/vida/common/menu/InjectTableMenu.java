package teamHTBP.vida.common.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import teamHTBP.vida.common.blockentity.InjectTableBlockEntity;
import teamHTBP.vida.common.menu.base.VidaBaseMenu;
import teamHTBP.vida.core.element.ElementHelper;

public class InjectTableMenu extends VidaBaseMenu {
    private final Level world;
    private final BlockPos pos;
    public ItemStack stack;
    protected int element;
    public InjectTableBlockEntity injectTable;

    public InjectTableMenu(int id, Inventory inventory, ItemStack itemStack, BlockPos pos, Level world) {
        super(VidaMenuTypeLoader.inject.get(), id, inventory);
        this.stack = ((InjectTableBlockEntity) world.getBlockEntity(pos)).getSwordStack();
        this.injectTable = (InjectTableBlockEntity) world.getBlockEntity(pos);
        this.world = world;
        this.pos = pos;
        getElement(itemStack);
        //this.addSlot(new Slot(new Inventory(itemStack), 0, 104, 72));
    }

    @Override
    public boolean stillValid(Player playerIn) {
        if (world.isAreaLoaded(pos, 1)
                && world.getBlockEntity(pos) instanceof InjectTableBlockEntity
                && this.stack != ItemStack.EMPTY
                && this.stack != null) {
            stack = ((InjectTableBlockEntity) world.getBlockEntity(pos)).getSwordStack();
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