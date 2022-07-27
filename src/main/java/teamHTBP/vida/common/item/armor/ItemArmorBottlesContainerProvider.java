package teamHTBP.vida.common.item.armor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import teamHTBP.vida.client.gui.GUI.ContainerBottles;

import javax.annotation.Nullable;

public class ItemArmorBottlesContainerProvider implements INamedContainerProvider {

    ItemStack stack = ItemStack.EMPTY;

    protected ItemArmorBottlesContainerProvider(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("bottle");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new ContainerBottles(p_createMenu_1_, p_createMenu_2_, stack);
    }
}
