package teamHTBP.vida.item.staff;

import net.minecraft.item.Item;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

/**
 * 生命法杖
 *
 * @version 0.0.2
 **/
public class ItemVidaWand extends Item {
    public ItemVidaWand() {
        super(new Item.Properties().tab(ItemGroupLoader.vidaItemGroup).stacksTo(1));
    }
}