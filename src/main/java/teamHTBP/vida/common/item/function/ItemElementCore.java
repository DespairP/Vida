package teamHTBP.vida.common.item.function;

import net.minecraft.item.Item;
import teamHTBP.vida.common.itemGroup.ItemGroupLoader;

public class ItemElementCore extends Item {
    public ItemElementCore() {
        super(new Properties().group(ItemGroupLoader.VIDA_ITEM_GROUP));
    }
}
