package teamHTBP.vida.helper.item;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 */
public class ItemHandlerHelper {

    private ItemHandlerHelper() {}

    public static List<ItemStack> getItems(ItemStackHandler... handlers) {
        List<ItemStack> items = new ArrayList<>();

        for (ItemStackHandler handler : handlers) {
            for (int i = 0; i < handler.getSlots(); i++) {
                items.add(handler.getStackInSlot(i));
            }
        }

        return items;
    }
}
