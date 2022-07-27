package teamHTBP.vida.common.item.function;

import net.minecraft.item.Item;
import teamHTBP.vida.common.itemGroup.ItemGroupLoader;

public class ItemGemElement extends Item {
    public ItemGemElement() {
        super(new Properties().group(ItemGroupLoader.VIDA_ITEM_GROUP));
    }
}
