package teamHTBP.vida.utils;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 */
public class ContainerHelper {
    public static List<ItemStack> getItems(Container... containers) {
        List<ItemStack> result = new ArrayList<>();

        for (Container container : containers) {
            for (int i = 0; i < container.getContainerSize(); i++) {
                ItemStack item = container.getItem(i);

                if (!item.isEmpty()) {
                    result.add(item);
                }
            }
        }

        return result;
    }
}
