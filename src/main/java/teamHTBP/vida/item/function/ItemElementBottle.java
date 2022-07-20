package teamHTBP.vida.item.function;

import net.minecraft.world.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemElementBottle extends Item {
    public ItemElementBottle() {
        super(new Properties().tab(ItemGroupLoader.vidaItemGroup).stacksTo(3));
    }
}
