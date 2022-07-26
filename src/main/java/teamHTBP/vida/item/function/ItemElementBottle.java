package teamHTBP.vida.item.function;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

public class ItemElementBottle extends Item {
    public ItemElementBottle() {
        super(new Properties().group(ItemGroupLoader.VIDA_ITEM_GROUP).maxStackSize(3));
    }
}
