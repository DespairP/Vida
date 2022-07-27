package teamHTBP.vida.common.item.armor;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.common.menu.BluePrintMenu;

import javax.annotation.Nullable;

public class ItemBluePrintBeltProvider implements MenuProvider {
    ItemStack stack = ItemStack.EMPTY;
    String providerName = "";

    public ItemBluePrintBeltProvider(String providerName, ItemStack stack) {
        this.stack = stack;
        this.providerName = providerName;
    }

    @Override
    public TextComponent getDisplayName() {
        return new TextComponent(providerName);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory p_createMenu_2_, Player p_createMenu_3_) {
        return new BluePrintMenu(id, p_createMenu_2_, stack);
    }
}
