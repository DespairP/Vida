package teamHTBP.vida.item.armor;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.menu.ContainerBottles;
import teamHTBP.vida.menu.base.ModBaseMenu;

import javax.annotation.Nullable;

public class ItemArmorBottlesContainerProvider implements MenuProvider {

    ItemStack stack = ItemStack.EMPTY;

    protected ItemArmorBottlesContainerProvider(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public TextComponent getDisplayName() {
        return new TextComponent("bottle");
    }

    @Nullable
    @Override
    public ModBaseMenu createMenu(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
        return new ContainerBottles(p_createMenu_1_, p_createMenu_2_, stack);
    }
}
