package teamHTBP.vida.item.function;

import net.minecraft.world.item.Item;
import teamHTBP.vida.creativetab.ItemGroupRegistry;

public class ItemElementBottle extends Item {
    public ItemElementBottle() {
        super(new Properties().tab(ItemGroupRegistry.vidaItemGroup).stacksTo(3));
    }
}
