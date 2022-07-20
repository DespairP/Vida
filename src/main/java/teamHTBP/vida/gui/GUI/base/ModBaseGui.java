package teamHTBP.vida.gui.gui.base;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

/**
 * @author DustW
 **/
public abstract class ModBaseGui<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    public ModBaseGui(T pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    protected <W extends AbstractWidget> W close(W widget) {
        widget.active = false;
        widget.visible = false;
        return widget;
    }

    protected <W extends AbstractWidget> W open(W widget) {
        widget.active = true;
        widget.visible = true;
        return widget;
    }
}
