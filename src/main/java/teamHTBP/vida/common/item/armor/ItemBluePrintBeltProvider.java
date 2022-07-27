package teamHTBP.vida.common.item.armor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import teamHTBP.vida.client.gui.GUI.ContainerBluePrint;

import javax.annotation.Nullable;

public class ItemBluePrintBeltProvider implements INamedContainerProvider {
    ItemStack stack = ItemStack.EMPTY;
    String providerName = "";

    public ItemBluePrintBeltProvider(String providerName, ItemStack stack) {
        this.stack = stack;
        this.providerName = providerName;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(providerName);
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new ContainerBluePrint(id, p_createMenu_2_, stack);
    }
}
