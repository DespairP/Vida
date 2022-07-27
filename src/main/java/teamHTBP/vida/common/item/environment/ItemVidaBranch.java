package teamHTBP.vida.common.item.environment;

import net.minecraft.item.Item;
import teamHTBP.vida.common.itemGroup.ItemGroupLoader;

public class ItemVidaBranch extends Item {
    public ItemVidaBranch() {
        super(new Properties().group(ItemGroupLoader.VIDA_ITEM_GROUP));
    }
}
