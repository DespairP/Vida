package teamHTBP.vida.gui.GUI;

import jdk.nashorn.internal.ir.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamHTBP.vida.Capability.ElementHelper;
import teamHTBP.vida.Item.staff.ItemElementPickaxe;
import teamHTBP.vida.Item.staff.ItemElementSword;
import teamHTBP.vida.TileEntity.TileEntityInjectTable;
import teamHTBP.vida.gui.HUD.ElementSwordHUD;

import javax.annotation.Nullable;

public class ContainerInjectTable extends Container {
    protected ItemStack stack;
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
        //this.addSlot(new Slot(new Inventory(itemStack), 0, 104, 72));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        if(world.isAreaLoaded(pos,1) && world.getTileEntity(pos) instanceof TileEntityInjectTable)
        stack = ((TileEntityInjectTable) world.getTileEntity(pos)).getSwordStack();
        //System.out.println(stack.getOrCreateTag().getInt("luckIEXP"));
        return true;
    }

    protected void jud(ItemStack stack){
        if(stack.getItem() instanceof ItemElementSword){
            int element = ((ItemElementSword) stack.getItem()).element;
            this.element = element;
            ElementHelper.addTip(stack);
        }else if(stack.getItem() instanceof ItemElementPickaxe){
            //TODO
        }else if(stack.getItem() instanceof ToolItem){
            //TODO
        }
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