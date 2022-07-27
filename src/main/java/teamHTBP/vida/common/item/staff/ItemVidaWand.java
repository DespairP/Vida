package teamHTBP.vida.common.item.staff;

import net.minecraft.item.Item;
import teamHTBP.vida.common.itemGroup.ItemGroupLoader;

/**
 * 生命法杖
 *
 * @version 0.0.2
 **/
public class ItemVidaWand extends Item {
    public ItemVidaWand() {
        super(new Item.Properties().group(ItemGroupLoader.VIDA_ITEM_GROUP).maxStackSize(1));
    }
}