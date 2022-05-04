package teamHTBP.vida.item.function;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemElementCore extends Item {
    public ItemElementCore() {
        super(new Properties().group(ItemGroupLoader.vidaItemGroup));
    }
}
