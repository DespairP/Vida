package teamHTBP.vida.common.item.function;

import net.minecraft.item.Item;
import teamHTBP.vida.common.itemGroup.ItemGroupLoader;

public class ItemElementBottle extends Item {
    public ItemElementBottle() {
        super(new Properties().group(ItemGroupLoader.VIDA_ITEM_GROUP).maxStackSize(3));
    }
}
